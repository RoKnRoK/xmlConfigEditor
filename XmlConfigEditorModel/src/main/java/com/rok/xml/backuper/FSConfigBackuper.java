package com.rok.xml.backuper;

import com.rok.xml.api.ConfigBackuper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * Created by RoK on 11.07.2015.
 * All rights reserved =)
 */
public class FSConfigBackuper implements ConfigBackuper {

    public static final String BACKUP_POSTFIX = "_backup";

    private final Logger logger = LoggerFactory.getLogger(FSConfigBackuper.class);

    private File configForBackup;
    private boolean rewriteOldBackup;
    private File backupedConfig;
    private boolean backupAlreadyExists = false;

    public FSConfigBackuper(File configForBackup) {
        this(configForBackup, true);
    }

    public FSConfigBackuper(File configForBackup, boolean rewriteOldBackup) {
        this.configForBackup = configForBackup;
        this.rewriteOldBackup = rewriteOldBackup;

        StringBuilder xmlBackupConfigName = getBackupFileName();
        this.backupedConfig = new File(xmlBackupConfigName.toString());
    }

    @Override
    public boolean backupConfig() {
        logger.trace("Starting backup config file {}", configForBackup.getAbsolutePath());
        FileChannel source = null;
        FileChannel destination = null;
        try {
            if (backupedConfig.exists()){
                backupAlreadyExists = true;
                if (!rewriteOldBackup) {
                    logger.error("There is backup already exists and ConfigBackuper not allowed to rewrite it. " +
                            "Further work will be in read-only mode.");
                    return false;
                }
                logger.warn("There is backup exists that was not deleted while previous editing session by some reason. It will be rewritten");
                if (backupedConfig.delete()) {
                    logger.trace("Previous backup deleted");
                }
            }

            boolean newFileCreated = backupedConfig.createNewFile();
            if (!newFileCreated) {
                backupAlreadyExists = true;
                logger.error("Backup not created. Further work will be in read-only mode.");
                return false;
            }

            source = new FileInputStream(configForBackup).getChannel();
            destination = new FileOutputStream(backupedConfig).getChannel();
            destination.transferFrom(source, 0, source.size());
            logger.trace("Backup {} successfully created", getBackupFileName());
            return true;
        } catch (Exception e) {
            logger.error("Error occurred while creating backup. Further work will be in read-only mode", e);
            return false;
        } finally {
            try {
                if (source != null) {
                    source.close();
                }
                if (destination != null) {
                    destination.close();
                }
            } catch (IOException e) {
                logger.error("Error while closing original or backup file: " + e.getMessage(), e);
            }
        }
    }

    @Override
    public void deleteBackup() {
        boolean deleted = backupedConfig.delete();
        logger.trace("Backup {} deleted", deleted ? "" : "not");
    }

    private StringBuilder getBackupFileName() {
        String xmlConfigName = configForBackup.getAbsolutePath();
        StringBuilder xmlBackupConfigName = new StringBuilder(xmlConfigName);
        xmlBackupConfigName.insert(xmlBackupConfigName.indexOf(".xml"), BACKUP_POSTFIX);
        return xmlBackupConfigName;
    }

    @Override
    public boolean isBackupAlreadyExists() {
        return backupAlreadyExists;
    }
}
