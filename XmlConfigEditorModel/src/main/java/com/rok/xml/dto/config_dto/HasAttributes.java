package com.rok.xml.dto.config_dto;

import java.util.List;

/**
 * Created by roman.kulikov on 5/31/2017.
 * All rights reserved =D
 */
public interface HasAttributes {

    List<ConfigValueNode> getAttributes();
    void setAttributes(List<ConfigValueNode> attributes);
}
