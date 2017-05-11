package com.rok.xml.locker;

import com.rok.xml.Constants;
import com.rok.xml.api.ConfigLocker;
import com.rok.xml.dto.LockInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

/**
 * Created by RoK.
 * All rights reserved =)
 */
public class ByLockFileLocker implements ConfigLocker {

    private final Logger logger = LoggerFactory.getLogger(ByLockFileLocker.class.getName());

    private File lockingConfig;

    public ByLockFileLocker(File lockingConfig) {
        this.lockingConfig = lockingConfig;
    }


    @Override
    public LockInfo tryLockConfig() {
        try {
            File lockFile = getLockFile();
            logger.trace("Trying to create custom lock file {}", lockFile.getAbsolutePath());
            boolean created = lockFile.createNewFile();
            if (!created) {
                logger.error("Config is already locked by someone else");
                return new LockInfo();
            }
            String lockGuid = UUID.randomUUID().toString();
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(lockFile))) {
                bw.write(lockGuid + "\n" + new Date().getTime() + "\n");
            } catch (IOException e) {
                logger.error("Cannot write lock info to lock file");
                return new LockInfo();
            }

            return new LockInfo(lockGuid, new Date().getTime());

        } catch (Exception e) {
            return new LockInfo();
        }
    }

    private File getLockFile() {
        String lockFilePath = lockingConfig.getParent() + File.separator + Constants.LOCK_FILENAME;
        return new File(lockFilePath);
    }

    @Override
    public boolean unlockConfig(Serializable lockGuid) {
        if (lockGuid instanceof String) {
            lockGuid = UUID.fromString((String) lockGuid);
        }
        if (!(lockGuid instanceof UUID) && (lockGuid != null)) {
            logger.error("Cannot process lock of type {}, only UUID is supported", lockGuid.getClass().getName());
            return false;
        }

        File lockFile = getLockFile();

        Serializable readLockGuid = readLockGuid(lockFile);
        if (readLockGuid == null) {
            return false;
        }
        if (!Objects.equals(lockGuid, readLockGuid)) {
            logger.warn("File {} is locked by other user, cannot unlock", lockingConfig.getAbsolutePath());
            return false;
        }

        try {
            logger.trace("Trying to FS unlock file {}", lockingConfig.getAbsolutePath());
            boolean deleted = lockFile.delete();
            if (deleted) {
                logger.trace("Successfully unlocked");
            }
            return deleted;
        } catch (Exception e) {
            logger.error("Some error occurred while unlocking config: {}", e.getMessage(), e);
            return false;
        }
    }

    private UUID readLockGuid(File lockFile) {
        if (lockFile == null || !lockFile.exists()) {
            logger.trace("No lock exists, skipping unlocking");
            return null;
        }
        Serializable readLockGuid;
        try (BufferedReader bw = new BufferedReader(new FileReader(lockFile))) {
            readLockGuid = bw.readLine();
        } catch (IOException e) {
            logger.error("Cannot read current lock info: ", e);
            return null;
        }
        return UUID.fromString((String) readLockGuid);
    }
}
