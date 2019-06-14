package com.ghareeb.androidthreadssimple;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private HandlerThread handlerThread = new HandlerThread("mainHandlerThread");
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handlerThread.start();
        //this.getMainLooper() is th UI thread
        handler = new Handler(handlerThread.getLooper());
    }

    public void sendTask(View view){
        handler.post(new Runnable() {
            @Override
            public void run() {
                for (int i =1; i<=10 ; i++){
                    Log.i("MainhandlerThread", "Running for i = " + i);
                    try {
                        //each iteration loop requires 0.5 sec
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handlerThread.quit();
    }
}
