package com.zjxf.ky_app;


import android.content.Context;
import android.view.Gravity;
import android.webkit.JavascriptInterface;
import android.widget.Toast;
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

    public String authToken = "";

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
        if (param.keySet().size() != 0) {
            Map<String, Object> paramMap = paramJson.getJSONObject("param").getInnerMap();
            //Response response = OkHttpUtils.getInstance().postDataAsJwt("http://192.168.0.139:8629" + url, paramMap, authToken);
            Response response = OkHttpUtils.getInstance().postDataAsJwt("http://192.168.0.153:8629" + url, paramMap, authToken);
            String responseStr = response.body().string();
            System.out.println(responseStr);
            return responseStr;
        } else {
            //Response response = OkHttpUtils.getInstance().postDataAsJwt("http://192.168.0.139:8629" + url, new HashMap<String, Object>(), authToken);
            Response response = OkHttpUtils.getInstance().postDataAsJwt("http://192.168.0.153:8629" + url, new HashMap<String, Object>(), authToken);
            String responseStr = response.body().string();
            System.out.println(responseStr);
            return responseStr;
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

    /**
     * android toast
     *
     * @param msg 参数信息
     */
    @JavascriptInterface
    public void showToast(String msg) {
        Toast toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    /**
     * 登录接口
     *
     * @param msg 请求参数
     * @throws IOException 流异常
     */
    @JavascriptInterface
    public String login(String msg) throws IOException {
        JSONObject paramJson = JSONObject.parseObject(msg);
        String url = paramJson.getString("url");
        Map<String, Object> paramMap = paramJson.getJSONObject("param").getInnerMap();
        //Response response = OkHttpUtils.getInstance().postData("http://192.168.0.139:8629" + url, paramMap);
        Response response = OkHttpUtils.getInstance().postData("http://192.168.0.153:8629" + url, paramMap);
        JSONObject jsonObject = JSONObject.parseObject(response.body().string());
        if (jsonObject.getBoolean("status")) {
            authToken = jsonObject.getString("msg");
            showToast("登录成功");
            return Boolean.TRUE.toString();
        } else {
            showToast(jsonObject.getString("msg"));
            return Boolean.FALSE.toString();
        }
    }


}
