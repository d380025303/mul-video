package com.daixinmini.video.config;

import com.daixinmini.video.monitor.FileAlteration;
import com.daixinmini.video.util.BasicUtil;
import com.daixinmini.video.util.InitUtil;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * <p>Project: mul-video </p>
 * <p>Description: </p>
 * <p>Copyright (c) 2019 http://www.daixinmini.com (Wuhan) Co., Ltd.</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:380025303@qq.com">Dai Xin</a>
 */
@Component
public class MonitorInitializer implements ApplicationRunner {
    @Autowired
    private FileAlteration fileAlteration;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("MonitorInitializer start...");
        FileAlterationObserver observer = new FileAlterationObserver(BasicUtil.getFolderListen());
        observer.addListener(fileAlteration);
        FileAlterationMonitor monitor = new FileAlterationMonitor(1000);
        monitor.addObserver(observer);
        monitor.start();
    }
}