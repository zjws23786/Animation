package com.hh.person.animation;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class PaintActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_paint);
    }
}
