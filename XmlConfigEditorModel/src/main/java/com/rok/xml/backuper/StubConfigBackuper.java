package com.rok.xml.backuper;

import com.rok.xml.api.ConfigBackuper;

/**
 * Created by RoK on 12.07.2015.
 * All rights reserved =)
 */
public class StubConfigBackuper implements ConfigBackuper {
    @Override
    public boolean backupConfig() {
        return true;
    }

    @Override
    public void deleteBackup() {

    }

    @Override
    public boolean isBackupAlreadyExists() {
        return false;
    }
}
