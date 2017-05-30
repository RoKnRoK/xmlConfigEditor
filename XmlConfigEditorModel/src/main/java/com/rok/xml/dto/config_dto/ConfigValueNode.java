package com.rok.xml.dto.config_dto;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Created by RoK on 26.06.2015.
 * All rights reserved =)
 */
@XmlJavaTypeAdapter(AnyTypeAdapter.class)
public interface ConfigValueNode extends ConfigNode {


    String getValue();
    void setValue(String value);

    String getOriginalValue();

    @JsonIgnore
    boolean isChanged();
}
