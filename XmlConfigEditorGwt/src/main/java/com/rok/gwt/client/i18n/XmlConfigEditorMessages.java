package com.rok.gwt.client.i18n;

import com.google.gwt.i18n.client.Messages;

/**
 * Created by RoK.
 * All rights reserved =)
 */
public interface XmlConfigEditorMessages extends Messages{
    @DefaultMessage("Can''t display config ''{0}''")
    String errorDisplayingConfig(String configName);

}
