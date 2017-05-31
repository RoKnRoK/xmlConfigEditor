package com.rok.gwt.client.logic;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.*;
import com.rok.gwt.client.i18n.XmlConfigEditorMessages;
import com.rok.gwt.client.widgets.ExpandableConfigBlockWidget;
import com.rok.gwt.client.widgets.ConfigEntryWidget;
import com.rok.gwt.client.widgets.TabConfigBlockWidget;
import com.rok.gwt.constants.StyleNames;
import com.rok.xml.dto.config_dto.*;


/**
 * Created by RoK on 24.06.2015.
 * All rights reserved =)
 */
public class ConfigBlockWidgetFactory {
    private static XmlConfigEditorMessages messages = GWT.create(XmlConfigEditorMessages.class);

    public static Widget createWidget(ConfigNode configBlock) {
        switch (configBlock.getNodeType()) {
            case BLOCK:
                if (configBlock.getParentNode() == null) {
                    return new TabConfigBlockWidget(configBlock);
                }
                return new ExpandableConfigBlockWidget(configBlock);
            case ENTRY:
            case ATTRIBUTE:
                return new ConfigEntryWidget((ConfigValueNode) configBlock);
            default:
                Label error = new Label(messages.errorDisplayingConfig(configBlock.getDisplayName()));
                error.addStyleName(StyleNames.ERROR);
                error.addStyleName(StyleNames.CONFIG_BLOCK);
                return error;
        }
    }

}
