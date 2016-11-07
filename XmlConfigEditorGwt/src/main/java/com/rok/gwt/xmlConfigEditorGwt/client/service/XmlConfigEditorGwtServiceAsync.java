package com.rok.gwt.xmlConfigEditorGwt.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.rok.xml.config_dto.ConfigBlock;
import com.rok.xml.config_dto.ConfigNode;

import java.io.IOException;

public interface XmlConfigEditorGwtServiceAsync {

    void saveConfigBlock(ConfigBlock configBlock, AsyncCallback<Boolean> async);

    void getConfigBlock(AsyncCallback<ConfigBlock> async);
}
