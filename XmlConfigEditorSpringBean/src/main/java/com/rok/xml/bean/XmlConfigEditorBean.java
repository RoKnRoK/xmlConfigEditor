package com.rok.xml.bean;

import com.rok.xml.api.XmlConfigModifier;
import com.rok.xml.dto.config_dto.ConfigModificationInfo;
import com.rok.xml.modifier.FSXmlConfigModifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

import static com.rok.xml.Constants.FILE_NAME;

/**
 * Created by roman.kulikov on 6/29/2017.
 * All rights reserved =D
 */
@Service
@Scope("prototype")
public class XmlConfigEditorBean implements XmlConfigEditor{

    private static final Logger logger = LoggerFactory.getLogger(XmlConfigEditorBean.class.getName());
    private XmlConfigModifier fsXmlConfigModifier;

    @PostConstruct
    public void init() {
        fsXmlConfigModifier = new FSXmlConfigModifier(FILE_NAME);
        logger.debug("Created configModifier: {}", fsXmlConfigModifier);
    }

//    private XmlLockCanceller canceller;

    @Override
    public ConfigModificationInfo getConfigModificationInfo() {
        ConfigModificationInfo config = fsXmlConfigModifier.getConfig();
//        canceller.startLockValidityTimer(config);
        return config;
    }


    @Override
    public boolean saveConfigBlock(ConfigModificationInfo configModificationInfo) {
        return fsXmlConfigModifier.saveConfig(configModificationInfo);
    }

    @Override
    public void cancelConfigEditing(ConfigModificationInfo configModificationInfo) {
        fsXmlConfigModifier.cancelConfigEditing(configModificationInfo);
    }
}
