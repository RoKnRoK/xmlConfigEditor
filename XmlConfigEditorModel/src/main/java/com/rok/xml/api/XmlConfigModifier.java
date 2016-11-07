package com.rok.xml.api;

import com.rok.xml.config_dto.ConfigBlock;
import com.rok.xml.config_dto.ConfigNode;

import java.io.File;
import java.io.IOException;

/**
 * Created by RoK on 30.06.2015.
 * All rights reserved =)
 */
public interface XmlConfigModifier {

    ConfigBlock getConfig();
    Object getConfigAsObject();
    boolean saveConfig(ConfigBlock config);

    void setBackuper(ConfigBackuper backuper);
    void setLocker(ConfigLocker locker);
}
