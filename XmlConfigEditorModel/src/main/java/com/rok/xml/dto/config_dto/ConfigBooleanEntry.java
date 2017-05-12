package com.rok.xml.dto.config_dto;

/**
 * Created by RoK on 27.06.2015.
 * All rights reserved =)
 */
public class ConfigBooleanEntry extends ConfigEntry{
    private static final long serialVersionUID = -6812828214278532402L;

    @SuppressWarnings("unused")
    public ConfigBooleanEntry() {

    }

    @Override
    public ConfigNodeType getNodeType() {

        return ConfigNodeType.BOOLEAN_ENTRY;
    }

    public ConfigBooleanEntry(String configEntryName, boolean entryBooleanValue, AbstractConfigNode parentNode){
        super(configEntryName, Boolean.toString(entryBooleanValue), parentNode);

    }

    @Override
    public String getValue() {
        return super.getValue();
    }

    @Override
    public void setValue(String configEntryValue) {
        super.setValue(configEntryValue);
    }

    public boolean getBooleanValue(){
        return stringToBoolean(configEntryValue);
    }
    public void setBooleanValue(boolean entryValue) {
        setValue(booleanToString(entryValue));

    }

    private String booleanToString(boolean value){
        return Boolean.toString(value);
    }

    private boolean stringToBoolean(String value){
        if (Boolean.TRUE.toString().equals(value)) return true;
        if (Boolean.FALSE.toString().equals(value)) return false;
        throw new IllegalStateException("value can be only \"true\" or \"false\"");
    }

}
