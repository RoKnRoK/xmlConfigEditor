package com.rok.gwt.xmlConfigEditorGwt.client.widgets;

import com.google.gwt.user.client.ui.*;
import com.google.gwt.view.client.ListDataProvider;
import com.rok.xml.config_dto.ConfigEntry;
import com.rok.xml.config_dto.ConfigValueNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RoK on 24.06.2015.
 * All rights reserved =)
 */
public class ConfigEntryWidget extends Composite {

    SimplePanel mainWidgetPanel = new SimplePanel();
    ConfigEntriesTable configEntryDataGrid;

    public ConfigEntryWidget(ConfigValueNode configEntry) {
        configEntryDataGrid = new ConfigEntriesTable(configEntry.isEditable());
        switch (configEntry.getNodeType()) {
            case ENTRY:
            case ENTRY_WITH_ATTRS:
            case ATTRIBUTE:
                createWidget(configEntry); break;
            default: {
                mainWidgetPanel.add(new Label("Не удалось отобразить настройку!"));
                initWidget(mainWidgetPanel);
            } break;
        }

    }

    private void createWidget(ConfigValueNode configEntry) {
        List<ConfigValueNode> configEntryAsList = new ArrayList<>();
        configEntryAsList.add(configEntry);
        ListDataProvider<ConfigValueNode> configEntriesProvider = new ListDataProvider<>(configEntryAsList);
        configEntriesProvider.addDataDisplay(configEntryDataGrid);
        configEntryDataGrid.setVisibleRange(0, configEntryAsList.size());
        mainWidgetPanel.add(configEntryDataGrid);

        initWidget(mainWidgetPanel);
    }

    public ConfigEntryWidget(List<ConfigValueNode> configEntries) {
        if (configEntries==null || configEntries.isEmpty()){
            initWidget(mainWidgetPanel);
            return;
        }
        configEntryDataGrid = new ConfigEntriesTable(configEntries.get(0).isEditable());
        ListDataProvider<ConfigValueNode> configEntriesProvider = new ListDataProvider<>(configEntries);
        configEntriesProvider.addDataDisplay(configEntryDataGrid);
        configEntryDataGrid.setVisibleRange(0, configEntries.size());
        mainWidgetPanel.add(configEntryDataGrid);

        initWidget(mainWidgetPanel);
    }
}
