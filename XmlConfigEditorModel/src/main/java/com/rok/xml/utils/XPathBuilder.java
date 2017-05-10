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
        List<ConfigValueNode> nodeAttributes = configNode.getNodeAttributes();
        String myXPath = "/" + configNodeName + "[";

        for (ConfigValueNode nodeAttribute : nodeAttributes) {
            myXPath += "@" + nodeAttribute.getName() + "=\'" + nodeAttribute.getValue() + "\' and";
        }
        if (myXPath.indexOf("and") > 0) {
            myXPath = myXPath.substring(0, myXPath.length() - 4);
        }
        myXPath += "]";
        myXPath = myXPath.replace("[]", "");

        myXPath = createXPath(configNode.getParentNode()) + myXPath;
        return myXPath;
    }
}
