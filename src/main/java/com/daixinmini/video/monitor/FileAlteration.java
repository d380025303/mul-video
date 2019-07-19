package com.daixinmini.video.monitor;

import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * <p>Project: mul-video </p>
 * <p>Description: </p>
 * <p>Copyright (c) 2019 http://www.daixinmini.com (Wuhan) Co., Ltd.</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:380025303@qq.com">Dai Xin</a>
 */
@Component
public class FileAlteration implements FileAlterationListener {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onStart(FileAlterationObserver observer) {
    }

    @Override
    public void onDirectoryCreate(File directory) {
        logger.info("onDirectoryCreate");
    }

    @Override
    public void onDirectoryChange(File directory) {
        logger.info("onDirectoryChange");
    }

    @Override
    public void onDirectoryDelete(File directory) {
        logger.info("onDirectoryDelete");
    }

    @Override
    public void onFileCreate(File file) {
        logger.info("onFileCreate");
    }

    @Override
    public void onFileChange(File file) {
        logger.info("onFileChange");
    }

    @Override
    public void onFileDelete(File file) {
        logger.info("onFileDelete");
    }

    @Override
    public void onStop(FileAlterationObserver observer) {

    }
}