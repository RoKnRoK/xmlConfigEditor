package com.rok.xml.api;

/**
 * Created by RoK on 11.07.2015.
 * All rights reserved =)
 */
public interface ConfigBackuper {
    boolean backupConfig();
    void deleteBackup();
    boolean isBackupAlreadyExists();
}
