package com.rok.gwt.client.widgets;

import com.google.gwt.event.logical.shared.BeforeSelectionEvent;
import com.google.gwt.event.logical.shared.BeforeSelectionHandler;
import com.google.gwt.user.client.ui.*;
import com.rok.xml.dto.config_dto.ConfigBlock;
import com.rok.xml.dto.config_dto.ConfigNode;
import com.rok.xml.dto.config_dto.ConfigNodeType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RoK on 28.06.2015.
 * All rights reserved =)
 */
public class TabConfigBlockWidget extends Composite {

    TabPanel widgetMainPanel = new TabPanel();

    public TabConfigBlockWidget(final ConfigNode configNode) {
        if (configNode.getNodeType() != ConfigNodeType.ROOT_BLOCK){
            Label errorLabel = new Label("Не удалось отобразить блок " + configNode.getDisplayName());
            widgetMainPanel.add(errorLabel, "Ошибка!");
            initWidget(widgetMainPanel);
        }
        final ConfigBlock configBlock = (ConfigBlock) configNode;

        widgetMainPanel.setWidth("100%");
        List<ConfigNode> childConfigBlocks = configBlock.getChildNodes();
        final List<SimplePanel> tabStubs = new ArrayList<>();

        for (ConfigNode childConfigBlock : childConfigBlocks) {
            if (childConfigBlock.getNodeType() != ConfigNodeType.BLOCK) {
                continue;
            } //todo: что делать в случае !BLOCK
            SimplePanel tabStub = new SimplePanel();
            tabStub.setWidth("100%");
            widgetMainPanel.add(tabStub, childConfigBlock.getDisplayName());
            tabStubs.add(tabStub);
        }
        widgetMainPanel.addBeforeSelectionHandler(new BeforeSelectionHandler<Integer>() {
            @Override
            public void onBeforeSelection(BeforeSelectionEvent<Integer> event) {
                Integer selectedItem = event.getItem();
                ConfigNode selectedChildNode = configBlock.getChildNode(selectedItem);
                SimpleConfigBlockWidget tabContent = new SimpleConfigBlockWidget((ConfigBlock) selectedChildNode);
                ScrollPanel scrollableTabContent = new ScrollPanel();
                scrollableTabContent.add(tabContent);
                boolean removed = widgetMainPanel.remove(tabStubs.get(selectedItem));
                if (removed)
                    widgetMainPanel.insert(scrollableTabContent, selectedChildNode.getDisplayName(), selectedItem);
            }
        });
        widgetMainPanel.selectTab(0);
        initWidget(widgetMainPanel);
    }
}
