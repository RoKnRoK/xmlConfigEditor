package com.rok.xml.rest;

import com.rok.xml.dto.config_dto.*;
import com.rok.xml.bean.XmlConfigEditorLocal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private static final Logger logger = LoggerFactory.getLogger(XmlConfigRestService.class.getName());

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
        logger.debug(configModificationInfo.toString());
        xmlConfigEditorLocal.saveConfigBlock(configModificationInfo);
    }

}
