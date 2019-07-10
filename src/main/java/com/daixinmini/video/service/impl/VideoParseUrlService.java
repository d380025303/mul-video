package com.daixinmini.video.service.impl;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.daixinmini.video.util.BasicUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    public void loadVideoParse() {
        try {
            String videoJson = CrawlerHttpUtil.sendGet(BaseConst.VIDEO_PARSE_URL);
            List<VideoParseUrlVo> parseUrlVoList = parseJson(videoJson);

            File file = BasicUtil.getParseUrlFile();

            ObjectMapper mapper = JsonUtil.buildObjectMapper();
            List<VideoParseUrlVo> oldList = null;
            if (file.exists()) {
                oldList = mapper.readValue(file, new TypeReference<List<VideoParseUrlVo>>() {
                });
            }
            if (oldList == null) {
                mapper.writeValue(file, parseUrlVoList);
            } else if (oldList.size() != parseUrlVoList.size()) {
                Map<String, VideoParseUrlVo> oldMap = new HashMap<>();
                for (VideoParseUrlVo vo : oldList) {
                    oldMap.put(vo.getUrl(), vo);
                }
                for (VideoParseUrlVo vo : parseUrlVoList) {
                    if (!oldMap.containsKey(vo.getUrl())) {
                        oldList.add(vo);
                    }
                }
                mapper.writeValue(file, parseUrlVoList);
            }
        } catch (IOException e) {
            logger.error("获取视频解析url失败 ---- e: " + e.getMessage(), e);
        }
    }

    @Override
    public List<VideoParseUrlVo> getVideoParse() {
        try {
            File file = BasicUtil.getParseUrlFile();
            ObjectMapper mapper = JsonUtil.buildObjectMapper();
            List<VideoParseUrlVo> oldList = null;
            if (file.exists()) {
                oldList = mapper.readValue(file, new TypeReference<List<VideoParseUrlVo>>() {
                });
            }
            if (oldList == null || oldList.size() == 0) {
                loadVideoParse();
                file = BasicUtil.getParseUrlFile();
                oldList = mapper.readValue(file, new TypeReference<List<VideoParseUrlVo>>() {
                });
            }
            for (int i = 0; i < oldList.size(); i++) {
                oldList.get(i).setSeqNo(i + 1);
            }
            return oldList;
        } catch (IOException e) {
            logger.error("读取视频解析url失败 ---- e: " + e.getMessage(), e);
        }
        return new ArrayList<>();
    }

    @Override
    public void addHotNum(VideoParseUrlVo vo) throws IOException {
        File file = BasicUtil.getParseUrlFile();
        ObjectMapper mapper = JsonUtil.buildObjectMapper();
        List<VideoParseUrlVo> oldList = mapper.readValue(file, new TypeReference<List<VideoParseUrlVo>>() {
            });
        String url = vo.getUrl();
        for (VideoParseUrlVo videoParseUrlVo : oldList) {
            if (videoParseUrlVo.getUrl().compareTo(url) == 0) {
                videoParseUrlVo.setHotNum(videoParseUrlVo.getHotNum() + 1);
            }
        }
        mapper.writeValue(file, oldList);
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
            for (JsonNode node : list) {
                VideoParseUrlVo vo = new VideoParseUrlVo();
                String name = JsonUtil.getNodeAsString(node, "name").replaceAll("\"", "");
                String url = JsonUtil.getNodeAsString(node, "url").replaceAll("\"", "");
                if (StringUtils.isEmpty(url)) {
                    continue;
                }
                vo.setName(name);
                vo.setUrl(url);
                vo.setHotNum(0);
                voList.add(vo);
            }
            Collections.reverse(voList);
        } catch (IOException e) {
            logger.error("解析 视频解析url json失败 ---- e: " + e.getMessage(), e);
        }
        return voList;
    }

}