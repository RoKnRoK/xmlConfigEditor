package com.rok.xml.settings;

import java.io.Serializable;

/**
 * Created by RoK on 28.06.2015.
 * All rights reserved =)
 */
public class ApplicationSettings implements Serializable{
    private static final long serialVersionUID = 6747455449463246943L;
    private static Boolean displayNamesEditingEnabled = false;

    public static void setDisplayNamesEditingEnabled(Boolean displayNamesEditingEnabled) {
        ApplicationSettings.displayNamesEditingEnabled = displayNamesEditingEnabled;
    }

    public static boolean isDisplayNamesEditingEnabled() {
        return ApplicationSettings.displayNamesEditingEnabled;
    }
}
