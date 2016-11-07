package com.rok.gwt.xmlConfigEditorGwt.client.events;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.rok.xml.config_dto.ConfigValueNode;

/**
 * Created by RoK on 27.06.2015.
 * All rights reserved =)
 */
public class ConfigValueChangedEvent extends GwtEvent<ConfigValueChangedEvent.ConfigValueChangedEventHandler> {
       private ConfigValueNode configValueNode;

    public ConfigValueChangedEvent(ConfigValueNode configValueNode) {
        this.configValueNode = configValueNode;
    }


    public ConfigValueNode getConfigValueNode() {
        return configValueNode;
    }

    public interface ConfigValueChangedEventHandler extends EventHandler {
        void onValueChanged(ConfigValueChangedEvent event);
    }

    public static Type<ConfigValueChangedEventHandler> MY_TYPE = new Type<>();

    @Override
    public Type<ConfigValueChangedEventHandler> getAssociatedType() {
        return MY_TYPE;
    }

    @Override
    protected void dispatch(ConfigValueChangedEventHandler handler) {
        handler.onValueChanged(this);
    }

}
