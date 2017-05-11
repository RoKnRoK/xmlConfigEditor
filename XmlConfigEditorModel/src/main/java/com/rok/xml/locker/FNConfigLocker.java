package com.rok.xml.locker;

import com.rok.xml.api.ConfigLocker;
import com.rok.xml.dto.LockInfo;

import java.io.File;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * Created by RoK on 11.07.2015.
 * All rights reserved =)
 */
public class FNConfigLocker implements ConfigLocker {
    private File lockingConfig;


    public FNConfigLocker(File lockingConfig) {
        this.lockingConfig = lockingConfig;
    }


    @Override
    public LockInfo tryLockConfig() {
        return new LockInfo(UUID.randomUUID(), new Date().getTime());
    }

    @Override
    public boolean unlockConfig(Serializable lock) {

        return true;
    }
}
