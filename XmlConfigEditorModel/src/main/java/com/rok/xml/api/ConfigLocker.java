package com.rok.xml.api;

import java.io.IOException;
import java.io.Serializable;

/**
 * Created by RoK on 11.07.2015.
 * All rights reserved =)
 */
public interface ConfigLocker {
    boolean isConfigLockedBySomeoneElse();
    Serializable tryLockConfig();
    void unlockConfig(Serializable lock);
}
