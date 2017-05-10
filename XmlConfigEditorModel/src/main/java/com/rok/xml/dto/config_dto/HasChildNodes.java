package com.rok.xml.dto.config_dto;

import java.util.List;

/**
 * Created by RoK on 28.06.2015.
 * All rights reserved =)
 */
public interface HasChildNodes {

    List<ConfigNode> getChildNodes();
    void setChildNodes(List<ConfigNode> childNodes);
    ConfigNode getChildNode(Integer selectedItem);
    void addChildNode(ConfigNode childNode);
}
