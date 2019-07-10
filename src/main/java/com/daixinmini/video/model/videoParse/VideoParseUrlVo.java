package com.daixinmini.video.model.videoParse;

/**
 * <p>Project: video </p>
 * <p>Description: </p>
 * <p>Copyright (c) 2019 http://www.daixinmini.com (Wuhan) Co., Ltd.</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:380025303@qq.com">Dai Xin</a>
 */
public class VideoParseUrlVo implements Comparable<VideoParseUrlVo> {
    private Integer hotNum;
    private String name;
    private String url;
    private Integer seqNo;

    public Integer getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(Integer seqNo) {
        this.seqNo = seqNo;
    }

    public Integer getHotNum() {
        return hotNum;
    }

    public void setHotNum(Integer hotNum) {
        this.hotNum = hotNum;
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
        int result = o.getHotNum() - this.hotNum;
        if (result == 0) {
           return o.getUrl().compareTo(this.getUrl());
        }
        return result;
    }
}