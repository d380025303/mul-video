package com.daixinmini.video.service.impl;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.daixinmini.video.consts.BaseConst;
import com.daixinmini.video.model.videoParse.VideoParseUrlVo;
import com.daixinmini.video.service.IVideoParseUrlService;
import com.daixinmini.video.util.CrawlerHttpUtil;
import com.daixinmini.video.util.DateUtil;
import com.daixinmini.video.util.JsonUtil;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * <p>Project: video </p>
 * <p>Description: </p>
 * <p>Copyright (c) 2019 http://www.daixinmini.com (Wuhan) Co., Ltd.</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:380025303@qq.com">Dai Xin</a>
 */
@Service
public class VideoParseUrlService implements IVideoParseUrlService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public List<VideoParseUrlVo> handleVideoParseUrl() {
        String videoJson = getVideoJson();
        return parseJson(videoJson);
    }

    /**
     * 解析json获取视频解析url列表
     * @param videoJson
     * @return
     */
    private List<VideoParseUrlVo> parseJson(String videoJson) {
        List<VideoParseUrlVo> voList = new ArrayList<>();
        try {
            JsonNode jsonNode = JsonUtil.asJson(videoJson);
            List<JsonNode> list = JsonUtil.asArray(jsonNode.get("list"));
            int count = list.size();
            for (JsonNode node : list) {
                VideoParseUrlVo vo = new VideoParseUrlVo();
                String name = JsonUtil.getNodeAsString(node, "name");
                String url = JsonUtil.getNodeAsString(node, "url").replaceAll("\"", "");
                if (StringUtils.isEmpty(url)) {
                    continue;
                }
                vo.setName(name);
                vo.setUrl(url);
                vo.setId(count);
                count--;
                voList.add(vo);
            }
            Collections.reverse(voList);
        } catch (IOException e) {
            logger.error("解析 视频解析url json失败 ---- e: " + e.getMessage(), e);
        }
        return voList;
    }

    /**
     * 获取json获取视频解析url列表
     * @return
     */
    private String getVideoJson() {
        String json = getVideoJsonFromDesk();
        if (Strings.isNotEmpty(json)) {
            return json;
        }
        try {
            String sendGet = CrawlerHttpUtil.sendGet(BaseConst.VIDEO_PARSE_URL);
            File file = getFile();
            FileUtils.writeStringToFile(file, sendGet, "UTF-8", false);
            return sendGet;
        } catch (IOException e) {
            logger.error("获取视频解析url失败 ---- e: " + e.getMessage(), e);
        }
        return null;
    }

    private File getFile() {
        Timestamp now = DateUtil.now();
        return new File("VideoParseUrl" + DateUtil.yyyyMMdd(now));
    }

    private String getVideoJsonFromDesk() {
        String s = null;
        try {
            File file = getFile();
            if (file.exists()) {
                s = FileUtils.readFileToString(file, "UTF-8");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }
}