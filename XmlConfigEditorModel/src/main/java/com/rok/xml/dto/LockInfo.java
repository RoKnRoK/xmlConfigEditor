package com.rok.xml.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.UUID;

/**
 * Created by roman.kulikov on 5/10/2017.
 * All rights reserved =D
 */
@XmlRootElement
public class LockInfo implements Serializable{


    private static final long serialVersionUID = -6320440594921061648L;
    @XmlElements({
            @XmlElement(name = "lock", type=String.class),
            @XmlElement(name = "lock", type=UUID.class),
    })
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
