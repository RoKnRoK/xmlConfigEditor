package com.rok.xml.modifier;

import com.rok.xml.backuper.FSConfigBackuper;
import com.rok.xml.retriever.FSFileRetriever;
import com.rok.xml.api.FileRetriever;
import com.rok.xml.locker.FSConfigLocker;

import java.io.*;

/**
 * Created by RoK on 27.06.2015.
 * All rights reserved =)
 */
public class FSXmlConfigModifier extends CommonConfigModifier{

    public FSXmlConfigModifier(String fileName) {
        FileRetriever fileRetriever = new FSFileRetriever();
        this.xmlConfig = (File) fileRetriever.retrieveFile(fileName);
        if (xmlConfig == null) {
            throw new IllegalStateException("Не найден файл настроек "+fileName);
        }
        configLocker = new FSConfigLocker(xmlConfig);
        configBackuper = new FSConfigBackuper(xmlConfig);
    }

    @Override
    public Object getConfigAsObject() {
        return this.xmlConfig;
    }
}
