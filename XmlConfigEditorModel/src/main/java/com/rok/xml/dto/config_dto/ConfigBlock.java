package com.rok.xml.dto.config_dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by RoK on 21.06.2015.
 * All rights reserved =)
 */
public class ConfigBlock extends AbstractConfigNode implements ConfigNode, HasChildNodes {

    private static final long serialVersionUID = -2968138039440394332L;


    private List<ConfigNode> allChildConfigBlocks = new ArrayList<>();
    private ConfigNodeType nodeType;


    private ConfigBlock() {
        this.configNodeName = "defaultBlockName";
        this.nodeType = ConfigNodeType.BLOCK;
    }
    public ConfigBlock(String configBlockName, AbstractConfigNode parentNode) {
        this();
        this.configNodeName = configBlockName;
        this.parentNode = parentNode;
    }


    @Override
    public ConfigNodeType getNodeType() {
        return this.nodeType;
    }

    public void setNodeType(ConfigNodeType configNodeType) {
        this.nodeType = configNodeType;
    }

    @Override
    public ConfigNode getChildNode(Integer index) {
        return allChildConfigBlocks.get(index);
    }

    @XmlElements({
            @XmlElement(name = "entry", type=ConfigEntry.class),
            @XmlElement(name = "block", type=ConfigBlock.class),
            @XmlElement(name = "booleanEntry", type=ConfigBooleanEntry.class),
            @XmlElement(name = "attribute", type=ConfigNodeAttribute.class)
    })
    @XmlElementWrapper
    public List<ConfigNode> getChildNodes() {
        return allChildConfigBlocks;
    }
    public void addChildNode(ConfigNode configNode) {
        if (configNode.getNodeType() == ConfigNodeType.ROOT_BLOCK) {
            return;
        }
        allChildConfigBlocks.add(configNode);
    }

    public List<ConfigValueNode> getConfigEntries() {
        return getChildValueNodesOfType(ConfigNodeType.ENTRY);
    }
    public List<ConfigValueNode> getConfigBooleanEntries() {
        return getChildValueNodesOfType(ConfigNodeType.BOOLEAN_ENTRY);
    }
    public List<ConfigNode> getConfigContainers() {
        return getChildNodesOfType(ConfigNodeType.BLOCK);
    }

    private List<ConfigNode> getChildNodesOfType(ConfigNodeType type) {
        List<ConfigNode> childNodes = new ArrayList<>();
        for (ConfigNode childBlock : allChildConfigBlocks) {
            if (childBlock.getNodeType() != type) {
                continue;
            }

            childNodes.add(childBlock);
        }
        return childNodes;
    }
    private List<ConfigValueNode> getChildValueNodesOfType(ConfigNodeType type) {
        List<ConfigValueNode> childNodes = new ArrayList<>();
        for (ConfigNode childBlock : allChildConfigBlocks) {
            if (childBlock.getNodeType() != type) {
                continue;
            }

            childNodes.add((ConfigValueNode) childBlock);
        }
        return childNodes;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("<").append(configNodeName);
        for (ConfigValueNode attribute : configNodeAttributes) {
            result.append(" ").append(attribute.toString()).append(" ");
        }
        result.append(">\n");
        for (ConfigNode child : allChildConfigBlocks) {
            result.append("\t").append(child.toString());
        }
        result.append("</").append(configNodeName).append(">\n");
        return result.toString();
    }

    @Override
    public AbstractConfigNode getParentNode() {
        return this.parentNode;
    }

    public List<ConfigValueNode> getChangedValueNodes() {
        List<ConfigValueNode> changedNodes = new ArrayList<>();
        List<ConfigNode> childNodes = getChildNodes();
        for (ConfigNode childNode : childNodes) {
            if (childNode.getNodeType() == ConfigNodeType.BLOCK) {
                ConfigBlock childConfigBlock = (ConfigBlock) childNode;
                changedNodes.addAll(childConfigBlock.getChangedValueNodes());
                continue;
            }
            if (!(childNode instanceof ConfigValueNode)) {
                continue;
            }
            if (!((ConfigValueNode) childNode).isChanged()) {
                continue;
            }
            changedNodes.add((ConfigValueNode) childNode);
        }
        return changedNodes;
    }

    public void setEditable(boolean editable){
        super.setEditable(editable);
        for (ConfigValueNode valueNode : configNodeAttributes){
            valueNode.setEditable(editable);
        }
        for (ConfigNode valueNode : getChildNodes()){
            valueNode.setEditable(editable);
        }
    }
}
