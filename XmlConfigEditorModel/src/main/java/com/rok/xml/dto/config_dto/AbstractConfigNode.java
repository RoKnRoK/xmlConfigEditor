package com.rok.xml.dto.config_dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rok.xml.Constants;
import com.rok.xml.settings.ApplicationSettings;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by RoK on 27.06.2015.
 * All rights reserved =)
 */

public abstract class AbstractConfigNode implements ConfigNode {
    private static final long serialVersionUID = -3926678108830977911L;
    String configNodeName;
    private String configNodeDisplayName;

    List<ConfigValueNode> configNodeAttributes = new ArrayList<>();
    private boolean isEditable;
    @JsonIgnore
    AbstractConfigNode parentNode;

    public void setName(String configEntryName) {
        this.configNodeName = configEntryName;
    }
    public String getName() {
        return this.configNodeName;
    }


    @Override
    @XmlElements({
            @XmlElement(name = "entry", type=ConfigEntry.class),
            @XmlElement(name = "booleanEntry", type=ConfigBooleanEntry.class),
            @XmlElement(name = "attribute", type=ConfigNodeAttribute.class)
    })
    @XmlElementWrapper
    public List<ConfigValueNode> getNodeAttributes() {
        List<ConfigValueNode> result = new ArrayList<>();
        result.addAll(configNodeAttributes);
        ConfigValueNode displayNameAttribute = getDisplayNameAttribute();
        if (!ApplicationSettings.isDisplayNamesEditingEnabled()) {
            result.remove(displayNameAttribute);
        }
        return result;
    }

    public void setNodeAttributes(List<ConfigValueNode> attributes) {
        this.configNodeAttributes.addAll(attributes);
        ConfigValueNode displayNameAttribute = getDisplayNameAttribute();
        configNodeDisplayName = displayNameAttribute == null ? configNodeName : displayNameAttribute.getValue();

    }

    @Override
    public String getDisplayName() {
        if (configNodeDisplayName != null) {return configNodeDisplayName;}
        return configNodeName;
    }

    private ConfigValueNode getDisplayNameAttribute(){
        for (ConfigValueNode configAttribute : configNodeAttributes) {
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
