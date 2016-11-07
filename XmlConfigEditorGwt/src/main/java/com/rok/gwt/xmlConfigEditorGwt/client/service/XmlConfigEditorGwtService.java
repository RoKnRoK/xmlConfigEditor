package com.rok.gwt.xmlConfigEditorGwt.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.rok.xml.config_dto.ConfigBlock;
import com.rok.xml.config_dto.ConfigNode;

import java.io.IOException;

@RemoteServiceRelativePath("XmlConfigEditorGwtService")
public interface XmlConfigEditorGwtService extends RemoteService {
    // Sample interface method of remote interface
    ConfigBlock getConfigBlock();
    boolean saveConfigBlock(ConfigBlock configBlock) ;

}
