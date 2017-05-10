package com.rok.gwt.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.*;
import com.rok.gwt.client.logic.ConfigBlockWidgetFactory;
import com.rok.xml.dto.config_dto.ConfigBlock;

/**
 * Created by RoK on 21.06.2015.
 * All rights reserved =)
 */
public class XmlConfigEditorMainView extends Composite {

    public void renderConfig(ConfigBlock result) {
        mainPanel.clear();
        mainPanel.add((ConfigBlockWidgetFactory.createWidget(result)));
    }

    interface XmlConfigEditorMainViewUiBinder extends UiBinder<Widget, XmlConfigEditorMainView> {    }
    private static XmlConfigEditorMainViewUiBinder ourUiBinder = GWT.create(XmlConfigEditorMainViewUiBinder.class);

    public interface Presenter {
        void onSaveButtonClick();
    }

    private Presenter presenter;

    @UiField
    SimplePanel mainPanel;
    @UiField
    Button saveButton;

    public CheckBox getDisplayNamesMode() {
        return displayNamesMode;
    }

    @UiField
    CheckBox displayNamesMode;

    public XmlConfigEditorMainView() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }

    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    @UiHandler("saveButton")
    public void handleClick(ClickEvent event) {
        presenter.onSaveButtonClick();
    }

    public void enableSaveButton() {
        saveButton.setEnabled(true);
    }

    public void disableSaveButton() {
        saveButton.setEnabled(false);
    }
}