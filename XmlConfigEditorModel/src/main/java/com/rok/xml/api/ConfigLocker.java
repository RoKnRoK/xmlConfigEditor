package com.rok.xml.api;

import com.rok.xml.dto.LockInfo;

import java.io.Serializable;

/**
 * Created by RoK on 11.07.2015.
 * All rights reserved =)
 */
public interface ConfigLocker {
    LockInfo tryLockConfig();
    boolean unlockConfig(Serializable lock);
}
