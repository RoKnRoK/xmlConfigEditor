package com.rok.xml.timer;

import com.rok.xml.Constants;
import com.rok.xml.dto.config_dto.ConfigModificationInfo;
import com.rok.xml.bean.XmlConfigEditorLocal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.ejb.*;
import java.io.*;
import java.util.Date;

/**
 * Created by roman.kulikov on 5/10/2017.
 * All rights reserved =D
 */
@SuppressWarnings("unused")
@Singleton
public class XmlLockCanceller {


    @Resource
    private TimerService timerService;

    @EJB
    private XmlConfigEditorLocal xmlConfigEditor;

    private static final Logger logger = LoggerFactory.getLogger(XmlLockCanceller.class.getName());

    public void startLockValidityTimer(ConfigModificationInfo configModificationInfo) {
        Serializable lock = configModificationInfo.getLock();
        if (lock == null) {
            logger.warn("No lock be tracked.");
            return;
        }
        logger.trace("Tracking of lock {} has started", lock);
        TimerConfig timerConfig = new TimerConfig();
        timerConfig.setInfo(configModificationInfo);
        timerService.createIntervalTimer((long) (Constants.EDITING_TIME_IN_MILLIS*1.2), 100, timerConfig);
    }

    @Timeout
    public void timeout(Timer timer) {
        Serializable timerInfo = timer.getInfo();
        if (!(timerInfo instanceof ConfigModificationInfo)) {
            logger.warn("Timer with info different from ConfigModificationInfo ignored");
            return;
        }
        ConfigModificationInfo configModificationInfo = (ConfigModificationInfo) timerInfo;
        logger.trace("Checking if lock {} expired", configModificationInfo.getLock());

        long lockStartTime = configModificationInfo.getLockStartTimeMillis();
        long currentTime = new Date().getTime();

        if (currentTime - lockStartTime > Constants.EDITING_TIME_IN_MILLIS) {
            logger.warn("Lock expired; removing lock");
            timer.cancel();
            xmlConfigEditor.cancelConfigEditing(configModificationInfo);
        }
    }
}
