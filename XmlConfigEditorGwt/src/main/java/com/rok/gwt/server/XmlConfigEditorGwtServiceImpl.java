package com.rok.gwt.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.rok.gwt.client.service.XmlConfigEditorGwtService;
import com.rok.xml.dto.config_dto.ConfigModificationInfo;
import com.rok.xml.ejb.XmlConfigEditorLocal;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;

public class XmlConfigEditorGwtServiceImpl extends RemoteServiceServlet implements XmlConfigEditorGwtService {

    @SuppressWarnings("unused")
    @EJB
    private XmlConfigEditorLocal xmlConfigEditorLocal;

    private static final long serialVersionUID = -8248560938058853857L;



    @PostConstruct
    public void init(){
        System.out.println("XmlConfigEditorLocal: " + xmlConfigEditorLocal);
    }
    @Override
    public ConfigModificationInfo getConfigModificationInfo() {
        return  xmlConfigEditorLocal.getConfigModificationInfo();

    }

    @Override
    public boolean saveConfigBlock(ConfigModificationInfo configModificationInfo){
        return  xmlConfigEditorLocal.saveConfigBlock(configModificationInfo);
    }

    @Override
    public void cancelConfigEditing(ConfigModificationInfo configModificationInfo) {
        xmlConfigEditorLocal.cancelConfigEditing(configModificationInfo);
    }
}