package com.rok.gwt.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.rok.xml.dto.config_dto.ConfigModificationInfo;

public interface XmlConfigEditorGwtServiceAsync {

    void saveConfigBlock(ConfigModificationInfo configModificationInfo, AsyncCallback<Boolean> async);

    void getConfigModificationInfo(AsyncCallback<ConfigModificationInfo> async);

    void cancelConfigEditing(ConfigModificationInfo configModificationInfo, AsyncCallback<Void> async);
}
