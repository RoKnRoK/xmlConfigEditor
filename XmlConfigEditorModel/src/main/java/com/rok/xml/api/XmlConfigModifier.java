package com.rok.xml.api;

import com.rok.xml.dto.config_dto.ConfigModificationInfo;

/**
 * Created by RoK on 30.06.2015.
 * All rights reserved =)
 */
public interface XmlConfigModifier {

    ConfigModificationInfo getConfig();

    boolean saveConfig(ConfigModificationInfo configModificationInfo);
    void cancelConfigEditing(ConfigModificationInfo configModificationInfo);

    @SuppressWarnings("unused")
    void setBackuper(ConfigBackuper backuper);
    @SuppressWarnings("unused")
    void setLocker(ConfigLocker locker);
}
