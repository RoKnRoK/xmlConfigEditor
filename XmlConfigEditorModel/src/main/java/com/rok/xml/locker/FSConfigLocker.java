package com.rok.xml.locker;

import com.rok.xml.api.ConfigLocker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;

/**
 * Created by RoK on 11.07.2015.
 * All rights reserved =)
 */
public class FSConfigLocker implements ConfigLocker {

    private final Logger logger = LoggerFactory.getLogger(FSConfigLocker.class);

    private FileLock lock;
    private FileChannel channel;
    private File lockingConfig;
    private boolean isConfigLockedBySomeoneElse;

    public FSConfigLocker(File lockingConfig) {
        this.isConfigLockedBySomeoneElse = false;
        this.lockingConfig = lockingConfig;
    }


    @Override
    public boolean isConfigLockedBySomeoneElse() {
        return isConfigLockedBySomeoneElse;
    }

    @Override
    public void tryLockConfig() {
        logger.trace("Trying to FS lock file {}", lockingConfig.getAbsolutePath());
        try {
            channel = new RandomAccessFile(lockingConfig, "rw").getChannel();

            try {
                lock = channel.tryLock(0L, Long.MAX_VALUE, true);
            } catch (OverlappingFileLockException e) {
                logger.error("Config is already locked by someone else");
                isConfigLockedBySomeoneElse = true;
                return;
            }

            isConfigLockedBySomeoneElse = false;
        } catch (Exception e) {
            isConfigLockedBySomeoneElse = true;
        }

    }

    @Override
    public void unlockConfig() {
        try {
            logger.trace("Trying to FS unlock file {}", lockingConfig.getAbsolutePath());
            if (lock != null) {
                lock.release();
                logger.trace("Successfully unlocked");
            } else {
                logger.warn("File {} is locked by other user, cannot unlock", lockingConfig.getAbsolutePath());
            }
            if (channel != null) {
                channel.close();
            }
        } catch (IOException e) {
            logger.error("Some error occurred while unlocking config: {}", e.getMessage(), e);
        }
    }
}
