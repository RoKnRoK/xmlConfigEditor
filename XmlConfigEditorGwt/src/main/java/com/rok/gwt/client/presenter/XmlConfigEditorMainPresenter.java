package com.rok.gwt.client.presenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.rok.gwt.client.constants.TimeConstants;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.rok.gwt.client.events.ConfigValueChangedEvent;
import com.rok.gwt.client.events.EventBusStorage;
import com.rok.gwt.client.i18n.XmlConfigEditorConstants;
import com.rok.gwt.client.logger.Logger;
import com.rok.gwt.client.service.XmlConfigEditorGwtService;
import com.rok.gwt.client.service.XmlConfigEditorGwtServiceAsync;
import com.rok.gwt.client.view.XmlConfigEditorMainView;
import com.rok.gwt.client.widgets.info.DialogBoxCreator;
import com.rok.xml.config_dto.ConfigBlock;
import com.rok.xml.config_dto.ConfigModificationInfo;
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

    public static final XmlConfigEditorConstants constants = GWT.create(XmlConfigEditorConstants.class);

    private ConfigModificationInfo configModificationInfo;
    private Timer editingTimer;
    private boolean configBlockChanged = false;

    public XmlConfigEditorMainPresenter() {
        mainView.setPresenter(this);
        SimpleEventBus eventBus = new SimpleEventBus();
        EventBusStorage.getInstance().setEventBus(eventBus);

        bind(eventBus);
        fetchConfig();

        RootPanel.get("main").clear();
        RootPanel.get("main").add(mainView.asWidget());
//        RootPanel.get().clear();
//        RootPanel.get().add(mainView.asWidget());

    }

    private void bind(SimpleEventBus eventBus) {
        eventBus.addHandler(ConfigValueChangedEvent.MY_TYPE, new ConfigValueChangedEvent.ConfigValueChangedEventHandler() {
            @Override
            public void onValueChanged(ConfigValueChangedEvent event) {
                ConfigValueNode configValueNode = event.getConfigValueNode();
                String newValue = configValueNode.getValue();
                String originalValue = configValueNode.getOriginalValue();
                configBlockChanged = ! Objects.equals(newValue, originalValue);
                if (configBlockChanged) {
                    mainView.enableSaveButton();
                } else {
                    mainView.disableSaveButton();
                }
            }
        });
        mainView.getDisplayNamesMode().addValueChangeHandler(new ValueChangeHandler<Boolean>() {
            @Override
            public void onValueChange(ValueChangeEvent<Boolean> event) {
                ApplicationSettings.setDisplayNamesEditingEnabled(event.getValue());
                mainView.renderConfig(configModificationInfo.getConfigBlock());
            }
        });

        Window.addWindowClosingHandler(new Window.ClosingHandler() {

            @Override
            public void onWindowClosing(Window.ClosingEvent event) {
                Logger.log("ConfigBlockChanged = " + configBlockChanged);
                Logger.log("Event = " + event.getMessage());
                if (configBlockChanged) {
                    event.setMessage("Имеются несохраненные изменения. Точно закрыть?");
                    return;
                }

                Logger.log("Going to cancel editing");
                serviceAsync.cancelConfigEditing(configModificationInfo, new AsyncCallback<Void>() {
                    @Override
                    public void onFailure(Throwable throwable) {

                    }

                    @Override
                    public void onSuccess(Void aBoolean) {

                    }
                });
            }


        });
    }

    private void fetchConfig() {
        serviceAsync.getConfigModificationInfo(new AsyncCallback<ConfigModificationInfo>() {

            @Override
            public void onFailure(Throwable caught) {
            }

            @Override
            public void onSuccess(ConfigModificationInfo result) {
                configModificationInfo = result;
                mainView.renderConfig(result.getConfigBlock());
                if (!configModificationInfo.getConfigBlock().isEditable()) {
                    return;
                }
                editingTimer = new Timer() {
                    @Override
                    public void run() {
                        mainView.disableSaveButton();
                        serviceAsync.saveConfigBlock(configModificationInfo, new AsyncCallback<Boolean>() {
                            @Override
                            public void onFailure(Throwable throwable) {

                            }

                            @Override
                            public void onSuccess(Boolean success) {
                                DialogBoxCreator.createErrorMessageBox(constants.warning(), constants.configEditingTimerRunout()).center();
                                configModificationInfo.getConfigBlock().setEditable(false);
                                mainView.renderConfig(configModificationInfo.getConfigBlock());
                            }
                        });
                    }
                };
                editingTimer.schedule(TimeConstants.EDITING_TIME_IN_MILLIS);
            }
        });
    }

    @Override
    public void onSaveButtonClick() {
        serviceAsync.saveConfigBlock(this.configModificationInfo, new AsyncCallback<Boolean>() {
            @Override
            public void onFailure(Throwable caught) {
                //todo: обработка провала
            }

            @Override
            public void onSuccess(Boolean result) {
                fetchConfig(); //needed to lock file again
                configBlockChanged = false;
                mainView.disableSaveButton();
                //todo: обработка успеха
            }
        });
    }
}