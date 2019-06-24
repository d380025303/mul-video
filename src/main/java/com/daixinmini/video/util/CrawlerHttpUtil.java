package com.daixinmini.video.util;


import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * <p>Project: demo </p>
 * <p>Description: </p>
 * <p>Copyright (c) 2019 Karrytech (Shanghai/Beijing) Co., Ltd.</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:daixin@karrytech.com">Dai Xin</a>
 */
public class CrawlerHttpUtil {

    public final static String userAgent = "ozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36";

    public static String sendGet(String url) throws IOException {
        HttpGet httpGet = new HttpGet(url);
        httpGet.addHeader("User-Agent", userAgent);
        RequestConfig requestConfig = RequestConfig.custom().build();
        httpGet.setConfig(requestConfig);
        CloseableHttpClient httpClient = HttpClients.custom().build();
        HttpResponse response = httpClient.execute(httpGet);
        String responseText = EntityUtils.toString(response.getEntity(), "UTF-8");
        return responseText;
    }

}