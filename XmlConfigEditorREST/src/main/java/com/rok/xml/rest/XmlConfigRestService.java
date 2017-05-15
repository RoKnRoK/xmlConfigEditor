package com.rok.xml.rest;

import com.rok.xml.dto.config_dto.ConfigModificationInfo;
import com.rok.xml.ejb.XmlConfigEditorLocal;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

/**
 * Created by roman.kulikov on 5/12/2017.
 * All rights reserved =D
 */
@Stateless
@ApplicationPath("/rest")
public class XmlConfigRestService extends Application{

    @SuppressWarnings("unused")
    @EJB
    private XmlConfigEditorLocal xmlConfigEditorLocal;

    @GET
    @Path("/config")
    @Produces(MediaType.APPLICATION_JSON)
    public ConfigModificationInfo getConfig(){
        ConfigModificationInfo configModificationInfo = xmlConfigEditorLocal.getConfigModificationInfo();
        return configModificationInfo;
    }

}
