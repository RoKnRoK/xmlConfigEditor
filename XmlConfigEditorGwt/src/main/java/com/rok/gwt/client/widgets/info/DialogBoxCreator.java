package com.rok.gwt.client.widgets.info;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.*;
import com.rok.gwt.client.i18n.XmlConfigEditorConstants;

/**
 * Created by RoK.
 * All rights reserved =)
 */
public class DialogBoxCreator {

    private static final XmlConfigEditorConstants constants = GWT.create(XmlConfigEditorConstants.class);

    public static DialogBox createErrorMessageBox(String capture, String message) {
        final DialogBox dialogBox = new DialogBox(false);
        dialogBox.setText(capture);

        Label content = new Label(message);
        if (dialogBox.isAutoHideEnabled()) {
            dialogBox.setWidget(content);
        } else {
            VerticalPanel vPanel = new VerticalPanel();
            vPanel.setSpacing(2);
            vPanel.add(content);
            vPanel.add(new Label("\n"));
            vPanel.add(new Button(constants.close(), (ClickHandler) event -> dialogBox.hide()));
            dialogBox.setWidget(vPanel);
        }
        return dialogBox;
    }

    @SuppressWarnings("unused")
    public static DialogBox createConfirmationBox(String capture, String message, final ConfirmCallback callback) {
        final DialogBox dialogBox = new DialogBox(false);
        dialogBox.setText(capture);

        Widget content = new Label(message);
        Button okButton = new Button(constants.yes(), (ClickHandler) event -> callback.onOk());
        Button cancelButton = new Button(constants.no(), (ClickHandler) event -> callback.onCancel());
        HorizontalPanel horizontalPanel = new HorizontalPanel();
        horizontalPanel.add(okButton);
        horizontalPanel.add(cancelButton);

        VerticalPanel verticalPanel = new VerticalPanel();
        verticalPanel.setSpacing(2);
        verticalPanel.add(content);
        verticalPanel.add(new Label("\n"));
        verticalPanel.add(horizontalPanel);

        dialogBox.setWidget(verticalPanel);
        return dialogBox;
    }
}
