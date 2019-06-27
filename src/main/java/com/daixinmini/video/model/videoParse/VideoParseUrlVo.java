package com.daixinmini.video.model.videoParse;

/**
 * <p>Project: video </p>
 * <p>Description: </p>
 * <p>Copyright (c) 2019 http://www.daixinmini.com (Wuhan) Co., Ltd.</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:380025303@qq.com">Dai Xin</a>
 */
public class VideoParseUrlVo implements Comparable<VideoParseUrlVo> {
    private Integer id;
    private String name;
    private String url;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int compareTo(VideoParseUrlVo o) {
        return o.getId() - this.id;
    }
}