package com.zjxf.ky_app;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

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
        setContentView(R.layout.activity_main);
        AndroidBug5497Workaround.assistActivity(this);
        initWebView(Boolean.FALSE);
    }

    /**
     * 初始化webView
     *
     * @param isAllScreen 是否全屏
     */
    public void initWebView(Boolean isAllScreen) {
        inquestWb = findViewById(R.id.inquest_wb);
        inquestWb.getSettings().setLayoutAlgorithm(SINGLE_COLUMN);
        inquestWb.getSettings().setLoadWithOverviewMode(Boolean.TRUE);
        inquestWb.setLongClickable(Boolean.TRUE);
        inquestWb.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return Boolean.TRUE;
            }
        });
        inquestWb.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return Boolean.TRUE;
            }
        });
        inquestWb.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                return super.onJsAlert(view, url, message, result);
            }
        });
        WebSettings settings = inquestWb.getSettings();
        settings.setJavaScriptEnabled(Boolean.TRUE);
        if (isAllScreen) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        inquestWb.addJavascriptInterface(new JavaScriptInterface(this), "android");
        inquestWb.loadUrl("file:///android_asset/html/discovery.html");
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
