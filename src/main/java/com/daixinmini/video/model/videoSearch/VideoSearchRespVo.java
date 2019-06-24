package com.daixinmini.video.model.videoSearch;

import java.util.List;

/**
 * <p>Project: video </p>
 * <p>Description: </p>
 * <p>Copyright (c) 2019 http://www.daixinmini.com (Wuhan) Co., Ltd.</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:380025303@qq.com">Dai Xin</a>
 */
public class VideoSearchRespVo {
    private List<SearchVo> searchVoList;

    public List<SearchVo> getSearchVoList() {
        return searchVoList;
    }

    public void setSearchVoList(List<SearchVo> searchVoList) {
        this.searchVoList = searchVoList;
    }
}