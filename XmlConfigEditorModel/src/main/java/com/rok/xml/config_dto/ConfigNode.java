package com.rok.xml.config_dto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by RoK on 27.06.2015.
 * All rights reserved =)
 */
public interface ConfigNode extends Serializable{

    String getName();
    void setName(String name);

    String getDisplayName();
    void setDisplayName(String displayName);

    ConfigNodeType getNodeType();
    void setNodeType(ConfigNodeType type);


    List<ConfigValueNode> getNodeAttributes();


    void setEditable(boolean isEditable);
    boolean isEditable();
}

