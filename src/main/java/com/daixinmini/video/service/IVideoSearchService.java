package com.daixinmini.video.service;

import com.daixinmini.video.model.videoSearch.MovieVo;
import com.daixinmini.video.model.videoSearch.VideoSearchRespVo;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * <p>Project: video </p>
 * <p>Description: </p>
 * <p>Copyright (c) 2019 http://www.daixinmini.com (Wuhan) Co., Ltd.</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:380025303@qq.com">Dai Xin</a>
 */
public interface IVideoSearchService {
    /**
     * 腾讯视频搜索接口
     * @param content
     * @return
     */
    VideoSearchRespVo search4Qq(String content) throws Exception;

    /**
     * 腾讯视频剧集
     * @param url
     * @return
     */
    List<MovieVo> movie4Qq(String url) throws IOException;

    /**
     * 爱奇艺视频搜索接口
     * @param content
     * @return
     */
    VideoSearchRespVo search4Iqiyi(String content) throws Exception;

    /**
     * 优酷视频搜索接口
     * @param content
     * @return
     */
    VideoSearchRespVo search4Youku(String content) throws UnsupportedEncodingException, Exception;

    /**
     * 优酷视频剧集
     * @param url
     * @return
     */
    List<MovieVo> movie4Youku(String url) throws IOException;

}