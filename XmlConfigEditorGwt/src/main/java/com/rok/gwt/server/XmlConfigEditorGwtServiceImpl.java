package com.rok.gwt.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.rok.gwt.client.service.XmlConfigEditorGwtService;
import com.rok.xml.config_dto.ConfigBlock;
import com.rok.xml.ejb.XmlConfigEditorLocal;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;

public class XmlConfigEditorGwtServiceImpl extends RemoteServiceServlet implements XmlConfigEditorGwtService {

    @EJB
    XmlConfigEditorLocal xmlConfigEditorLocal;

    private static final long serialVersionUID = -8248560938058853857L;



    @PostConstruct
    public void init(){
        System.out.println("XmlConfigEditorLocal: " + xmlConfigEditorLocal);
    }
    @Override
    public ConfigBlock getConfigBlock() {
        return  xmlConfigEditorLocal.getConfigBlock();

    }

    @Override
    public boolean saveConfigBlock(ConfigBlock configBlock){
        return  xmlConfigEditorLocal.saveConfigBlock(configBlock);
    }
}