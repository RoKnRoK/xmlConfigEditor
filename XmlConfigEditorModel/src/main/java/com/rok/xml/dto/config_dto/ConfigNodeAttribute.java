package com.rok.xml.dto.config_dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

/**
 * Created by RoK on 21.06.2015.
 * All rights reserved =)
 */
@XmlRootElement
public class ConfigNodeAttribute implements ConfigValueNode {
    private static final long serialVersionUID = -677434795409847499L;

    private String name;
    private String displayName;
    private String value;
    private String originalValue;
    private boolean isEditable;
    private ConfigNodeType nodeType;
    private AbstractConfigNode parentNode;

    public ConfigNodeAttribute(String name, String value) {
        this();
        this.name = name;
        this.displayName = name;
        this.value = value;
        this.originalValue = value;
    }

    private ConfigNodeAttribute(){
        this.nodeType = ConfigNodeType.ATTRIBUTE;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    @JsonProperty
    public String getDisplayName() {
        return name;
    }

    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }

    public String getOriginalValue() {
        return originalValue;
    }

    @Override
    public boolean isChanged() {
        return !Objects.equals(getOriginalValue(), getValue());
    }

    @Override
    public ConfigNodeType getNodeType() {
        return this.nodeType;
    }
    @Override
    public void setNodeType(ConfigNodeType type) {
        this.nodeType = type;
    }

    @Override
    public void setEditable(boolean isEditable) {
        this.isEditable = isEditable;
    }

    @Override
    public boolean isEditable() {
        return this.isEditable;
    }

    @Override
    public AbstractConfigNode getParentNode() {
        return this.parentNode;
    }
    @Override
    public void setParentNode(AbstractConfigNode parentNode) {
        this.parentNode = parentNode;
    }

    @Override
    public String toString() {
        return name +"=\"" + value +"\"";
    }

}
