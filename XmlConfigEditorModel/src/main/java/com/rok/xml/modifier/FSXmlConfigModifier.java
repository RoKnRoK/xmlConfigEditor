package com.rok.xml.modifier;

import com.rok.xml.backuper.FSConfigBackuper;
import com.rok.xml.locker.ByLockFileLocker;
import com.rok.xml.retriever.FSConfigRetriever;
import com.rok.xml.api.ConfigRetriever;

import java.io.*;

/**
 * Created by RoK on 27.06.2015.
 * All rights reserved =)
 */
public class FSXmlConfigModifier extends CommonConfigModifier {

    public FSXmlConfigModifier(String fileName) {
        ConfigRetriever configRetriever = new FSConfigRetriever();
        this.xmlConfig = (File) configRetriever.retrieveConfig(fileName);
        if (xmlConfig == null) {
            throw new IllegalStateException("Configuration file not found: " + fileName);
        }
        configLocker = new ByLockFileLocker(xmlConfig);
        configBackuper = new FSConfigBackuper(xmlConfig);
    }

}
