package com.rok.xml.utils;

import com.rok.xml.dto.config_dto.AbstractConfigNode;
import com.rok.xml.dto.config_dto.ConfigValueNode;

import java.util.List;

/**
 * Created by RoK on 28.06.2015.
 * All rights reserved =)
 */
public class XPathBuilder {

    public String createXPath(AbstractConfigNode configNode) {
        if (configNode == null) {
            return "";
        }
        String configNodeName = configNode.getName();
        List<ConfigValueNode> nodeAttributes = configNode.getAttributes();
        StringBuilder myXPath = new StringBuilder("/" + configNodeName + "[");

        for (ConfigValueNode nodeAttribute : nodeAttributes) {
            myXPath.append("@").append(nodeAttribute.getName()).append("=\'").append(nodeAttribute.getValue()).append("\' and");
        }
        if (myXPath.indexOf("and") > 0) {
            myXPath = new StringBuilder(myXPath.substring(0, myXPath.length() - 4));
        }
        myXPath.append("]");
        myXPath = new StringBuilder(myXPath.toString().replace("[]", ""));

        myXPath.insert(0, createXPath(configNode.getParentNode()));
        return myXPath.toString();
    }
}
