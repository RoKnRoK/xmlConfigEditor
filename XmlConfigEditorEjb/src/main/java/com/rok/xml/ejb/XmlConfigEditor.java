package com.rok.xml.ejb;

import com.rok.xml.dto.config_dto.ConfigModificationInfo;

/**
 * Created by roman.kulikov on 6/28/2017.
 * All rights reserved =D
 */
public interface XmlConfigEditor {
    ConfigModificationInfo getConfigModificationInfo();
    boolean saveConfigBlock(ConfigModificationInfo configModificationInfo) ;
    void cancelConfigEditing(ConfigModificationInfo configModificationInfo);
}