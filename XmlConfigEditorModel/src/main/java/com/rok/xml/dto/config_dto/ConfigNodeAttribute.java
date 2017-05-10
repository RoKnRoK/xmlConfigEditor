package com.rok.xml.dto.config_dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by RoK on 21.06.2015.
 * All rights reserved =)
 */
public class ConfigNodeAttribute implements ConfigValueNode {
    private static final long serialVersionUID = -677434795409847499L;
    private String attributeName;
    private String attributeValue;
    private boolean isEditable;



    private String attributeOriginalValue;
    private ConfigNodeType type;

    public ConfigNodeAttribute(String attributeName, String attributeValue) {
        this();
        this.attributeName = attributeName;
        this.attributeValue = attributeValue;
        this.attributeOriginalValue = attributeValue;

    }

    public ConfigNodeAttribute(){
        this.type = ConfigNodeType.ATTRIBUTE;
    }

    public String getName() {
        return attributeName;
    }
    public void setName(String name) {
        this.attributeName = name;
    }

    @Override
    public String getDisplayName() {
        return attributeName;
    }
    @Override
    public void setDisplayName(String displayName) {
        this.attributeName = displayName;
    }

    public String getValue() {
        return attributeValue;
    }
    public void setValue(String value) {
        this.attributeValue = value;
    }

    public String getOriginalValue() {
        return attributeOriginalValue;
    }

    @Override
    public boolean isChanged() {
        return !Objects.equals(getOriginalValue(), getValue());
    }

    @Override
    public ConfigNodeType getNodeType() {
        return this.type;
    }
    @Override
    public void setNodeType(ConfigNodeType type) {
        this.type = type;
    }

    @Override
    public List<ConfigValueNode> getNodeAttributes() {
        return new ArrayList<>();
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
    public String toString() {
        return attributeName +"=\"" + attributeValue+"\"";
    }

}
