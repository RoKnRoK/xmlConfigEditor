package com.rok.gwt.client.widgets;

import com.google.gwt.event.logical.shared.OpenEvent;
import com.google.gwt.event.logical.shared.OpenHandler;
import com.google.gwt.user.client.ui.*;
import com.rok.xml.config_dto.AbstractConfigNode;
import com.rok.xml.config_dto.ConfigEntry;
import com.rok.xml.config_dto.ConfigNodeType;
import com.rok.xml.config_dto.ConfigValueNode;

import java.util.List;

/**
 * Created by RoK on 28.06.2015.
 * All rights reserved =)
 */
public class ConfigEntryWithAttrsWidget extends Composite {

    DisclosurePanel widgetMainPanel = new DisclosurePanel();

    public ConfigEntryWithAttrsWidget(final ConfigValueNode configValueNode) {
        if (configValueNode.getNodeType() != ConfigNodeType.ENTRY_WITH_ATTRS) {
            widgetMainPanel.add(new Label("�� ������� ���������� ���� " + configValueNode.getDisplayName()));
            initWidget(widgetMainPanel);
            return;
        }

        widgetMainPanel.setHeader(new Label(configValueNode.getDisplayName()));
        widgetMainPanel.setContent(new VerticalPanel());
        widgetMainPanel.setAnimationEnabled(true);
        widgetMainPanel.setOpen(false);
        widgetMainPanel.setWidth("100%");
        widgetMainPanel.addOpenHandler(new OpenHandler<DisclosurePanel>() {
            @Override
            public void onOpen(OpenEvent<DisclosurePanel> event) {
                List<ConfigValueNode> nodeAttributes = configValueNode.getNodeAttributes();
                ConfigValueNode fictiveValueAttribute =
                        new ConfigEntry(configValueNode.getName() + " value ", configValueNode.getValue(), (AbstractConfigNode) configValueNode);
                nodeAttributes.add(fictiveValueAttribute);
                Widget containerWidget = new ConfigEntryWidget(nodeAttributes);
                containerWidget.setStyleName("padding");
                widgetMainPanel.setContent(containerWidget);
            }
        });
        widgetMainPanel.addStyleName("configBlock");

        initWidget(widgetMainPanel);
    }
}
