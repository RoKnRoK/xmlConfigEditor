package com.rok.xml.utils;

import com.rok.xml.Constants;
import com.rok.xml.config_dto.*;
import com.rok.xml.settings.ApplicationSettings;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by RoK on 21.06.2015.
 * All rights reserved =)
 */
public class DomNodeToConfigBlockConverter {
    private final static List<Short> relevantNodeTypes = new ArrayList<Short>(Collections.singletonList(Node.ELEMENT_NODE));


    public ConfigNode createConfigNode(Node node, AbstractConfigNode parent) {

        NodeList childNodes = node.getChildNodes();

        if (childNodes.getLength() == 0) {
            if ( isEmptyAttributes(node)) { return createConfigEntry(node, parent);}
            else {return  createEmptyConfigBlockWithAttributes(node, parent);}
        }
        // <name>value</name>
        if (childNodes.getLength() == 1 && node.getFirstChild().getNodeType() == Node.TEXT_NODE) {

            return createConfigEntry(node, parent);

        }

        ConfigBlock configBlock = createEmptyConfigBlockWithAttributes(node, parent);

        for (int i = 0; i < childNodes.getLength(); i++) {
            Node childNode = childNodes.item(i);
            if (!relevantNodeTypes.contains(childNode.getNodeType())) {continue;}

            if (node.getNodeValue() == null) {
                configBlock.addChildNode(createConfigNode(childNode, configBlock));
            }

        }
        return configBlock;
    }

    private ConfigBlock createEmptyConfigBlockWithAttributes(Node node, AbstractConfigNode parent) {
        ConfigBlock configBlock = new ConfigBlock(node.getNodeName(), parent);
        configBlock.setNodeAttributes(defineAttributes(node));
        return configBlock;
    }

    private List<ConfigValueNode> defineAttributes(Node node) {
        List<ConfigValueNode> result = new ArrayList<>();
        NamedNodeMap attributes = node.getAttributes();
        for (int i = 0; i< attributes.getLength(); i++) {
            Node attribute = attributes.item(i);
            result.add(new ConfigNodeAttribute(attribute.getNodeName(), attribute.getNodeValue()));
        }
        return result;
    }

    private ConfigEntry createConfigEntry(Node node, AbstractConfigNode parentNode) {

        ConfigEntry configEntry;

        String textContent = node.getTextContent();

        String normalizedTextContent = normalize(textContent).trim();

        boolean isBoolean =
                Boolean.TRUE.toString().equals(normalizedTextContent) ||
                Boolean.FALSE.toString().equals(normalizedTextContent);
        if (isBoolean) {
            Boolean value = Boolean.valueOf(normalizedTextContent);
            configEntry = new ConfigBooleanEntry(node.getNodeName(), value, parentNode);
        }
        else {
            configEntry = new ConfigEntry(node.getNodeName(), normalizedTextContent, parentNode);
        }

        List<ConfigValueNode> configBlockAttributes = defineAttributes(node);
        configEntry.setNodeAttributes(configBlockAttributes);
        return configEntry;
    }

    private boolean isEmptyAttributes(Node node){
        List<ConfigValueNode> attributes = defineAttributes(node);
        if ( attributes == null || attributes.isEmpty()) {return true;}

        if (ApplicationSettings.isDisplayNamesEditingEnabled()){return false;}
        else {
            return attributes.size() == 1 && Constants.DISPLAY_NAME.equals(attributes.get(0).getName());
        }
    }

    private String normalize(String textContent) {
        String result;
        result = textContent.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;");
        return result;
    }
}
