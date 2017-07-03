package com.rok.xml.rest;

import com.rok.xml.dto.config_dto.ConfigModificationInfo;
import com.rok.xml.bean.XmlConfigEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

/**
 * Created by roman.kulikov on 6/6/2017.
 * All rights reserved =D
 */
@RestController
@RequestMapping("/rest/config")
public class XmlConfigEditorController {

    // this works too, but Java EE dependent
//    @EJB(mappedName = "XmlConfigEditorEJB")
//    private XmlConfigEditorRemote xmlConfigEditorBean;

    @Autowired
    @Qualifier("xmlConfigEditorBean")
    private XmlConfigEditor xmlConfigEditorBean;

    @RequestMapping(value = "/save", method = RequestMethod.PUT, produces="application/json")
    @ResponseBody
    public void saveConfig(@RequestBody ConfigModificationInfo configModificationInfo) {
        xmlConfigEditorBean.saveConfigBlock(configModificationInfo);
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET, produces="application/json")
    @ResponseBody
    public ConfigModificationInfo getConfig() {
        return xmlConfigEditorBean.getConfigModificationInfo();
    }

}
