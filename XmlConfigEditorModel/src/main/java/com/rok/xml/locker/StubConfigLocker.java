package com.rok.xml.locker;

import com.rok.xml.api.ConfigLocker;
import com.rok.xml.dto.LockInfo;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

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
    public LockInfo tryLockConfig() {
        return new LockInfo(UUID.randomUUID(), new Date().getTime());
    }

    @Override
    public void unlockConfig(Serializable lock)  {

    }
}
