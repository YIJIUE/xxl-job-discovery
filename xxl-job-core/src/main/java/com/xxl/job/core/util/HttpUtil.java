package com.xxl.job.core.util;

import com.alibaba.fastjson.JSON;
import com.xxl.job.core.biz.AdminBiz;
import com.xxl.job.core.biz.model.HandleCallbackParam;
import com.xxl.job.core.biz.model.ReturnT;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author YIJIUE
 */
public class HttpUtil {

    // 执行器执行接口
    public final static String executorApi = "/v1/executor/api";

    public final static String callBackApi = "/xxl-job-admin" + AdminBiz.MAPPING;

    public static String sendHttpPost(String url, Object regJson) throws Exception {
        String str = JSON.toJSONString(regJson);
        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("Content-Type", "application/json");
        httpPost.setEntity(new StringEntity(str,"UTF-8"));

        CloseableHttpResponse response = httpClient.execute(httpPost);
        HttpEntity entity = response.getEntity();
        String responseContent = EntityUtils.toString(entity, "UTF-8");

        response.close();
        httpClient.close();
        return responseContent;
    }

}
