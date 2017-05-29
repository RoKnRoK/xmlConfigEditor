package com.rok.xml.rest;

import com.rok.xml.dto.config_dto.*;
import com.rok.xml.ejb.XmlConfigEditorLocal;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

/**
 * Created by roman.kulikov on 5/12/2017.
 * All rights reserved =D
 */
@ApplicationPath("/rest")
@Path("/config")
public class XmlConfigRestService extends Application{

    @SuppressWarnings("unused")
    @EJB
    private XmlConfigEditorLocal xmlConfigEditorLocal;

    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public ConfigModificationInfo getConfig(){
        return xmlConfigEditorLocal.getConfigModificationInfo();
    }


    @PUT
    @Path("/save")
    @Consumes(MediaType.APPLICATION_JSON)
    public void saveConfig(ConfigModificationInfo configModificationInfo){
        xmlConfigEditorLocal.saveConfigBlock(configModificationInfo);
    }

}
