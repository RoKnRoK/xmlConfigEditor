package com.rok.xml.dto;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

import com.rok.xml.Constants;

/**
 * Created by roman.kulikov on 5/10/2017.
 * All rights reserved =D
 */
@XmlRootElement
public class LockInfo implements Serializable{


    private static final long serialVersionUID = -6320440594921061648L;

    private String lockObject;
    private long lockStartTime;
    private long lockDuration;

    public LockInfo() {
        this.lockObject = null;
        this.lockStartTime = new Date().getTime();
        this.lockDuration = Constants.EDITING_TIME_IN_MILLIS;
    }

    public LockInfo(String lockObject) {
        this();
        this.lockObject = lockObject;
    }

    public String getLockObject() {
        return lockObject;
    }

    public long getLockStartTime() {
        return lockStartTime;
    }

    public long getLockDuration() {
        return lockDuration;
    }
}
