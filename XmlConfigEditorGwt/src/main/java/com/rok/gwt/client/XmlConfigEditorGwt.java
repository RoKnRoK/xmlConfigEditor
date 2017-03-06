package com.rok.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.rok.gwt.client.presenter.XmlConfigEditorMainPresenter;

public class XmlConfigEditorGwt implements EntryPoint {


    public void onModuleLoad() {
        new XmlConfigEditorMainPresenter();
    }

}
