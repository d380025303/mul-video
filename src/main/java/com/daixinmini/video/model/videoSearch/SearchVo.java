package com.daixinmini.video.model.videoSearch;

import java.util.List;

/**
 * <p>Project: video </p>
 * <p>Description: </p>
 * <p>Copyright (c) 2019 http://www.daixinmini.com (Wuhan) Co., Ltd.</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:380025303@qq.com">Dai Xin</a>
 */
public class SearchVo {
    private String name;
    private String url;
    private String type;
    private List<MovieVo> movieVoList;

    public List<MovieVo> getMovieVoList() {
        return movieVoList;
    }

    public void setMovieVoList(List<MovieVo> movieVoList) {
        this.movieVoList = movieVoList;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
}