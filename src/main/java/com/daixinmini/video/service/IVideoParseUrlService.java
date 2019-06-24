package com.daixinmini.video.service;

import com.daixinmini.video.model.videoParse.VideoParseUrlVo;

import java.util.List;

/**
 * <p>Project: video </p>
 * <p>Description: </p>
 * <p>Copyright (c) 2019 http://www.daixinmini.com (Wuhan) Co., Ltd.</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:380025303@qq.com">Dai Xin</a>
 */
public interface IVideoParseUrlService {

    /**
     * 解析视频解析url
     */
    List<VideoParseUrlVo> handleVideoParseUrl();
}