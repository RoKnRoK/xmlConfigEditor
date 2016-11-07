package com.rok.xml.backuper;

import com.rok.xml.api.ConfigBackuper;

import java.io.File;

/**
 * Created by RoK on 11.07.2015.
 * All rights reserved =)
 */
//todo: и это тоже
public class FNConfigBackuper implements ConfigBackuper {
    private File configToBackup;

    public FNConfigBackuper(File configToBackup) {
        this.configToBackup = configToBackup;
    }

    @Override
    public boolean backupConfig() {
        return false;
    }

    @Override
    public void deleteBackup() {

    }

    private StringBuilder getBackupFileName() {
        return null;
    }

    @Override
    public boolean isBackupAlreadyExists() {
        return false;
    }
}
