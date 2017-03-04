package com.rok.gwt.client.logger;

/**
 * Created by RoK.
 * All rights reserved =)
 */
public class Logger {

    public static native void log(String msg) /*-{
        $wnd.console.log(msg);
    }-*/;
}
