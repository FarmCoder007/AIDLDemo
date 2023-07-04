package com.lemonydbook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;

import java.lang.reflect.Proxy;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    private static class MyHandler extends Handler {
        // 处理消息的逻辑

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
        }
    }

    private MyHandler mHandler = new MyHandler();


//    Handler mHandler = new Handler(){
//        @Override
//        public void handleMessage(@NonNull Message msg) {
//            super.handleMessage(msg);
//
//        }
//    };

    public void click(View view){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();

                Looper.loop();
                // 子线程中 调用主线程的handler 发送消息
                mHandler.sendMessage(Message.obtain());
            }
        }).start();
        // 主线程中 调用 子线程的handler 发送消息 怎么到子线程接受的
        threadHandler.sendMessage(Message.obtain());
    }

    Handler threadHandler;

    public void test(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(threadHandler == null){
                    Looper.prepare();
                    threadHandler = new Handler(){
                        @Override
                        public void handleMessage(@NonNull Message msg) {
                            super.handleMessage(msg);
                        }
                    };
                    Looper.loop();
                }
            }
        }).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        threadHandler.getLooper().quit();
    }
}





