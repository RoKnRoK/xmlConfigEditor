package com.rok.gwt.client.widgets.table;

import com.google.gwt.user.cellview.client.CellTable;

/**
 * Created by RoK.
 * All rights reserved =)
 */
public interface ConfigEntriesTableResources extends CellTable.Resources {
    @Source({CellTable.Style.DEFAULT_CSS, "ConfigEntriesTable.css"})
    ConfigEntriesTableStyle cellTableStyle();
    interface ConfigEntriesTableStyle extends CellTable.Style {
    }
}

