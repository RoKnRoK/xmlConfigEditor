package com.rok.xml.ejb;

import com.rok.xml.config_dto.ConfigBlock;
import com.rok.xml.modifier.FSXmlConfigModifier;
import com.rok.xml.api.XmlConfigModifier;

import javax.ejb.Stateful;

/**
 * Created by RoK on 10.07.2015.
 * All rights reserved =)
 */
@Stateful(name = "XmlConfigEditorEJB")
public class XmlConfigEditorImpl implements XmlConfigEditorRemote {

    private XmlConfigModifier xmlConfigModifier;

    public XmlConfigEditorImpl() {

    }


    @Override
    public ConfigBlock getConfigBlock() {

        xmlConfigModifier = new FSXmlConfigModifier("environment_config.xml");
        return xmlConfigModifier.getConfig();
    }

    @Override
    public boolean saveConfigBlock(ConfigBlock configBlock) {
        return xmlConfigModifier.saveConfig(configBlock);
    }
}
