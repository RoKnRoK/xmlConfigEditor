package com.rok.xml.api;

import com.rok.xml.dto.config_dto.ConfigModificationInfo;

/**
 * Created by RoK on 30.06.2015.
 * All rights reserved =)
 */
public interface XmlConfigModifier {

    ConfigModificationInfo getConfig();
    Object getConfigAsObject();
    boolean saveConfig(ConfigModificationInfo configModificationInfo);
    void cancelConfigEditing(ConfigModificationInfo configModificationInfo);

    void setBackuper(ConfigBackuper backuper);
    void setLocker(ConfigLocker locker);
}
