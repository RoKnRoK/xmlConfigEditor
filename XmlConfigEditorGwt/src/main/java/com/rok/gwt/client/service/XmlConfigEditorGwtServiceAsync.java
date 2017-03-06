package com.rok.gwt.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.rok.xml.config_dto.ConfigBlock;
import com.rok.xml.config_dto.ConfigModificationInfo;
import com.rok.xml.config_dto.ConfigNode;

import java.io.IOException;

public interface XmlConfigEditorGwtServiceAsync {

    void saveConfigBlock(ConfigModificationInfo configModificationInfo, AsyncCallback<Boolean> async);

    void getConfigModificationInfo(AsyncCallback<ConfigModificationInfo> async);

    void cancelConfigEditing(ConfigModificationInfo configModificationInfo, AsyncCallback<Void> async);
}
