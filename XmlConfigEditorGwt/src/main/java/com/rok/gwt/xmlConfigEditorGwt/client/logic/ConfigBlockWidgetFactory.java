package com.rok.gwt.xmlConfigEditorGwt.client.logic;

import com.google.gwt.user.client.ui.*;
import com.rok.gwt.xmlConfigEditorGwt.client.widgets.ConfigEntryWithAttrsWidget;
import com.rok.gwt.xmlConfigEditorGwt.client.widgets.ExpandableConfigBlockWidget;
import com.rok.gwt.xmlConfigEditorGwt.client.widgets.ConfigEntryWidget;
import com.rok.gwt.xmlConfigEditorGwt.client.widgets.TabConfigBlockWidget;
import com.rok.xml.config_dto.*;

/**
 * Created by RoK on 24.06.2015.
 * All rights reserved =)
 */
public class ConfigBlockWidgetFactory {
    public static Widget createWidget(ConfigNode configBlock) {
        switch (configBlock.getNodeType()) {
            case ROOT_BLOCK:
                return new TabConfigBlockWidget(configBlock);
            case BLOCK:
                return new ExpandableConfigBlockWidget(configBlock);
            case ENTRY_WITH_ATTRS:
                return new ConfigEntryWithAttrsWidget((ConfigValueNode) configBlock);
            case ENTRY:
            case ATTRIBUTE:
                return new ConfigEntryWidget((ConfigValueNode) configBlock);
            default:
                return new Label("Не удалось отобразить настройку "+configBlock.getDisplayName());
        }
    }

}
