package com.zjxf.ky_app;


import android.content.Context;
import android.webkit.JavascriptInterface;
import com.alibaba.fastjson.JSONObject;
import com.zjxf.ky_app.utils.OkHttpUtils;
import okhttp3.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
        JSONObject param = paramJson.getJSONObject("param");
        if (param != null) {
            Map<String, Object> paramMap = paramJson.getJSONObject("param").getInnerMap();
            Response response = OkHttpUtils.getInstance().postData("http://192.168.0.139:8629" + url, paramMap);
            System.out.println(response.body().string());
            return response.body().string();
        } else {
            Response response = OkHttpUtils.getInstance().postData("http://192.168.0.139:8629" + url, new HashMap<String, Object>());
            System.out.println(response.body().string());
            return response.body().string();
        }
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
