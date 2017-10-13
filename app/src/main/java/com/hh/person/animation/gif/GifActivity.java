package com.hh.person.animation.gif;

import android.app.Activity;
import android.os.Bundle;

import com.hh.person.animation.R;
import com.hh.person.animation.base.BaseActivity;
import com.hh.person.animation.widget.gif.CustomGifView;

public class GifActivity extends BaseActivity {
    private CustomGifView gifView;

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_gif);
    }

    @Override
    protected void findViewById() {
        gifView = (CustomGifView) findViewById(R.id.gif_view);
    }

    @Override
    protected void setLister() {

    }

    @Override
    protected void initData() {
        gifView.setStart();
    }
}
