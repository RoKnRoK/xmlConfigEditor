package com.rok.xml.dto.config_dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rok.xml.dto.LockInfo;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by RoK.
 * All rights reserved =)
 */
@XmlRootElement //for automatic conversion to JSON and XML in REST
public class ConfigModificationInfo implements Serializable {

    private static final long serialVersionUID = -2968138039395794332L;

    private ConfigBlock configBlock;
    private LockInfo lockInfo;

    public ConfigModificationInfo() {
        this.configBlock = null;
    }

    public ConfigModificationInfo(ConfigBlock configBlock, LockInfo lockInfo) {
        this.configBlock = configBlock;
        this.lockInfo = lockInfo;
    }

    public ConfigBlock getConfigBlock() {
        return configBlock;
    }
    public void setConfigBlock(ConfigBlock configBlock) {
        this.configBlock = configBlock;
    }


    public LockInfo getLockInfo() {
        return lockInfo;
    }
    public void setLockInfo(LockInfo lockInfo) {
        this.lockInfo = lockInfo;
    }

    @JsonIgnore
    public Serializable getLock() {
        return lockInfo.getLockObject();
    }
    @JsonIgnore
    public long getLockStartTimeMillis() {
        return lockInfo.getLockStartTime();
    }


}
