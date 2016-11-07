package com.rok.gwt.xmlConfigEditorGwt.client.presenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.rok.gwt.xmlConfigEditorGwt.client.events.ConfigValueChangedEvent;
import com.rok.gwt.xmlConfigEditorGwt.client.events.EventBusStorage;
import com.rok.gwt.xmlConfigEditorGwt.client.service.XmlConfigEditorGwtService;
import com.rok.gwt.xmlConfigEditorGwt.client.service.XmlConfigEditorGwtServiceAsync;
import com.rok.gwt.xmlConfigEditorGwt.client.view.XmlConfigEditorMainView;
import com.rok.xml.config_dto.ConfigBlock;
import com.rok.xml.config_dto.ConfigValueNode;
import com.rok.xml.settings.ApplicationSettings;

import java.util.Objects;

/**
 * Created by RoK on 21.06.2015.
 * All rights reserved =)
 */
public class XmlConfigEditorMainPresenter implements XmlConfigEditorMainView.Presenter {
    XmlConfigEditorGwtServiceAsync serviceAsync = GWT.create(XmlConfigEditorGwtService.class);

    XmlConfigEditorMainView mainView = GWT.create(XmlConfigEditorMainView.class);

    private ConfigBlock configBlock;

    public XmlConfigEditorMainPresenter() {
        mainView.setPresenter(this);
        SimpleEventBus eventBus = new SimpleEventBus();
        EventBusStorage.getInstance().setEventBus(eventBus);

        bind(eventBus);
        fetchConfig();

        RootPanel.get("main").clear();
        RootPanel.get("main").add(mainView.asWidget());

    }

    private void bind(SimpleEventBus eventBus) {
        eventBus.addHandler(ConfigValueChangedEvent.MY_TYPE, new ConfigValueChangedEvent.ConfigValueChangedEventHandler() {
            @Override
            public void onValueChanged(ConfigValueChangedEvent event) {
                ConfigValueNode configValueNode = event.getConfigValueNode();
                String newValue = configValueNode.getValue();
                String originalValue = configValueNode.getOriginalValue();

                if (Objects.equals(newValue, originalValue))
                    mainView.disableSaveButton();
                else
                    mainView.enableSaveButton();
            }
        });
        mainView.getDisplayNamesMode().addValueChangeHandler(new ValueChangeHandler<Boolean>() {
            @Override
            public void onValueChange(ValueChangeEvent<Boolean> event) {
                ApplicationSettings.setDisplayNamesEditingEnabled(event.getValue());
                mainView.renderConfig(configBlock);
            }
        });
    }

    private void fetchConfig() {
        serviceAsync.getConfigBlock(new AsyncCallback<ConfigBlock>() {

            @Override
            public void onFailure(Throwable caught) {
            }

            @Override
            public void onSuccess(ConfigBlock result) {
                configBlock = result;
                mainView.renderConfig(result);
            }
        });
    }

    @Override
    public void onSaveButtonClick() {
        serviceAsync.saveConfigBlock(this.configBlock, new AsyncCallback<Boolean>() {
            @Override
            public void onFailure(Throwable caught) {
                //todo: обработка провала
            }

            @Override
            public void onSuccess(Boolean result) {
                fetchConfig();
                mainView.disableSaveButton();
                //todo: обработка успеха
            }
        });
    }
}
