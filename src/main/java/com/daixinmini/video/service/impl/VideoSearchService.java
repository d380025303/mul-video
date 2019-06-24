package com.daixinmini.video.service.impl;

import java.io.IOException;
import java.net.URLEncoder;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.daixinmini.video.consts.BaseConst;
import com.daixinmini.video.model.videoSearch.MovieVo;
import com.daixinmini.video.model.videoSearch.SearchVo;
import com.daixinmini.video.model.videoSearch.VideoSearchRespVo;
import com.daixinmini.video.service.IVideoSearchService;
import com.daixinmini.video.util.BasicUtil;
import com.daixinmini.video.util.CrawlerHttpUtil;
import com.daixinmini.video.util.JsonUtil;
import com.daixinmini.video.util.ParseXmlUtil;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * <p>Project: video </p>
 * <p>Description: </p>
 * <p>Copyright (c) 2019 http://www.daixinmini.com (Wuhan) Co., Ltd.</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:380025303@qq.com">Dai Xin</a>
 */
@Service
public class VideoSearchService implements IVideoSearchService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public VideoSearchRespVo search4Qq(String content) throws Exception {
        if (BasicUtil.isEmpty(content)) {
            return new VideoSearchRespVo();
        }
        String a = URLEncoder.encode(content, "UTF-8");
        String format = MessageFormat.format(BaseConst.QQ_SEARCH_URL, a);

        String sendGet = CrawlerHttpUtil.sendGet(format);

        logger.info(sendGet);
        List<SearchVo> searchVoList = parseQqSearchResult(sendGet);

