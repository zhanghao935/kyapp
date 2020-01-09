package com.zjxf.ky_app;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.view.WindowManager;
import androidx.appcompat.app.AppCompatActivity;

import com.zjxf.ky_app.utils.StatusBarUtils;

public class SplashActivity extends AppCompatActivity {
    private static final int WHAT_DELAY = 0x11;// 启动页的延时跳转
    private static final int DELAY_TIME = 3000;// 延时时间

    // 创建Handler对象，处理接收的消息
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case WHAT_DELAY:// 延时3秒跳转
                    goHome();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(com.zjxf.ky_app.R.layout.activity_splash);
        StatusBarUtils.setTranslucentStatus(this);
        // 调用handler的sendEmptyMessageDelayed方法
        handler.sendEmptyMessageDelayed(WHAT_DELAY, DELAY_TIME);
    }

    /**
     * 跳转到主页面
     */
    private void goHome() {
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        finish();// 销毁当前活动界面
    }
}
