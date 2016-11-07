package com.rok.gwt.xmlConfigEditorGwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.rok.gwt.xmlConfigEditorGwt.client.presenter.XmlConfigEditorMainPresenter;

public class XmlConfigEditorGwt implements EntryPoint {


    public void onModuleLoad() {
        new XmlConfigEditorMainPresenter();

    }

}
