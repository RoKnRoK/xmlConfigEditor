package com.rok.xml.dto.config_dto;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

/**
 * Created by RoK on 21.06.2015.
 * All rights reserved =)
 */
@XmlRootElement
public class ConfigEntry extends AbstractConfigNode implements ConfigValueNode {

    private static final long serialVersionUID = -4269070863916591307L;

    String value;
    private String originalValue;

    public ConfigEntry(String configEntryName, String value, AbstractConfigNode parentNode) {
        this.name = configEntryName;
        this.value = value;
        this.originalValue = value;
        this.parentNode = parentNode;

    }

    ConfigEntry() {
        this.name = "defaultEntryName";
        this.value = this.originalValue = "defaultEntryValue";
    }

    public String getValue() {
        return value;
    }
    public void setValue(String configEntryValue) {
        this.value = configEntryValue;
    }

    public String getOriginalValue() {
        return originalValue;
    }

    @Override
    public boolean isChanged() {
        return !Objects.equals(getOriginalValue(), getValue());
    }


    public ConfigNodeType getNodeType(){
        if (getAttributes() != null && !getAttributes().isEmpty()) {
            return ConfigNodeType.ENTRY;
        }
        else {
            return ConfigNodeType.ENTRY;
        }
    }
    public void setNodeType(ConfigNodeType type){
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("<").append(name);
        for (ConfigValueNode attribute : attributes){
            result.append(" ").append(attribute.toString()).append(" ");
        }
        result.append(">");
        result.append(value).append("</").append(name).append(">\n");
        return result.toString();
    }

    @Override
    public AbstractConfigNode getParentNode() {
        return this.parentNode;
    }

    public void setEditable(boolean isEditable){
        super.setEditable(isEditable);
        for (ConfigValueNode attribute : getAttributes()){
            attribute.setEditable(isEditable);
        }

    }
}