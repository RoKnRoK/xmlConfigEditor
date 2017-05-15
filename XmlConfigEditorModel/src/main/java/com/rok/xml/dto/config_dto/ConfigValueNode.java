package com.rok.xml.dto.config_dto;

import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * Created by RoK on 26.06.2015.
 * All rights reserved =)
 */
@XmlSeeAlso({
        ConfigBooleanEntry.class,
        ConfigEntry.class,
        ConfigNodeAttribute.class, })
public interface ConfigValueNode extends ConfigNode {


    String getValue();
    void setValue(String value);

    String getOriginalValue();

    boolean isChanged();
}
