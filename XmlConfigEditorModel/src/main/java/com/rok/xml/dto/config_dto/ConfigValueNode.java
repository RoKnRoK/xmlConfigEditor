package com.rok.xml.dto.config_dto;

/**
 * Created by RoK on 26.06.2015.
 * All rights reserved =)
 */
public interface ConfigValueNode extends ConfigNode {


    String getValue();
    void setValue(String value);

    String getOriginalValue();

    boolean isChanged();
}
