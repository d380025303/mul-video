package com.daixinmini.video.service;

import com.daixinmini.video.model.videoParse.VideoParseUrlVo;

import java.io.IOException;
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
     * 加载视频解析文件
     */
    void loadVideoParse();

    /**
     * 加载视频解析文件
     */
    List<VideoParseUrlVo> getVideoParse();

    /**
     * 增加热度
     * @param vo
     */
    void addHotNum(VideoParseUrlVo vo) throws IOException;
}