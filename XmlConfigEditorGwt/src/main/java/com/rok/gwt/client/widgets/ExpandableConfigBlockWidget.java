package com.rok.gwt.client.widgets;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.Label;
import com.rok.gwt.client.logger.Logger;
import com.rok.xml.dto.config_dto.ConfigBlock;
import com.rok.xml.dto.config_dto.ConfigNode;
import com.rok.xml.dto.config_dto.ConfigNodeType;

/**
 * Created by RoK on 28.06.2015.
 * All rights reserved =)
 */
public class ExpandableConfigBlockWidget extends Composite {

    private DisclosurePanel widgetMainPanel = new DisclosurePanel();

    public ExpandableConfigBlockWidget(final ConfigNode configNode) {
        if (configNode.getNodeType() != ConfigNodeType.BLOCK) {
            widgetMainPanel.setHeader(new Label("Не удалось отобразить блок настроек " + configNode.getDisplayName()));
            initWidget(widgetMainPanel);
            return;
        }

        widgetMainPanel.setAnimationEnabled(true);
        widgetMainPanel.setHeader(new Label(configNode.getDisplayName()));

        widgetMainPanel.setOpen(false);
        widgetMainPanel.setWidth("100%");
        widgetMainPanel.addStyleName("configBlock");


        // please read comment #1 below class
        widgetMainPanel.addOpenHandler(event -> {

            SimpleConfigBlockWidget configBlockWidget = new SimpleConfigBlockWidget((ConfigBlock) configNode);
            configBlockWidget.setStyleName("padding");
            widgetMainPanel.setContent(configBlockWidget);
            Logger.log(widgetMainPanel.getHeader().toString());
        });

        initWidget(widgetMainPanel);
    }
}

// Comment #1: GWT 2.8.0
// OpenHandler is used for lazy disclosure panel content loading on expand. But there is an issue:
// method setContentDisplay used with parameter false inside, which disables animation.
// It's better to open without animation rather than load all disclosure panels' content at once
