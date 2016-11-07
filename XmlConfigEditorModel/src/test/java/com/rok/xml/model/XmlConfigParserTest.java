package com.rok.xml.model;

import com.rok.xml.config_dto.ConfigBlock;
import com.rok.xml.config_dto.ConfigEntry;
import com.rok.xml.config_dto.ConfigNode;
import com.rok.xml.locker.ByFSBackupLocker;
import com.rok.xml.modifier.FSXmlConfigModifier;
import com.rok.xml.api.XmlConfigModifier;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;

/**
 * Created by RoK on 21.06.2015.
 * All rights reserved =)
 */
public class XmlConfigParserTest {

    @DataProvider(parallel = true, name = "test1")
    public Object[][] concurrencyData() {
        return new Object[][]{
                {"newValue1", 111},
                {"newValue2", 222},
        };
    }

    @Test(dataProvider = "test1", threadPoolSize = 1, invocationCount = 1)
    public void doTest(String newValue, int i) {
        XmlConfigModifier configModifier = new FSXmlConfigModifier("D:\\Work\\test\\environment_config.xml");
       // ByFSBackupLocker locker = new ByFSBackupLocker((File) configModifier.getConfigAsObject());
      //  configModifier.setLocker(locker);
        ConfigBlock configBlock = configModifier.getConfig();
        ConfigBlock childNode = (ConfigBlock) configBlock.getChildNode(0);
        ConfigEntry childEntry = (ConfigEntry) childNode.getChildNode(1);
        if (childEntry.isEditable()) {
            System.out.println("set");
            childEntry.setValue(newValue);
        }
        System.out.println(configModifier.saveConfig(configBlock));
    }
}
