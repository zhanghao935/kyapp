package com.zjxf.ky_app;


import android.content.Context;
import android.webkit.JavascriptInterface;

import com.alibaba.fastjson.JSONObject;
import com.zjxf.ky_app.utils.OkHttpUtils;

import java.io.IOException;
import java.util.Map;

import okhttp3.Response;

/**
 * created with IntelliJ IDEA
 *
 * @author: create by limu
 * Date: 2019/11/12
 * Time：9:39
 */
public class JavaScriptInterface {

    Context context;

    public JavaScriptInterface(Context c) {
        context = c;
    }

    /**
     * 发送post请求
     *
     * @param msg 参数信息
     */
    @JavascriptInterface
    public String postData(String msg) throws IOException {
        JSONObject paramJson = JSONObject.parseObject(msg);
        String url = paramJson.getString("url");
        Map<String, Object> paramMap = paramJson.getJSONObject("param").getInnerMap();
        Response response = OkHttpUtils.getInstance().postData("http://192.168.0.139:8629" + url, paramMap);
        return response.body().string();
    }

    /**
     * 发送get请求
     *
     * @param msg 参数信息
     */
    @JavascriptInterface
    public void getData(String msg) {
        System.out.println(msg);
    }


}
