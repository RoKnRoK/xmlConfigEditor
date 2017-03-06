package com.rok.xml.config_dto;

import javax.sql.rowset.serial.SerialArray;
import java.io.Serializable;

/**
 * Created by RoK.
 * All rights reserved =)
 */
public class ConfigModificationInfo implements Serializable {

    private static final long serialVersionUID = -2968138039395794332L;

    private ConfigBlock configBlock;
    private Serializable lock;

    public ConfigModificationInfo() {
        this.configBlock = null;
        this.lock = null;
    }

    public ConfigModificationInfo(ConfigBlock configBlock, Serializable lock) {
        this.configBlock = configBlock;
        this.lock = lock;
    }

    public ConfigBlock getConfigBlock() {
        return configBlock;
    }

    public void setConfigBlock(ConfigBlock configBlock) {
        this.configBlock = configBlock;
    }

    public Serializable getLock() {
        return lock;
    }

    public void setLock(Serializable lock) {
        this.lock = lock;
    }
}
