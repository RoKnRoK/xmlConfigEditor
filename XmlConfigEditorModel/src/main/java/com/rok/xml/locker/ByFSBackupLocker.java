package com.rok.xml.locker;

import com.rok.xml.api.ConfigBackuper;
import com.rok.xml.api.ConfigLocker;
import com.rok.xml.backuper.FSConfigBackuper;

import java.io.File;
import java.io.IOException;

/**
 * Created by RoK on 11.07.2015.
 * All rights reserved =)
 */
public class ByFSBackupLocker implements ConfigLocker {
    private final ConfigBackuper backuper;

    public ByFSBackupLocker(File configToLock) {
        this.backuper = new FSConfigBackuper(configToLock, false);
    }

    @Override
    public boolean isConfigLockedBySomeoneElse() {
        return backuper.isBackupAlreadyExists();
    }

    @Override
    public void tryLockConfig() {
        backuper.backupConfig();
    }

    @Override
    public void unlockConfig() {
        backuper.deleteBackup();
    }
}
