package com.rok.xml.modifier;

import com.rok.xml.api.ConfigBackuper;
import com.rok.xml.api.ConfigLocker;
import com.rok.xml.api.XmlConfigModifier;
import com.rok.xml.backuper.StubConfigBackuper;
import com.rok.xml.config_dto.*;
import com.rok.xml.locker.ByFSBackupLocker;
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
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * Created by RoK on 11.07.2015.
 * All rights reserved =)
 */
public abstract class CommonConfigModifier implements XmlConfigModifier {
    private final Logger logger = LoggerFactory.getLogger(CommonConfigModifier.class);

    protected File xmlConfig;
    protected ConfigLocker configLocker;
    protected ConfigBackuper configBackuper;


    private ConfigBlock parseConfig() {
        try {

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlConfig);

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
    public ConfigBlock getConfig() {
        configLocker.tryLockConfig();
        boolean isConfigLockedBySomeoneElse = configLocker.isConfigLockedBySomeoneElse();
        boolean backupSuccessful = false;
        if (!isConfigLockedBySomeoneElse) {
            backupSuccessful = configBackuper.backupConfig();
        }
        ConfigBlock configNode = parseConfig();
        configNode.setEditable(backupSuccessful);

        return configNode;
    }

    public boolean saveConfig(ConfigBlock configBlock) {
        try {
            configLocker.unlockConfig();

            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(new FileInputStream(xmlConfig));
            XPathFactory factory = XPathFactory.newInstance();
            XPath xpath = factory.newXPath();

            XPathBuilder xPathBuilder = new XPathBuilder();
            List<ConfigValueNode> changedNodes = configBlock.getChangedValueNodes();
            for (ConfigValueNode changedNode : changedNodes) {
                switch (changedNode.getNodeType()) {
                    case ENTRY:
                    case ENTRY_WITH_ATTRS: {
                        String expression = xPathBuilder.createXPath((AbstractConfigNode) changedNode);
                        Node node = (Node) xpath.evaluate(expression, document, XPathConstants.NODE);
                        node.setTextContent(changedNode.getValue());
                    }
                    break;
                    case ATTRIBUTE:
                        break;

                }
            }
            DOMSource source = new DOMSource(document);
            StreamResult outputFile = new StreamResult(xmlConfig);
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, outputFile);

           configBackuper.deleteBackup();

        } catch (Exception e) {
            logger.error("Save configuration failed, reason: {}", e.getMessage(), e);
            return false;
        }
        return true;
    }

    @Override
    public void setBackuper(ConfigBackuper backuper) {
        if (backuper == null) {this.configBackuper = new StubConfigBackuper();}
        else  {this.configBackuper = backuper;}
    }

    @Override
    public void setLocker(ConfigLocker locker) {
        if (locker == null) {this.configLocker = new StubConfigLocker();}
        else if (locker instanceof ByFSBackupLocker) {
            setBackuper(null);
            this.configLocker = locker;
        }
        else {
            this.configLocker = locker;
        }
    }


}
