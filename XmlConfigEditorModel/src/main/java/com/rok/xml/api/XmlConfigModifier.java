package com.rok.xml.api;

import com.rok.xml.config_dto.ConfigBlock;
import com.rok.xml.config_dto.ConfigModificationInfo;
import com.rok.xml.config_dto.ConfigNode;

import java.io.File;
import java.io.IOException;

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
