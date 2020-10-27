package com.week2.httptest

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import java.io.IOException;

/**
 * @author zx
 * @description 使用 HttpClient 或 OkHttp 访问 http://localhost:8801
 */
 
public class HttpTest{

    public static void doGet() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet("http://localhost:8088/api/hello");
        CloseableHttpResponse response;
        try {
            response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            System.out.println("响应状态码：" + response.getStatusLine());
            String result = IOUtils.toString(entity.getContent());
            System.out.println("返回内容：" + result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        //调用doGet()方法
        doGet();
    }
}
