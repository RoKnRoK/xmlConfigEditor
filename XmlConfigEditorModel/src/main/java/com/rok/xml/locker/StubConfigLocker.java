package com.rok.xml.locker;

import com.rok.xml.api.ConfigLocker;

import java.io.IOException;

/**
 * Created by RoK on 12.07.2015.
 * All rights reserved =)
 */
public class StubConfigLocker implements ConfigLocker {
    @Override
    public boolean isConfigLockedBySomeoneElse() {
        return false;
    }

    @Override
    public void tryLockConfig() {

    }

    @Override
    public void unlockConfig()  {

    }
}
