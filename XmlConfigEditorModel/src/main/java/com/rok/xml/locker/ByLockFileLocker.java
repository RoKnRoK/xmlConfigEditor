package com.rok.xml.locker;

import com.rok.xml.api.ConfigLocker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * Created by RoK.
 * All rights reserved =)
 */
public class ByLockFileLocker implements ConfigLocker {

    private final Logger logger = LoggerFactory.getLogger(ByLockFileLocker.class);

    private File lockingConfig;
    private static final String LOCK_FILENAME = "config.lock";
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
    public UUID tryLockConfig() {
        try {
            String path = lockingConfig.getParent();
            lockFile = new File(path + "\\" + LOCK_FILENAME);
            logger.trace("Trying to create custom lock file {}", lockFile.getAbsolutePath());
            boolean created = lockFile.createNewFile();
            if (!created) {
                logger.error("Config is already locked by someone else");
                return null;
            }
            lockGuid = UUID.randomUUID();
            return lockGuid;

        } catch (Exception e) {
            return null;
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
