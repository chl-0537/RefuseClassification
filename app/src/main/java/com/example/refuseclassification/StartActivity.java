package com.example.refuseclassification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class StartActivity extends BaseActivity implements View.OnClickListener{

    private TextView skip;
    private int TIME = 3;
    private Handler handler;
    private Runnable runnable;
    Timer timer = new Timer();

    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    TIME--;
                    skip.setText("跳过 " + "(" + TIME + "s)");
                    if (TIME < 0) {
                        // 小于0时隐藏字体
                        timer.cancel();
                        skip.setVisibility(View.GONE);
                    }
                }
            });
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 去掉app标题栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_start);

        skip = findViewById(R.id.skip);
        skip.setOnClickListener(this);// 设置点击跳过

        timer.schedule(task, 1000, 1000);// 等待时间1s，停顿时间1s
        // 设置不点击跳过
        handler = new Handler();
        handler.postDelayed(runnable = new Runnable() {
            @Override
            public void run() {
                //从闪屏界面跳转到首界面
                Intent intent = new Intent(StartActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, 5000);//延迟5S后发送handler信息
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.skip:
                // 跳转到登录页面
                Intent intent = new Intent(StartActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                if (runnable != null) {
                    handler.removeCallbacks(runnable);
                }
                break;
            default:
                break;
        }
    }
}
