package com.hh.person.animation;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;

import com.hh.person.animation.base.BaseActivity;
import com.hh.person.animation.widget.BubbleAnimationView;

/**
 * 气泡动画
 */
public class BubbleAnimationActivity extends BaseActivity implements View.OnClickListener {
    private BubbleAnimationView animationView;
    private Button start;
    private Button stop;
    private boolean isRunning = true;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(isRunning){
                animationView.addImageView();
                handler.sendEmptyMessageDelayed(0, 1000);
            }
        }
    };


    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_bubble_animation);
    }

    @Override
    protected void findViewById() {
        animationView = (BubbleAnimationView) findViewById(R.id.animation_id);
        start = (Button) findViewById(R.id.start);
        stop = (Button) findViewById(R.id.stop);

    }

    @Override
    protected void setLister() {
        start.setOnClickListener(this);
        stop.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.start:
                isRunning = true;
                handler.sendMessage(new Message());
                break;
            case R.id.stop:
                isRunning = false;
                handler.sendMessage(new Message());
                break;
        }
    }
}
