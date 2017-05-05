package com.hh.person.animation.base;

import android.app.Activity;
import android.os.Bundle;

public abstract class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayout();
        findViewById();
        setLister();
        initData();
    }

    protected abstract void setLayout();

    protected abstract void findViewById();

    protected abstract void setLister();

    protected abstract void initData();
}
