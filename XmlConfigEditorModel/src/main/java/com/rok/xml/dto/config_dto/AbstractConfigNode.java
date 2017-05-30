package com.rok.xml.dto.config_dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.rok.xml.Constants;
import com.rok.xml.settings.ApplicationSettings;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by RoK on 27.06.2015.
 * All rights reserved =)
 */

public abstract class AbstractConfigNode implements ConfigNode {
    private static final long serialVersionUID = -3926678108830977911L;

    String name;
    private String displayName;
    private boolean isEditable;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    List<ConfigValueNode> attributes = new ArrayList<>();
    AbstractConfigNode parentNode;

    public void setName(String configEntryName) {
        this.name = configEntryName;
    }
    public String getName() {
        return this.name;
    }


    @Override
    @XmlElement(name = "attribute", type=ConfigNodeAttribute.class)
    @XmlElementWrapper
    @JsonUnwrapped
    public List<ConfigValueNode> getAttributes() {
        List<ConfigValueNode> result = new ArrayList<>();
        result.addAll(attributes);
        ConfigValueNode displayNameAttribute = getDisplayNameAttribute();
        if (!ApplicationSettings.isDisplayNamesEditingEnabled()) {
            result.remove(displayNameAttribute);
        }
        return result;
    }

    public void setAttributes(List<ConfigValueNode> attributes) {
        this.attributes.addAll(attributes);
        ConfigValueNode displayNameAttribute = getDisplayNameAttribute();
        displayName = displayNameAttribute == null ? name : displayNameAttribute.getValue();

    }

    @Override
    public String getDisplayName() {
        return displayName != null ? displayName : name;
    }

    private ConfigValueNode getDisplayNameAttribute(){
        for (ConfigValueNode configAttribute : attributes) {
            if (!Constants.DISPLAY_NAME.equals(configAttribute.getName())) {
                continue;
            }
            return configAttribute;
        }
        return  null;
    }


    public abstract AbstractConfigNode getParentNode() ;


    @Override
    public void setEditable(boolean isEditable) {
        this.isEditable = isEditable;
    }

    @Override
    public boolean isEditable() {
        return this.isEditable;
    }
}
