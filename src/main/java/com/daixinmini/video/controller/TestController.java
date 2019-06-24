package com.daixinmini.video.controller;

import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * <p>Project: video </p>
 * <p>Description: </p>
 * <p>Copyright (c) 2019 http://www.daixinmini.com (Wuhan) Co., Ltd.</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:380025303@qq.com">Dai Xin</a>
 */
@RestController
@RequestMapping("/daixinmini/test")
public class TestController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("test")
    @ApiOperation("测试")
    public void handleVideoParseUrl() throws UnsupportedEncodingException {
        String a = URLEncoder.encode("中文", "UTF-8");
        logger.info(a);
    }

}