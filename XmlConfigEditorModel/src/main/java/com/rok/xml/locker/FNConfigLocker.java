package com.rok.xml.locker;

import com.rok.xml.api.ConfigLocker;
import com.rok.xml.dto.LockInfo;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * Created by RoK on 11.07.2015.
 * All rights reserved =)
 */
@SuppressWarnings("unused")
public class FNConfigLocker implements ConfigLocker {


    public FNConfigLocker() {
    }


    @Override
    public LockInfo tryLockConfig() {
        return new LockInfo(UUID.randomUUID().toString());
    }

    @Override
    public boolean unlockConfig(Serializable lock) {

        return true;
    }
}
