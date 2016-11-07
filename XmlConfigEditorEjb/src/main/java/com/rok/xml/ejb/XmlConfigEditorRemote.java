package com.rok.xml.ejb;

import com.rok.xml.config_dto.ConfigBlock;

import javax.ejb.Remote;

/**
 * Created by RoK on 10.07.2015.
 * All rights reserved =)
 */
@Remote
public interface XmlConfigEditorRemote {
    ConfigBlock getConfigBlock();
    boolean saveConfigBlock(ConfigBlock configBlock) ;
}
