package com.rok.xml.ejb;

import com.rok.xml.api.XmlConfigModifier;
import com.rok.xml.dto.config_dto.ConfigModificationInfo;
import com.rok.xml.modifier.FSXmlConfigModifier;
import com.rok.xml.timer.XmlLockCanceller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
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


    private static final Logger logger = LoggerFactory.getLogger(XmlConfigEditorImpl.class.getName());


    @EJB
    private XmlLockCanceller canceller;

    public XmlConfigEditorImpl() {
    }

    private XmlConfigModifier getXmlConfigModifier() {
        return new FSXmlConfigModifier(FILE_NAME);
    }

    @Override
    public ConfigModificationInfo getConfigModificationInfo() {

        //todo: filename as @Resource

        ConfigModificationInfo config = getXmlConfigModifier().getConfig();
        canceller.startLockValidityTimer(config);
        return config;
    }


    @Override
    public boolean saveConfigBlock(ConfigModificationInfo configModificationInfo) {
        return getXmlConfigModifier().saveConfig(configModificationInfo);
    }

    @Override
    public void cancelConfigEditing(ConfigModificationInfo configModificationInfo) {
        getXmlConfigModifier().cancelConfigEditing(configModificationInfo);
    }
}
