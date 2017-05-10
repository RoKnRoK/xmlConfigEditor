package com.rok.xml.dto.config_dto;

import com.rok.xml.dto.LockInfo;

import java.io.Serializable;

/**
 * Created by RoK.
 * All rights reserved =)
 */
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

    public Serializable getLock() {
        return lockInfo.getLockObject();
    }

    public long getLockStartTimeMillis() {
        return lockInfo.getLockStartTime();
    }


}
