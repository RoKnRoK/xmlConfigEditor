package com.rok.gwt.xmlConfigEditorGwt.client.events;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;

/**
 * Created by RoK on 27.06.2015.
 * All rights reserved =)
 */
//todo: check if there are some concurrency problems
public class EventBusStorage {

    private SimpleEventBus eventBus;
    private static EventBusStorage instance = null;

    public static EventBusStorage getInstance() {
        if (instance != null) {return instance;}
        instance = new EventBusStorage();
        return instance;
    }


    public SimpleEventBus getEventBus() {
        if (instance == null) {return this.eventBus;}
        else                  {return instance.eventBus;}
    }

    public void setEventBus(SimpleEventBus eventBus) {

        if (instance == null) {this.eventBus = eventBus;}
        else                  {instance.eventBus = eventBus;}
    }

}
