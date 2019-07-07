package com.course.httpclient.cookies;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MyCookiesForPost {
    private String url;
    private ResourceBundle bundle;
    private CookieStore store;

    @BeforeTest
    public void beforeTest(){
        bundle = ResourceBundle.getBundle("application", Locale.CHINA);
        url = bundle.getString("test.url");
    }

    @Test
    public void  testGetCookies() throws IOException {
        String result;
        String uri = bundle.getString("getCookies.uri");
        String testUrl = this.url + uri;
        HttpGet get = new HttpGet(testUrl);
        DefaultHttpClient client = new DefaultHttpClient();
        HttpResponse response = client.execute(get);
        result = EntityUtils.toString(response.getEntity(),"utf-8");

        store = client.getCookieStore();
        List<Cookie> listcookie = store.getCookies();
        for(Cookie cookie:listcookie){
            String name = cookie.getName();
            String value = cookie.getValue();
            System.out.println("Name: "+name+" Value: "+value);
        }
    }

    @Test(dependsOnMethods = "testGetCookies")
    public void testPostMethod() throws IOException {
       String uri = bundle.getString("test.post.with.cookies");
       String testUrl = this.url+uri;

       DefaultHttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(testUrl);
        JSONObject param = new JSONObject();
        param.put("name","florence");
        param.put("state","insister");

        post.setHeader("content-type","application/json");
        StringEntity stringEntity = new StringEntity(param.toString(),"utf-8");
        post.setEntity(stringEntity);
        String result;
        client.setCookieStore(store);

        HttpResponse response=client.execute(post);
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);

        JSONObject jsonObject = new JSONObject(result);
        String state = (String) jsonObject.get("florence");
        String status = (String) jsonObject.get("status");
        Assert.assertEquals("success",state);
        Assert.assertEquals("success",state);
    }
}
