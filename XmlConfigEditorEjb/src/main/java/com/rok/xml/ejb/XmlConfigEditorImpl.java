package com.rok.xml.ejb;

import com.rok.xml.config_dto.ConfigBlock;
import com.rok.xml.modifier.FSXmlConfigModifier;
import com.rok.xml.api.XmlConfigModifier;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Created by RoK on 10.07.2015.
 * All rights reserved =)
 */
@Stateless(name = "XmlConfigEditorEJB")
@LocalBean
public class XmlConfigEditorImpl implements XmlConfigEditorLocal, XmlConfigEditorRemote {

    private XmlConfigModifier xmlConfigModifier;

    public XmlConfigEditorImpl() {

    }


    @Override
    public ConfigBlock getConfigBlock() {

        //todo: filename as @Resource
        xmlConfigModifier = new FSXmlConfigModifier("D:/Temp/environment_config.xml");
        return xmlConfigModifier.getConfig();
    }

    @Override
    public boolean saveConfigBlock(ConfigBlock configBlock) {
        return xmlConfigModifier.saveConfig(configBlock);
    }
}
