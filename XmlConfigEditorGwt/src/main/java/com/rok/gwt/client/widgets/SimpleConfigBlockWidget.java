package com.rok.gwt.client.widgets;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.HasDirection;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.view.client.ListDataProvider;
import com.rok.gwt.client.events.ConfigValueChangedEvent;
import com.rok.gwt.client.events.EventBusStorage;
import com.rok.gwt.client.logic.ConfigBlockWidgetFactory;
import com.rok.gwt.client.widgets.table.ConfigEntriesTable;
import com.rok.xml.dto.config_dto.ConfigBlock;
import com.rok.xml.dto.config_dto.ConfigBooleanEntry;
import com.rok.xml.dto.config_dto.ConfigNode;
import com.rok.xml.dto.config_dto.ConfigValueNode;

import java.util.List;

/**
 * Created by RoK on 25.06.2015.
 * All rights reserved =)
 */
public class SimpleConfigBlockWidget extends Composite {

    VerticalPanel mainWidgetPanel = new VerticalPanel();
    ConfigEntriesTable configEntryDataGrid;
    ConfigEntriesTable configAttributesDataGrid ;


    public SimpleConfigBlockWidget(ConfigBlock configBlock) {
        configEntryDataGrid =  new ConfigEntriesTable(configBlock.isEditable());
        configAttributesDataGrid = new ConfigEntriesTable(configBlock.isEditable());

        addBooleanEntries(configBlock);
        addAttributes(configBlock);
        addEntries(configBlock);
        addEntriesWithAttrs(configBlock);
        addChildBlocks(configBlock);

        mainWidgetPanel.setWidth("100%");
        initWidget(mainWidgetPanel);
    }

    private void addEntriesWithAttrs(ConfigBlock configBlock) {
        List<ConfigNode> childConfigContainers = configBlock.getEntriesWithAttrs();
        for (ConfigNode childConfigContainer : childConfigContainers) {
            Widget widget = ConfigBlockWidgetFactory.createWidget(childConfigContainer);
            mainWidgetPanel.add(widget);
        }
    }

    private void addChildBlocks(ConfigBlock configBlock) {
        List<ConfigNode> childConfigContainers = configBlock.getConfigContainers();
        for (ConfigNode childConfigContainer : childConfigContainers) {
            Widget widget = ConfigBlockWidgetFactory.createWidget(childConfigContainer);
            mainWidgetPanel.add(widget);
        }
    }

    private void addEntries(ConfigBlock configBlock) {
        List<ConfigValueNode> childConfigEntries = configBlock.getConfigEntries();
        ListDataProvider<ConfigValueNode> configEntriesProvider = new ListDataProvider<>(childConfigEntries);
        configEntriesProvider.addDataDisplay(configEntryDataGrid);
        configEntryDataGrid.setVisibleRange(0, childConfigEntries.size());
        mainWidgetPanel.add(configEntryDataGrid);
    }
    private void addBooleanEntries(ConfigBlock configBlock) {
        List<ConfigValueNode> childConfigEntries = configBlock.getConfigBooleanEntries();
        for (final ConfigValueNode configValueNode : childConfigEntries) {
            final ConfigBooleanEntry configBooleanEntry = (ConfigBooleanEntry) configValueNode;
            CheckBox checkBox = new CheckBox();
            checkBox.setText(configBooleanEntry.getDisplayName(), HasDirection.Direction.RTL);
            checkBox.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
                @Override
                public void onValueChange(ValueChangeEvent<Boolean> event) {
                    Boolean value = event.getValue();
                    configBooleanEntry.setBooleanValue(value);
                    EventBusStorage.getInstance().getEventBus().fireEvent(new ConfigValueChangedEvent(configBooleanEntry));
                }
            });
            checkBox.setValue(configBooleanEntry.getBooleanValue());
            mainWidgetPanel.add(checkBox);
        }
        mainWidgetPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
    }

    private void addAttributes(ConfigBlock configBlock) {
        List<ConfigValueNode> configBlockAttributes = configBlock.getNodeAttributes();
        ListDataProvider<ConfigValueNode> configAttributesProvider = new ListDataProvider<>(configBlockAttributes);
        configAttributesProvider.addDataDisplay(configAttributesDataGrid);
        configAttributesDataGrid.setVisibleRange(0, configBlockAttributes.size());
        mainWidgetPanel.add(configAttributesDataGrid);
    }

}
