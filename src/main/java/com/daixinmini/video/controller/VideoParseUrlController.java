package com.daixinmini.video.controller;

import com.daixinmini.video.model.videoParse.VideoParseUrlVo;
import com.daixinmini.video.service.IVideoParseUrlService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>Project: video </p>
 * <p>Description: </p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:380025303@qq.com">Dai Xin</a>
 */
@RestController
@RequestMapping("/daixinmini/videoparse")
public class VideoParseUrlController {

    @Autowired
    private IVideoParseUrlService videoParseUrlService;

//    @Scheduled(fixedRate = 5000)
    @GetMapping("handlevideoparseurl")
    @ApiOperation("获取视频解析接口列表")
    public List<VideoParseUrlVo> handleVideoParseUrl() {
        return videoParseUrlService.handleVideoParseUrl();
    }
}