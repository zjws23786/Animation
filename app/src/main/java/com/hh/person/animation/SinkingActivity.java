package com.hh.person.animation;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.hh.person.animation.base.BaseActivity;
import com.hh.person.animation.widget.MySinkingView;

public class SinkingActivity extends BaseActivity {
    private MySinkingView mSinkingView;
    private float percent = 0;


    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_sinking);
    }

    @Override
    protected void findViewById() {
        mSinkingView = (MySinkingView) findViewById(R.id.sinking);

        findViewById(R.id.btn_test).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                test();
            }
        });

        percent = 0.56f;
        mSinkingView.setPercent(percent);
    }

    private void test() {
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {

                percent = 0;
                while (percent <= 1) {
                    mSinkingView.setPercent(percent);
                    percent += 0.01f;
                    try {
                        Thread.sleep(40);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
//                percent = 0.56f;
                percent = 0;
                mSinkingView.setPercent(percent);
//                 mSinkingView.clear();
            }
        });
        thread.start();
    }


    @Override
    protected void setLister() {

    }

    @Override
    protected void initData() {

    }
}
