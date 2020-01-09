package com.zjxf.ky_app;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.GeolocationPermissions;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.zjxf.ky_app.utils.StatusBarUtils;

import static android.webkit.WebSettings.LayoutAlgorithm.SINGLE_COLUMN;

/**
 * created with IntelliJ IDEA
 *
 * @author: create by limu
 * Date: 2019/11/12
 * Time：9:39
 */
public class MainActivity extends AppCompatActivity implements KeyEvent.Callback {

    public WebView inquestWb;    //全局浏览器对象

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtils.setTranslucentStatus(this);
        setContentView(com.zjxf.ky_app.R.layout.activity_main);
        initWebView(Boolean.FALSE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getApplicationContext(), "没有权限,请手动开启定位权限", Toast.LENGTH_SHORT).show();
            // 申请一个（或多个）权限，并提供用于回调返回的获取码（用户定义）
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
        }
    }

    /**
     * 初始化webView
     *
     * @param isAllScreen 是否全屏
     */
    public void initWebView(Boolean isAllScreen) {
        inquestWb = findViewById(com.zjxf.ky_app.R.id.inquest_wb);
        inquestWb.getSettings().setLayoutAlgorithm(SINGLE_COLUMN);
        inquestWb.getSettings().setLoadWithOverviewMode(Boolean.TRUE);
        inquestWb.setLongClickable(Boolean.TRUE);
        inquestWb.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return Boolean.TRUE;
            }
        });
        WebView.setWebContentsDebuggingEnabled(Boolean.TRUE);
        inquestWb.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return Boolean.TRUE;
            }
        });
        //启用地理定位
        inquestWb.getSettings().setGeolocationEnabled(true);
        inquestWb.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                return super.onJsAlert(view, url, message, result);
            }
        });
        WebSettings settings = inquestWb.getSettings();
        settings.setDomStorageEnabled(Boolean.TRUE);
        settings.setJavaScriptEnabled(Boolean.TRUE);
        if (isAllScreen) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        inquestWb.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
                callback.invoke(origin, true, false);
                super.onGeolocationPermissionsShowPrompt(origin, callback);
            }
        });
        inquestWb.addJavascriptInterface(new JavaScriptInterface(this), "zjxf");
        inquestWb.loadUrl("file:///android_asset/html/login.html");
    }

    /**
     * 返回按钮
     *
     * @param keyCode 点击按钮标识
     * @param event   事件
     * @return boolean
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && inquestWb.canGoBack()) {
            inquestWb.goBack();
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

}
