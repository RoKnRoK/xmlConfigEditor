package com.rok.xml.dto.config_dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

/**
 * Created by RoK on 28.06.2015.
 * All rights reserved =)
 */
public interface HasChildNodes {

    List<ConfigValueNode> getEntries();
    ConfigValueNode getEntry(Integer index);

    List<ConfigValueNode> getBooleanEntries();
    ConfigValueNode getBooleanEntry(Integer index);

    List<ConfigNode> getBlocks();
    ConfigNode getBlock(Integer index);

    void addChildNode(ConfigNode childNode);
}
