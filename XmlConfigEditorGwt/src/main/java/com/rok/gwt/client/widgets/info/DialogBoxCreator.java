package com.rok.gwt.client.widgets.info;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.rok.gwt.client.i18n.XmlConfigEditorConstants;
import com.rok.gwt.client.i18n.XmlConfigEditorMessages;

/**
 * Created by RoK.
 * All rights reserved =)
 */
public class DialogBoxCreator {


    public static DialogBox createErrorMessageBox(String capture, String message) {
        DialogBox dialogBox = new DialogBox(true, true);
        dialogBox.setText(capture);

        // Setcontent
        Label content = new Label(message);
        if (dialogBox.isAutoHideEnabled())  {
            dialogBox.setWidget(content);
        } else {
            VerticalPanel vPanel = new VerticalPanel();
            vPanel.setSpacing(2);
            vPanel.add(content);
            vPanel.add(new Label("\n"));
            vPanel.add(new Button("Close", new ClickHandler() {
                public void onClick(ClickEvent event) {
                    dialogBox.hide();
                }
            }));
            dialogBox.setWidget(vPanel);
        }
        return  dialogBox;
//        setPopupPosition(100, 150);
    }

}
