package com.rok.xml.locker;

import com.rok.xml.api.ConfigLocker;

import java.io.File;
import java.io.IOException;

/**
 * Created by RoK on 11.07.2015.
 * All rights reserved =)
 */
public class FNConfigLocker implements ConfigLocker {
    private File lockingConfig;
    private boolean isConfigLockedBySomeoneElse;


    public FNConfigLocker(File lockingConfig) {
        this.isConfigLockedBySomeoneElse = false;
        this.lockingConfig = lockingConfig;
    }

    @Override
    public boolean isConfigLockedBySomeoneElse() {
        return isConfigLockedBySomeoneElse;
    }

    @Override
    public void tryLockConfig() {

    }

    @Override
    public void unlockConfig() {

    }
}
