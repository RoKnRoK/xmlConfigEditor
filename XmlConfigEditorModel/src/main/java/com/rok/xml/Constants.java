package com.rok.xml;

import java.io.File;
import java.io.Serializable;

/**
 * Created by RoK on 27.06.2015.
 * All rights reserved =)
 */
//in order to be correctly serialised/deserialised it should extend Serializable
public interface Constants extends Serializable {
    String DISPLAY_NAME = "displayName";
    String FILE_NAME = "C:" + File.separator + "Temp" + File.separator + "standard.xml";


    int EDITING_TIME_IN_MILLIS = 15000;
    String LOCK_FILENAME = "config.lock";
}
