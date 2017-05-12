package com.rok.xml.modifier;

import com.rok.xml.api.ConfigBackuper;
import com.rok.xml.api.ConfigLocker;
import com.rok.xml.api.XmlConfigModifier;
import com.rok.xml.backuper.StubConfigBackuper;
import com.rok.xml.dto.config_dto.*;
import com.rok.xml.dto.LockInfo;
import com.rok.xml.locker.StubConfigLocker;
import com.rok.xml.utils.DomNodeToConfigBlockConverter;
import com.rok.xml.utils.XPathBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * Created by RoK on 11.07.2015.
 * All rights reserved =)
 */
public abstract class CommonConfigModifier implements XmlConfigModifier {
    private final Logger logger = LoggerFactory.getLogger(CommonConfigModifier.class.getName());

    protected File xmlConfig;
    protected ConfigLocker configLocker;
    protected ConfigBackuper configBackuper;


    private ConfigBlock parseConfig() {
        try {

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = null;
            //todo: try to get rid of it
            for (int i = 0; i< 10; i++) {
                try {
                    doc = dBuilder.parse(xmlConfig);
                } catch (SAXException | IOException e) {
                    logger.error("Try №{}: cannot parse config, {}", i, e.getMessage());
                    Thread.sleep(250);
                }
                if (doc != null) {
                    break;
                }
            }
            if (doc == null) {
                throw new IllegalStateException("Cannot parse config because it's being read by someone");
            }

            //optional, but recommended
            //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            Element root = doc.getDocumentElement();
            root.normalize();
            DomNodeToConfigBlockConverter converter = new DomNodeToConfigBlockConverter();

            ConfigNode configBlock = converter.createConfigNode(root, null);
            configBlock.setNodeType(ConfigNodeType.ROOT_BLOCK);

            return (ConfigBlock) configBlock;
        } catch (Exception e) {
            logger.error("Error occured while parsing xml {}: {}", xmlConfig.getName(), e.getMessage(), e);
            return new ConfigBlock("undefined", null);
        }
    }

    @Override
    public ConfigModificationInfo getConfig() {
        logger.trace("Trying to lock configuration file");
        LockInfo lockInfo = configLocker.tryLockConfig();
        Serializable lockObject = lockInfo.getLockObject();
        boolean backupSuccessful = false;
        if (lockObject != null) {
            backupSuccessful = configBackuper.backupConfig();
        }
        logger.trace("Trying to parse configuration file");
        ConfigBlock configNode = parseConfig();
        configNode.setEditable(backupSuccessful);

        return new ConfigModificationInfo(configNode, lockInfo);
    }

    public boolean saveConfig(ConfigModificationInfo configModificationInfo) {
        if (configModificationInfo.getLock() == null){
            logger.warn("Config is not locked by me, skipping save");
            return false;
        }
        try {

            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(new FileInputStream(xmlConfig));
            XPathFactory factory = XPathFactory.newInstance();
            XPath xpath = factory.newXPath();

            XPathBuilder xPathBuilder = new XPathBuilder();
            List<ConfigValueNode> changedNodes = configModificationInfo.getConfigBlock().getChangedValueNodes();
            for (ConfigValueNode changedNode : changedNodes) {
                switch (changedNode.getNodeType()) {
                    case ENTRY:
                    case BOOLEAN_ENTRY:{
                        String expression = xPathBuilder.createXPath((AbstractConfigNode) changedNode);
                        Node node = (Node) xpath.evaluate(expression, document, XPathConstants.NODE);
                        node.setTextContent(changedNode.getValue());
                    }
                    break;
                    case ATTRIBUTE:
                        break;

                }
            }
//            String fileCopyName = xmlConfig.getAbsolutePath().replace(".xml", "_new.xml");
            DOMSource source = new DOMSource(document);
//            File fileCopy = new File(fileCopyName);
//            StreamResult outputFile = new StreamResult(fileCopy);
            StreamResult outputFile = new StreamResult(xmlConfig);
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, outputFile);

            //todo: theoretically in period of time between this lines someone can open config for editing
            boolean unlocked = configLocker.unlockConfig(configModificationInfo.getLock());
//            Files.move(fileCopy.toPath(), xmlConfig.toPath(), StandardCopyOption.ATOMIC_MOVE);
            if (unlocked) {
                configBackuper.deleteBackup();
            }

        } catch (Exception e) {
            logger.error("Save configuration failed, reason: {}", e.getMessage(), e);
            return false;
        }
        return true;
    }

    @Override
    public void cancelConfigEditing(ConfigModificationInfo configModificationInfo) {
        logger.trace("Cancelling editing of config");
        boolean unlocked = configLocker.unlockConfig(configModificationInfo.getLock());
        if (unlocked) {
            configBackuper.deleteBackup();
        }
        logger.trace("Cancelling finished");
    }

    @Override
    public void setBackuper(ConfigBackuper backuper) {
        if (backuper == null) {
            this.configBackuper = new StubConfigBackuper();
        } else {
            this.configBackuper = backuper;
        }
    }

    @Override
    public void setLocker(ConfigLocker locker) {
        if (locker == null) {
            this.configLocker = new StubConfigLocker();
        } else {
            this.configLocker = locker;
        }
    }


}
