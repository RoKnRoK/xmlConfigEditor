package com.rok.xml.ejb;

import com.rok.xml.config_dto.ConfigBlock;
import com.rok.xml.config_dto.ConfigModificationInfo;
import com.rok.xml.modifier.FSXmlConfigModifier;
import com.rok.xml.api.XmlConfigModifier;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import static com.rok.xml.Constants.FILE_NAME;

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
    public ConfigModificationInfo getConfigModificationInfo() {

        //todo: filename as @Resource
        xmlConfigModifier = new FSXmlConfigModifier(FILE_NAME);
        return xmlConfigModifier.getConfig();
    }

    @Override
    public boolean saveConfigBlock(ConfigModificationInfo configModificationInfo) {
        return xmlConfigModifier.saveConfig(configModificationInfo);
    }

    @Override
    public void cancelConfigEditing(ConfigModificationInfo configModificationInfo) {
        xmlConfigModifier.cancelConfigEditing(configModificationInfo);
    }
}
