package com.rok.xml.backuper;

import com.rok.xml.api.ConfigBackuper;

import java.io.File;

/**
 * Created by RoK on 11.07.2015.
 * All rights reserved =)
 */
//todo: don't remember
@SuppressWarnings("unused")
public class FNConfigBackuper implements ConfigBackuper {

    public FNConfigBackuper(File configToBackup) {
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

}
