package com.rok.xml.api;

import java.io.IOException;

/**
 * Created by RoK on 11.07.2015.
 * All rights reserved =)
 */
public interface ConfigLocker {
    boolean isConfigLockedBySomeoneElse();
    void tryLockConfig();
    void unlockConfig();
}
