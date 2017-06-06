package com.rok.xml.dto.config_dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.security.krb5.Config;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by RoK on 21.06.2015.
 * All rights reserved =)
 */

public class ConfigBlock extends AbstractConfigNode implements ConfigNode, HasChildNodes {

    private static final long serialVersionUID = -2968138039440394332L;
    private static final Logger logger = LoggerFactory.getLogger(ConfigBlock.class.getName());


    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private final List<ConfigValueNode> entries = new ArrayList<>();
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private final List<ConfigValueNode> booleanEntries = new ArrayList<>();
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private final List<ConfigNode> blocks = new ArrayList<>();
    private ConfigNodeType nodeType;


    private ConfigBlock() {
        this.name = "defaultBlockName";
        this.nodeType = ConfigNodeType.BLOCK;
    }

    public ConfigBlock(String configBlockName, AbstractConfigNode parentNode) {
        this();
        this.name = configBlockName;
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
    public ConfigValueNode getEntry(Integer index) {
        return entries.get(index);
    }
    @Override
    public ConfigValueNode getBooleanEntry(Integer index) {
        return booleanEntries.get(index);
    }
    @Override
    public ConfigNode getBlock(Integer index) {
        return blocks.get(index);
    }

    /* todo: this is example for future
    @XmlElements({
            @XmlElement(name = "entry", type=ConfigEntry.class),
            @XmlElement(name = "block", type=ConfigBlock.class),
            @XmlElement(name = "booleanEntry", type=ConfigBooleanEntry.class),
            @XmlElement(name = "attribute", type=ConfigNodeAttribute.class)
    })
    @XmlElementWrapper
    public List<ConfigNode> getChildNodes() {
        return childNodes;
    }*/
    public void addChildNode(ConfigNode configNode) {
        switch (configNode.getNodeType()) {
            case BLOCK: {
                blocks.add(configNode);
            }
            break;
            case ENTRY: {
                entries.add((ConfigValueNode) configNode);
            }
            break;
            case BOOLEAN_ENTRY: {
                booleanEntries.add((ConfigValueNode) configNode);
            }
            break;
        }
    }

    public List<ConfigValueNode> getEntries() {
        return entries;
    }

    public List<ConfigValueNode> getBooleanEntries() {
        return booleanEntries;
    }

    public List<ConfigNode> getBlocks() {
        return blocks;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("<").append(name);
        for (ConfigValueNode attribute : attributes) {
            result.append(" ").append(attribute.toString()).append(" ");
        }
        result.append(">\n");
        for (ConfigValueNode configBooleanEntry : getBooleanEntries()) {
            result.append("\t").append(configBooleanEntry.toString());
        }
        result.append(">\n");
        for (ConfigValueNode configEntry : getEntries()) {
            result.append("\t").append(configEntry.toString());
        }
        Object firstBlock = blocks.size() > 1 ? blocks.get(0) : new ConfigBlock();
        if (firstBlock instanceof Map){
            Map firstBlockMap = (Map) firstBlock;
            logger.debug("Block #1: {}", firstBlockMap.get("name"));
        }
        else {
            logger.debug("Block #1: {}", ((ConfigBlock)firstBlock).getName());
        }
        logger.debug("Block of type: {}", firstBlock.getClass().getName());
        result.append(">\n");
        for (ConfigNode configBlock : getBlocks()) {
            result.append("\t").append(configBlock.toString());
        }
        result.append("</").append(name).append(">\n");
        return result.toString();
    }

    @Override
    public AbstractConfigNode getParentNode() {
        return this.parentNode;
    }
    @Override
    public void setParentNode(AbstractConfigNode parentNode) {
        this.parentNode = parentNode;
    }

    public List<ConfigValueNode> getChangedValueNodes() {
        List<ConfigValueNode> changedNodes = new ArrayList<>();
        logger.trace("Config block {}", this.getName());
        List<ConfigValueNode> configEntries = new ArrayList<>(getBooleanEntries());
        configEntries.addAll(getEntries());

        for (ConfigValueNode configEntry : configEntries) {
            if (!configEntry.isChanged()) {
                continue;
            }
            logger.trace("Child node {}: changed = {}", configEntry.getName(), configEntry.isChanged());
            changedNodes.add(configEntry);
        }
        logger.debug("Detecting changes: {}", getBlocks());
        for (ConfigNode configBlock : getBlocks()) {
            ConfigBlock childConfigBlock = (ConfigBlock) configBlock;
            changedNodes.addAll(childConfigBlock.getChangedValueNodes());

        }
        return changedNodes;
    }

    public void setEditable(boolean editable) {
        super.setEditable(editable);
        for (ConfigValueNode valueNode : attributes) {
            valueNode.setEditable(editable);
        }
        for (ConfigNode valueNode : getBlocks()) {
            valueNode.setEditable(editable);
        }
        for (ConfigNode valueNode : getEntries()) {
            valueNode.setEditable(editable);
        }
        for (ConfigNode valueNode : getBooleanEntries()) {
            valueNode.setEditable(editable);
        }
    }
}
