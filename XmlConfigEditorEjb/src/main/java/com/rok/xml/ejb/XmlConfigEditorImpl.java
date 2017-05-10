package com.rok.xml.ejb;

import com.rok.xml.Constants;
import com.rok.xml.dto.config_dto.ConfigModificationInfo;
import com.rok.xml.modifier.FSXmlConfigModifier;
import com.rok.xml.api.XmlConfigModifier;
import com.rok.xml.timer.XmlLockCanceller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.ejb.*;

import java.io.Serializable;

import static com.rok.xml.Constants.FILE_NAME;

/**
 * Created by RoK on 10.07.2015.
 * All rights reserved =)
 */
@Stateless(name = "XmlConfigEditorEJB")
@LocalBean
public class XmlConfigEditorImpl implements XmlConfigEditorLocal, XmlConfigEditorRemote {


    private XmlConfigModifier xmlConfigModifier;
    private static final Logger logger = LoggerFactory.getLogger(XmlConfigEditorImpl.class);


    @EJB
    private XmlLockCanceller canceller;

    public XmlConfigEditorImpl() {}


    @Override
    public ConfigModificationInfo getConfigModificationInfo() {

        //todo: filename as @Resource
        xmlConfigModifier = new FSXmlConfigModifier(FILE_NAME);
        ConfigModificationInfo config = xmlConfigModifier.getConfig();
        canceller.startLockValidityTimer(config);
        return config;
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
