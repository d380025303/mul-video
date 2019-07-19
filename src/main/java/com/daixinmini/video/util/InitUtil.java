package com.daixinmini.video.util;

/**
 * <p>Project: mul-video </p>
 * <p>Description: </p>
 * <p>Copyright (c) 2019 http://www.daixinmini.com (Wuhan) Co., Ltd.</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:380025303@qq.com">Dai Xin</a>
 */
public class InitUtil {
    private static Boolean runAsJar = false;

    public static Boolean isRunAsJar() {
        return runAsJar;
    }

    public static void setRunAsJar(Boolean runAsJar) {
        InitUtil.runAsJar = runAsJar;
    }
}