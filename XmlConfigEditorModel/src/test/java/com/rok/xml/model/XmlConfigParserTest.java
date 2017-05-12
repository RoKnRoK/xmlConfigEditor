package com.rok.xml.model;

import com.rok.xml.api.XmlConfigModifier;
import com.rok.xml.dto.config_dto.ConfigBlock;
import com.rok.xml.dto.config_dto.ConfigEntry;
import com.rok.xml.dto.config_dto.ConfigModificationInfo;
import com.rok.xml.modifier.FSXmlConfigModifier;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.rok.xml.Constants.FILE_NAME;

/**
 * Created by RoK on 21.06.2015.
 * All rights reserved =)
 */
public class XmlConfigParserTest {

//    public static final String FILE_NAME = "C:\\Temp\\standart.xml";

    @DataProvider(parallel = true, name = "test1")
    public Object[][] concurrencyData() {
        return new Object[][]{
                {"newValue1", 111},
                {"newValue2", 222},
        };
    }

    @Test(dataProvider = "test1", threadPoolSize = 1, invocationCount = 10)
    public void doTest(String newValue, int i) {

        XmlConfigModifier configModifier = new FSXmlConfigModifier(FILE_NAME);
        ConfigModificationInfo configModificationInfo = configModifier.getConfig();
        System.out.println("Config lock: " + configModificationInfo.getLock());
        ConfigBlock configBlock = configModificationInfo.getConfigBlock();
        System.out.println("Config editable: " + configBlock.isEditable());
        ConfigBlock childNode = (ConfigBlock) configBlock.getChildNode(0);
        ConfigEntry childEntry = (ConfigEntry) childNode.getChildNode(3);
        if (childEntry.isEditable()) {
            System.out.println("Setting "+ newValue);
            childEntry.setValue(newValue);
        }
        System.out.println("Config saved: " + configModifier.saveConfig(configModificationInfo));

        configModifier.cancelConfigEditing(configModificationInfo);
        System.out.println("===================================");
        System.out.println();
    }

}
