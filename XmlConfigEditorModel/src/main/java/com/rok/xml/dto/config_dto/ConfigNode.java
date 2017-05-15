package com.rok.xml.dto.config_dto;

import javax.xml.bind.annotation.XmlSeeAlso;
import java.io.Serializable;
import java.util.List;

/**
 * Created by RoK on 27.06.2015.
 * All rights reserved =)
 */
@XmlSeeAlso({
        ConfigBlock.class,
        ConfigBooleanEntry.class,
        ConfigEntry.class,
        ConfigNodeAttribute.class,
        AbstractConfigNode.class,  })
public interface ConfigNode extends Serializable{

    String getName();
    void setName(String name);

    String getDisplayName();

    ConfigNodeType getNodeType();
    void setNodeType(ConfigNodeType type);


    List<ConfigValueNode> getNodeAttributes();


    void setEditable(boolean isEditable);
    boolean isEditable();
}

