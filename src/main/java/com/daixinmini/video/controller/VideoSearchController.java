package com.daixinmini.video.controller;

import com.daixinmini.video.model.videoParse.VideoParseUrlVo;
import com.daixinmini.video.model.videoSearch.MovieVo;
import com.daixinmini.video.model.videoSearch.SearchVo;
import com.daixinmini.video.model.videoSearch.VideoSearchReqVo;
import com.daixinmini.video.model.videoSearch.VideoSearchRespVo;
import com.daixinmini.video.service.IVideoParseUrlService;
import com.daixinmini.video.service.IVideoSearchService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * <p>Project: video </p>
 * <p>Description: </p>
 * <p>Copyright (c) 2019 http://www.daixinmini.com (Wuhan) Co., Ltd.</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:380025303@qq.com">Dai Xin</a>
 */
@RestController
@RequestMapping("/daixinmini/video")
public class VideoSearchController {

    @Autowired
    private IVideoSearchService videoSearchService;
    @Autowired
    private IVideoParseUrlService videoParseUrlService;

    @PostMapping("/searchList")
    @ApiOperation("搜索视频")
    public VideoSearchRespVo searchList(@RequestBody VideoSearchReqVo videoSearchReqVo) throws Exception {
        if ("qq".equals(videoSearchReqVo.getType())){
            VideoSearchRespVo respVo = videoSearchService.search4Qq(videoSearchReqVo.getContent());
            return respVo;
        } else if ("iqiyi".equals(videoSearchReqVo.getType())){
            VideoSearchRespVo respVo = videoSearchService.search4Iqiyi(videoSearchReqVo.getContent());
            return respVo;
        } else if ("youku".equals(videoSearchReqVo.getType())){
            VideoSearchRespVo respVo = videoSearchService.search4Youku(videoSearchReqVo.getContent());
            return respVo;
        }
        return null;
    }

    @PostMapping("/movieList")
    @ApiOperation("搜索剧集")
    public List<MovieVo> searchList(@RequestBody SearchVo searchVo) throws Exception {
        if ("qq".equals(searchVo.getType())){
            return videoSearchService.movie4Qq(searchVo.getUrl());
        } else if ("youku".equals(searchVo.getType())){
            return videoSearchService.movie4Youku(searchVo.getUrl());
        }
        return null;
    }

}