package com.rok.gwt.client.widgets.table;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.EditTextCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.rok.gwt.client.events.ConfigValueChangedEvent;
import com.rok.gwt.client.events.EventBusStorage;
import com.rok.xml.config_dto.ConfigValueNode;

import java.util.Objects;

/**
 * Created by RoK on 25.06.2015.
 * All rights reserved =)
 */
public class ConfigEntriesTable extends CellTable<ConfigValueNode> {

    private static final int DEFAULT_PAGESIZE = 15;
    private static CellTable.Resources resources = GWT.create(ConfigEntriesTableResources.class);

    public ConfigEntriesTable() {
        this(false);
    }

    public ConfigEntriesTable(boolean isEditable) {
        super(DEFAULT_PAGESIZE, resources);
        this.setWidth("100%");
        initColumns(isEditable);
    }

    protected void initColumns(boolean isEditable) {
        initNameColumn();
        AbstractCell<String> valueCell = isEditable ? new EditTextCell() : new TextCell();
        initValueColumn(valueCell);

    }

    protected void initValueColumn(AbstractCell<String> valueCell) {
        // EntryValue

        //editTextCell.
        Column<ConfigValueNode, String> valueColumn =
                new Column<ConfigValueNode, String>(valueCell) {
                    @Override
                    public String getValue(ConfigValueNode object) {
                        return object.getValue();
                    }
                };

        valueColumn.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
        valueColumn.setFieldUpdater(new FieldUpdater<ConfigValueNode, String>() {
            @Override
            public void update(int index, ConfigValueNode object, String value) {
                // Called when the user changes the value.
                String oldValue = object.getValue();
                if (Objects.equals(oldValue, value)) {
                    return;
                }
                object.setValue(value);
                EventBusStorage.getInstance().getEventBus().fireEvent(new ConfigValueChangedEvent(object));


            }
        });
        this.addColumn(valueColumn);
        this.setColumnWidth(valueColumn, 70, com.google.gwt.dom.client.Style.Unit.PCT);
    }

    protected void initNameColumn() {
        TextColumn<ConfigValueNode> nameColumn = new TextColumn<ConfigValueNode>() {
            @Override
            public String getValue(ConfigValueNode object) {
                return object.getDisplayName();
            }
        };
        nameColumn.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
        this.addColumn(nameColumn);
        this.setColumnWidth(nameColumn, 30, com.google.gwt.dom.client.Style.Unit.PCT);
    }
}