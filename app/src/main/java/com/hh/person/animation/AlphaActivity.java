package com.hh.person.animation;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;

import com.hh.person.animation.base.BaseActivity;

public class AlphaActivity extends BaseActivity implements View.OnClickListener {
    private Button alphaBtn;
    private TextView alphaTv;

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_alpha);
    }

    @Override
    protected void findViewById() {
        alphaBtn = (Button) findViewById(R.id.alpha_btn);
        alphaTv = (TextView) findViewById(R.id.alpha_tv);
    }

    @Override
    protected void setLister() {
        alphaBtn.setOnClickListener(this);
        alphaTv.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.alpha_tv:
            case R.id.alpha_btn:
//                codeImp();
                xmlImp(this);
                break;
        }
    }

    private void codeImp() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(alphaTv,"alpha",1f,0f,1f,0.5f);
        animator.setDuration(10000);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.setTarget(alphaTv);
        animator.start();
    }

    private void xmlImp(Context context) {
        Animator animator = AnimatorInflater.loadAnimator(context,R.animator.alpha);
        animator.setTarget(alphaTv);
        animator.start();
    }
}
