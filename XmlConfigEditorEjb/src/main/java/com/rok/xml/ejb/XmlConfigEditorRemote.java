package com.rok.xml.ejb;

import com.rok.xml.config_dto.ConfigBlock;
import com.rok.xml.config_dto.ConfigModificationInfo;

import javax.ejb.Remote;

/**
 * Created by RoK on 10.07.2015.
 * All rights reserved =)
 */
@Remote
public interface XmlConfigEditorRemote {
    ConfigModificationInfo getConfigModificationInfo();
    boolean saveConfigBlock(ConfigModificationInfo configModificationInfo) ;
    void cancelConfigEditing(ConfigModificationInfo configModificationInfo);
}
