package com.rok.xml.config_dto;

import java.io.Serializable;

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
