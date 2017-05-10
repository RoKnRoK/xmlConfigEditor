package com.rok.xml.locker;

import com.rok.xml.Constants;
import com.rok.xml.api.ConfigLocker;
import com.rok.xml.dto.LockInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.*;

/**
 * Created by RoK.
 * All rights reserved =)
 */
public class ByLockFileLocker implements ConfigLocker {

    private final Logger logger = LoggerFactory.getLogger(ByLockFileLocker.class);

    private File lockingConfig;
    private UUID lockGuid;
    private File lockFile;

    public ByLockFileLocker(File lockingConfig) {
        this.lockingConfig = lockingConfig;
    }


    @Override
    public boolean isConfigLockedBySomeoneElse() {
        return lockGuid == null;
    }

    @Override
    public LockInfo tryLockConfig() {
        try {
            String lockFilePath = lockingConfig.getParent() + File.separator + Constants.LOCK_FILENAME;
            lockFile = new File(lockFilePath);
            logger.trace("Trying to create custom lock file {}", lockFile.getAbsolutePath());
            boolean created = lockFile.createNewFile();
            if (!created) {
                logger.error("Config is already locked by someone else");
                return new LockInfo();
            }
            lockGuid = UUID.randomUUID();
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

    @Override
    public void unlockConfig(Serializable lockGuid) {
        if (lockGuid instanceof String){
            lockGuid = UUID.fromString((String) lockGuid);
        }
        if (!(lockGuid instanceof UUID) && (lockGuid != null)) {
            logger.error("Cannot process lock of type {}, only UUID is supported", lockGuid.getClass().getName());
            return;
        }
        if (!Objects.equals(lockGuid, this.lockGuid)) {
            logger.error("Cannot unlock lock {}: not owned lock", lockGuid);
            return;
        }
        try {
            logger.trace("Trying to FS unlock file {}", lockingConfig.getAbsolutePath());
            if (this.lockGuid == null) {
                logger.warn("File {} is locked by other user, cannot unlock", lockingConfig.getAbsolutePath());
                return;
            }
            boolean deleted = lockFile.delete();
            if (deleted) {logger.trace("Successfully unlocked");}
            
        } catch (Exception e) {
            logger.error("Some error occurred while unlocking config: {}", e.getMessage(), e);
        }
    }
}
