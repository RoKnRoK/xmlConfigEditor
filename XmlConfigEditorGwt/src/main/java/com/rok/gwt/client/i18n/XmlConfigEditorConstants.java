package com.rok.gwt.client.i18n;


import com.google.gwt.i18n.client.Constants;

/**
 * Created by RoK.
 * All rights reserved =)
 */
public interface XmlConfigEditorConstants extends Constants{
    @DefaultStringValue("Warning")
    String warning();

    @DefaultStringValue("Your editing time has run out. Your changes will be saved. Please refresh page to continue editing")
    String configEditingTimerRunout();

    @DefaultStringValue("Ok")
    String ok();

    @DefaultStringValue("Cancel")
    String cancel();

    @DefaultStringValue("Yes")
    String yes();

    @DefaultStringValue("No")
    String no();


    @DefaultStringValue("Close")
    String close();

    @DefaultStringValue("There are unsaved changes, do you want to save before quit?")
    String thereAreUnsavedChanges();
}
