package com.rok.xml.dto;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by roman.kulikov on 5/10/2017.
 * All rights reserved =D
 */
@XmlRootElement
public class LockInfo implements Serializable{


    private static final long serialVersionUID = -6320440594921061648L;

    private String lockObject;
    private long lockStartTime;

    public LockInfo() {
        this.lockObject = null;
        this.lockStartTime = -1;
    }

    public LockInfo(String lockObject, long lockStartTime) {
        this.lockObject = lockObject;
        this.lockStartTime = lockStartTime;
    }

    public String getLockObject() {
        return lockObject;
    }

    public long getLockStartTime() {
        return lockStartTime;
    }

}
