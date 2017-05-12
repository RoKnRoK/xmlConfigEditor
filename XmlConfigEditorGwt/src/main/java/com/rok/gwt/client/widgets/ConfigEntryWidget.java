package com.rok.gwt.client.widgets;

import com.google.gwt.user.client.ui.*;
import com.google.gwt.view.client.ListDataProvider;
import com.rok.gwt.client.widgets.table.ConfigEntriesTable;
import com.rok.xml.dto.config_dto.ConfigValueNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RoK on 24.06.2015.
 * All rights reserved =)
 */
public class ConfigEntryWidget extends Composite {

    private SimplePanel mainWidgetPanel = new SimplePanel();
    private ConfigEntriesTable configEntryDataGrid;

    public ConfigEntryWidget(ConfigValueNode configEntry) {
        configEntryDataGrid = new ConfigEntriesTable(configEntry.isEditable());
        switch (configEntry.getNodeType()) {
            case ENTRY:
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

}
