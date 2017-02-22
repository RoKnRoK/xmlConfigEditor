package com.rok.gwt.client.widgets;

import com.google.gwt.event.logical.shared.OpenEvent;
import com.google.gwt.event.logical.shared.OpenHandler;
import com.google.gwt.user.client.ui.*;
import com.rok.xml.config_dto.ConfigBlock;
import com.rok.xml.config_dto.ConfigNode;
import com.rok.xml.config_dto.ConfigNodeType;

/**
 * Created by RoK on 28.06.2015.
 * All rights reserved =)
 */
public class ExpandableConfigBlockWidget extends Composite {

    DisclosurePanel widgetMainPanel = new DisclosurePanel();

    public ExpandableConfigBlockWidget(final ConfigNode configNode) {
        if (configNode.getNodeType() != ConfigNodeType.BLOCK) {
            widgetMainPanel.setHeader(new Label("Не удалось отобразить блок настроек " + configNode.getDisplayName()));
            initWidget(widgetMainPanel);
            return;
        }

        widgetMainPanel.setHeader(new Label(configNode.getDisplayName()));
        widgetMainPanel.setContent(new VerticalPanel());
        widgetMainPanel.setAnimationEnabled(true);
        widgetMainPanel.setOpen(false);
        widgetMainPanel.setWidth("100%");
        widgetMainPanel.addStyleName("configBlock");

        widgetMainPanel.addOpenHandler(new OpenHandler<DisclosurePanel>() {
            @Override
            public void onOpen(OpenEvent<DisclosurePanel> event) {
                SimpleConfigBlockWidget configBlockWidget = new SimpleConfigBlockWidget((ConfigBlock) configNode);
                configBlockWidget.setStyleName("padding");
                widgetMainPanel.setContent(configBlockWidget);
            }
        });

        initWidget(widgetMainPanel);
    }

}
