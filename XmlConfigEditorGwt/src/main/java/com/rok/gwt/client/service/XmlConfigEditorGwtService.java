package com.rok.gwt.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.rok.xml.dto.config_dto.ConfigModificationInfo;

@RemoteServiceRelativePath("XmlConfigEditorGwtService")
public interface XmlConfigEditorGwtService extends RemoteService {
    // Sample interface method of remote interface
    ConfigModificationInfo getConfigModificationInfo();
    boolean saveConfigBlock(ConfigModificationInfo configModificationInfo) ;
    void cancelConfigEditing(ConfigModificationInfo configModificationInfo);

}
