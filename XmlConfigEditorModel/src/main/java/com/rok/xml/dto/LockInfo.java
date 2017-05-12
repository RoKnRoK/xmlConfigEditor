package com.rok.xml.dto;

import java.io.Serializable;

/**
 * Created by roman.kulikov on 5/10/2017.
 * All rights reserved =D
 */
public class LockInfo implements Serializable{


    private static final long serialVersionUID = -6320440594921061648L;
    private Serializable lockObject;
    private long lockStartTime;

    public LockInfo() {
        this.lockObject = null;
        this.lockStartTime = -1;
    }

    public LockInfo(Serializable lockObject, long lockStartTime) {
        this.lockObject = lockObject;
        this.lockStartTime = lockStartTime;
    }

    public Serializable getLockObject() {
        return lockObject;
    }

    public long getLockStartTime() {
        return lockStartTime;
    }

}
