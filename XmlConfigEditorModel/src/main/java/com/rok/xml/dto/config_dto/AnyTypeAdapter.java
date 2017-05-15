package com.rok.xml.dto.config_dto;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Created by roman.kulikov on 5/15/2017.
 * All rights reserved =D
 */
public class AnyTypeAdapter extends XmlAdapter<Object, Object> {

    @Override
    public Object marshal(Object v) throws Exception {
        return v;
    }

    @Override
    public Object unmarshal(Object v) throws Exception {
        return v;
    }

}
