package com.rok.xml.utils;

import com.rok.xml.Constants;
import com.rok.xml.dto.config_dto.*;
import com.rok.xml.settings.ApplicationSettings;
import org.apache.commons.lang.StringEscapeUtils;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.rok.xml.dto.config_dto.ConfigNodeType.*;

/**
 * Created by RoK on 21.06.2015.
 * All rights reserved =)
 */
public class DomNodeToConfigBlockConverter {
    private final static List<Short> relevantNodeTypes = Collections.singletonList(Node.ELEMENT_NODE);


    public ConfigNode createConfigNode(Node node, AbstractConfigNode parent) {

        List<ConfigValueNode> attributes = createNodeAttributes(node);
        AbstractConfigNode result = null;
        NodeList childNodes = node.getChildNodes();

        //<name></name>
        boolean hasNoChildren = childNodes.getLength() == 0;
        //<name displayName="foo"></name> or <name></name>
        boolean hasNoAttrsOrOnlyDisplayNameAttr = isEmptyAttributes(attributes);
        boolean isEmptyEntry = hasNoChildren && hasNoAttrsOrOnlyDisplayNameAttr;
        boolean isNameValueNode = childNodes.getLength() == 1 && node.getFirstChild().getNodeType() == Node.TEXT_NODE; // <name>value</name>

        ConfigNodeType typeToCreate = (isEmptyEntry || isNameValueNode) ? ENTRY : BLOCK;
        switch (typeToCreate) {
            case ENTRY: {
                result = createConfigEntry(node, parent);
            }
            break;
            case BLOCK: {
                ConfigBlock configBlock = createEmptyConfigBlock(node, parent);
                result = addChildrenToBlock(configBlock, node, childNodes);
            }
        }
        /*if (hasNoChildren) {

            if (hasNoAttrsOrOnlyDisplayNameAttr) {
                result = createConfigEntry(node, parent);
            } else {
                //<name attr1="d"></name>
                result = createEmptyConfigBlock(node, parent);
            }
        }

        if (isNameValueNode) {
            result = createConfigEntry(node, parent);
        }

        ConfigBlock configBlock = createEmptyConfigBlock(node, parent);*/


//        result = result == null ? configBlock : result;
        result.setAttributes(attributes);
        return result;
    }

    private ConfigBlock addChildrenToBlock(ConfigBlock configBlock, Node node, NodeList childNodes) {
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node childNode = childNodes.item(i);
            if (!relevantNodeTypes.contains(childNode.getNodeType())) {
                continue;
            }

//            if (node.getNodeValue() == null) {
                configBlock.addChildNode(createConfigNode(childNode, configBlock));
//            }

        }
        return configBlock;
    }

    private ConfigBlock createEmptyConfigBlock(Node node, AbstractConfigNode parent) {
        return new ConfigBlock(node.getNodeName(), parent);
    }

    private List<ConfigValueNode> createNodeAttributes(Node node) {
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

        String normalizedTextContent = StringEscapeUtils.unescapeXml(textContent).trim();

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

        return configEntry;
    }

    private boolean isEmptyAttributes(List<ConfigValueNode> attributes){
        if ( attributes == null || attributes.isEmpty()) {return true;}

        if (ApplicationSettings.isDisplayNamesEditingEnabled()){
            return false;
        } else {
            boolean onlyOneAttribute = attributes.size() == 1;
            boolean firstAttrIsDisplayName = Constants.DISPLAY_NAME.equals(attributes.get(0).getName());
            return onlyOneAttribute && firstAttrIsDisplayName;
        }
    }

}
