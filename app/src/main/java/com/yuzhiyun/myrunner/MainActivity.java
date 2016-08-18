package com.yuzhiyun.myrunner;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.logging.LogRecord;

public class MainActivity extends AppCompatActivity {


    TextView tvStepNum;

    private boolean isRun = false;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.i("handleMessage", "步数是" + StepDetector.CURRENT_SETP);
            tvStepNum.setText(StepDetector.CURRENT_SETP+"");

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initView();

        new Thread() {
            @Override
            public void run() {
                super.run();
                while (true) {
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    if (isRun) {


                        handler.sendEmptyMessage(199);
                    }
                }
            }
        }.start();


    }

    private void initView() {
        tvStepNum = (TextView) findViewById(R.id.tvStepNum);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                isRun = true;
                startService(new Intent(MainActivity.this, StepCounterService.class));
            }
        });
    }


}