        VideoSearchRespVo respVo = new VideoSearchRespVo();
        respVo.setSearchVoList(searchVoList);
        return respVo;
    }

    @Override
    public List<MovieVo> movie4Qq(String url) throws IOException {
        List<MovieVo> list = new ArrayList<>();
        if (BasicUtil.isEmpty(url)) {
            return list;
        }
        String sendGet = CrawlerHttpUtil.sendGet(url);
        org.jsoup.nodes.Document document = Jsoup.parse(sendGet);

        Elements episode = document.getElementsByAttributeValue("data-tpl", "episode");
        if (episode != null && episode.size() > 0) {
            org.jsoup.nodes.Element element = episode.get(0);
            Elements itemEpisodeElements = element.getElementsByClass("item");

            for (int i = 0; i < itemEpisodeElements.size(); i++) {
                org.jsoup.nodes.Element itemEpisodeElement = itemEpisodeElements.get(i);
                Elements imgEpisodeElements = itemEpisodeElement.getElementsByTag("img");
                Elements aEpisodeElements = itemEpisodeElement.getElementsByTag("a");

                org.jsoup.nodes.Element imgEpisodeElement = null;
                if (imgEpisodeElements != null && imgEpisodeElements.size() > 0) {
                    imgEpisodeElement = imgEpisodeElements.get(0);
                }

                org.jsoup.nodes.Element aEpisodeElement = aEpisodeElements.get(0);
                String targetUrl = "https://v.qq.com" + aEpisodeElement.attr("href");

                String num = aEpisodeElement.text();
                if (imgEpisodeElement != null) {
                    if (imgEpisodeElement.attr("src").contains("yu")) {
                        num = "预" + num;
                    } else {
                        num = "vip" + num;
                    }
                }
                MovieVo vo = new MovieVo();
                vo.setNum(num);
                vo.setUrl(targetUrl);
                list.add(vo);
            }
        } else {
            Elements picTitle = document.getElementsByAttributeValue("data-tpl", "pic-title");
            org.jsoup.nodes.Element element = picTitle.get(0);
            Elements aEpisodeElements = element.getElementsByTag("a");
            org.jsoup.nodes.Element aEpisodeElement = aEpisodeElements.get(0);
            String targetUrl = "https://v.qq.com" + aEpisodeElement.attr("href");
            String num = aEpisodeElement.text();
            MovieVo vo = new MovieVo();
            vo.setNum(num);
            vo.setUrl(targetUrl);
            list.add(vo);
        }
        return list;
    }

    @Override
    public VideoSearchRespVo search4Iqiyi(String content) throws Exception {
        if (BasicUtil.isEmpty(content)) {
            return new VideoSearchRespVo();
        }
        String a = URLEncoder.encode(content, "UTF-8");
        String format = MessageFormat.format(BaseConst.IQIYI_SEARCH_URL, a);

        String sendGet = CrawlerHttpUtil.sendGet(format);

        List<SearchVo> searchVos = parseIqiyiSearchResult(sendGet);

        VideoSearchRespVo respVo = new VideoSearchRespVo();
        respVo.setSearchVoList(searchVos);
        return respVo;
    }

    @Override
    public VideoSearchRespVo search4Youku(String content) throws Exception {
        if (BasicUtil.isEmpty(content)) {
            return new VideoSearchRespVo();
        }
        String a = URLEncoder.encode(content, "UTF-8");
        String format = MessageFormat.format(BaseConst.YOUKU_SEARCH_URL, a);

        String sendGet = CrawlerHttpUtil.sendGet(format);

        org.jsoup.nodes.Document document = Jsoup.parse(sendGet);

        List<SearchVo> list = new ArrayList<>();
        Elements elementsScript = document.getElementsByTag("script");
        for (org.jsoup.nodes.Element element : elementsScript) {
            String html = element.html();
            if (html.startsWith("bigview.view") && html.contains("bpmodule-main")) {
                String s = html.substring(13, html.length() - 1);
                logger.info(s);
                JsonNode jsonNode = JsonUtil.asJson(s);
                JsonNode node = jsonNode.get("html");
                String mainHtml = node.toString();
                if (mainHtml != null) {
                    mainHtml = mainHtml//
                            .replaceAll("\"\\\\n", "")//
                            .replaceAll("\\\\n\"", "")//
                            .replaceAll("\"\\\\t", "")//
                            .replaceAll("\\\\t\"", "")//
                            .replaceAll("\\\\n", "")//
                            .replaceAll("\\\\t", "")//
                            .replaceAll("\\\\&quot;", "")//
                            .replaceAll("\\\\", "");
                    org.jsoup.nodes.Document mainHtmlD = Jsoup.parse(mainHtml);
                    Elements elementsByAttributeValue = mainHtmlD.getElementsByAttributeValue("data-spm", "dtitle");
                    for (org.jsoup.nodes.Element element1 : elementsByAttributeValue) {
                        String name = element1.attr("title").toString();
                        String url = element1.attr("href").toString();
                        if (!url.startsWith("https")) {
                            url = "https:" + url;
                        }
                        SearchVo vo = new SearchVo();
                        vo.setName(name);
                        vo.setUrl(url);
                        vo.setType("youku");
                        list.add(vo);
                    }
                }
            }

        }
        VideoSearchRespVo respVo = new VideoSearchRespVo();
        respVo.setSearchVoList(list);
        return respVo;
    }

    @Override
    public List<MovieVo> movie4Youku(String url) throws IOException {
        List<MovieVo> list = new ArrayList<>();
        if (BasicUtil.isEmpty(url)) {
            return list;
        }
        String sendGet = CrawlerHttpUtil.sendGet(url);
        org.jsoup.nodes.Document document = Jsoup.parse(sendGet);

        org.jsoup.nodes.Element pageConfig = document.getElementById("pageconfig-code");
        String text1 = pageConfig.html().replaceAll(" ", "");
        text1 = text1.substring(text1.indexOf("showid:"), text1.length() - 1);
        text1 = text1.substring(text1.indexOf("showid:") + 8, text1.indexOf(",") - 1);

        String text2 = pageConfig.html().replaceAll(" ", "");
        text2 = text2.substring(text2.indexOf("videoCategoryId:"), text2.length() - 1);
        text2 = text2.substring(text2.indexOf("videoCategoryId:") + 17, text2.indexOf(",") - 1);

        for (int i = 1; i < 10; i++) {
            String format = MessageFormat.format(BaseConst.YOUKU_MOVIE_URL, text1, text2, i);
            String json = CrawlerHttpUtil.sendGet(format);
            JsonNode jsonNode = JsonUtil.asJson(json);
            String html = jsonNode.get("html").toString();
            if (BasicUtil.isEmpty(html)) {
                break;
            }
            html = html.substring(1, html.length() - 1);
            html = html//
                    .replaceAll("\\\\n\\\\t\\\\t", " ")//
                    .replaceAll("\\\\n\\\\t", " ")
                    .replaceAll("\\\\n", "")//
                    .replaceAll("\\\\t", "")
                    .replaceAll("\\\\", "");
            if (BasicUtil.isEmpty(html)) {
                break;
            }
            org.jsoup.nodes.Document mainHtmlD = Jsoup.parse(html);
            Elements elementsByAttributes = mainHtmlD.getElementsByClass("item-num");
            for (org.jsoup.nodes.Element element1 : elementsByAttributes) {
                Elements elementsByTag = element1.getElementsByTag("a");
                org.jsoup.nodes.Element elementA = elementsByTag.get(0);

                // 是否是预告
                boolean isPreview = false;
                Elements elementsByClass = elementA.getElementsByClass("label-preview");
                if (elementsByClass.size() > 0) {
                    isPreview = true;
                }
                Elements snNum = elementA.getElementsByClass("sn_num");
                org.jsoup.nodes.Element num = snNum.get(0);

                String name = num.text();
                if (isPreview){
                    name = "预" + num.text();
                }

                String movieUrl = elementA.attr("href");
                if (!movieUrl.startsWith("http")) {
                    movieUrl = "https:" + movieUrl;
                }
                MovieVo vo = new MovieVo();
                vo.setNum(name);
                vo.setUrl(movieUrl);
                list.add(vo);
            }
        }



        return list;
    }

    @SuppressWarnings("unchecked")
    private List<SearchVo> parseIqiyiSearchResult(String result) throws IOException {
        JsonNode jsonNode = JsonUtil.asJson(result);

        List<JsonNode> nodeList = JsonUtil.asArray(jsonNode.get("data"));

        List<SearchVo> list = new ArrayList<>();
        for (JsonNode node : nodeList) {
            String url = node.get("link").toString();
            String name = node.get("name").toString();
            SearchVo vo = new SearchVo();
            vo.setName(name);
            vo.setUrl(url);
            vo.setType("iqiyi");
            list.add(vo);
        }
        return list;
    }

    @SuppressWarnings("unchecked")
    private List<SearchVo> parseQqSearchResult(String result) throws DocumentException {
        Document document = ParseXmlUtil.parseXml(result);
        Element element = document.getRootElement();
        List<Element> itemList = element.elements("item");

        List<SearchVo> list = new ArrayList<>();
        for (Element item : itemList) {
            String url = item.elementText("url");
            String name = item.elementText("tt");
            if (BasicUtil.isEmpty(url) || BasicUtil.isEmpty(name)) {
                continue;
            }
            SearchVo vo = new SearchVo();
            vo.setName(name);
            vo.setUrl(url);
            vo.setType("qq");
            list.add(vo);
        }
        return list;
    }
}