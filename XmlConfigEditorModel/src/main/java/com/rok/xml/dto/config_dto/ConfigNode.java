package com.rok.xml.dto.config_dto;

import com.fasterxml.jackson.annotation.*;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonSubTypes.*;

/**
 * Created by RoK on 27.06.2015.
 * All rights reserved =)
 */
@XmlJavaTypeAdapter(AnyTypeAdapter.class)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "nodeType")
@JsonSubTypes({
        @Type(name = "BLOCK", value = ConfigBlock.class),
        @Type(name = "ROOT_BLOCK", value = ConfigBlock.class),
        @Type(name = "ENTRY", value = ConfigEntry.class),
        @Type(name = "BOOLEAN_ENTRY", value = ConfigBooleanEntry.class),
        @Type(name = "ATTRIBUTE", value = ConfigNodeAttribute.class)
})
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class)
public interface ConfigNode extends Serializable{

    String getName();
    void setName(String name);

    String getDisplayName();

    ConfigNodeType getNodeType();
    void setNodeType(ConfigNodeType type);


    List<ConfigValueNode> getAttributes();


    void setEditable(boolean isEditable);
    boolean isEditable();
}

