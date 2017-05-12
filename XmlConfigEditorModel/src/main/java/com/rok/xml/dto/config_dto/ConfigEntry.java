package com.rok.xml.dto.config_dto;

import java.util.Objects;

/**
 * Created by RoK on 21.06.2015.
 * All rights reserved =)
 */
public class  ConfigEntry extends AbstractConfigNode implements ConfigValueNode {

    private static final long serialVersionUID = -4269070863916591307L;

    protected String configEntryValue;

    private String configEntryOriginalValue;

    public ConfigEntry(String configEntryName, String configEntryValue, AbstractConfigNode parentNode) {
        this.configNodeName = configEntryName;
        this.configEntryValue = configEntryValue;
        this.configEntryOriginalValue = configEntryValue;
        this.parentNode = parentNode;

    }

    public ConfigEntry() {
        this.configNodeName = "defaultEntryName";
        this.configEntryValue = this.configEntryOriginalValue = "defaultEntryValue";
    }

    public String getValue() {
        return configEntryValue;
    }
    public void setValue(String configEntryValue) {
        this.configEntryValue = configEntryValue;
    }

    public String getOriginalValue() {
        return configEntryOriginalValue;
    }

    @Override
    public boolean isChanged() {
        return !Objects.equals(getOriginalValue(), getValue());
    }


    public ConfigNodeType getNodeType(){
        if (getNodeAttributes() != null && !getNodeAttributes().isEmpty()) {
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
        String result ="";
        result += "<"+ configNodeName;
        for (ConfigValueNode attribute : configNodeAttributes){
            result +=" "+attribute.toString()+" ";
        }
        result +=">";
        result +=configEntryValue+"</"+ configNodeName +">\n";
        return result;
    }

    @Override
    public AbstractConfigNode getParentNode() {
        return this.parentNode;
    }
    @Override
    public void setParentNode(AbstractConfigNode parentNode) {
        this.parentNode = parentNode;
    }

    public void setEditable(boolean isEditable){
        super.setEditable(isEditable);
        for (ConfigValueNode attribute : getNodeAttributes()){
            attribute.setEditable(isEditable);
        }

    }
}