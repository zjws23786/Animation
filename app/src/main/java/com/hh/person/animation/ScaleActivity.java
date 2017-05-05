package com.hh.person.animation;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.hh.person.animation.base.BaseActivity;

public class ScaleActivity extends BaseActivity implements View.OnClickListener {
    private Button scaleBtn;
    private TextView scaleTv;

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_scale);
    }

    @Override
    protected void findViewById() {
        scaleBtn = (Button) findViewById(R.id.scale_btn);
        scaleTv = (TextView) findViewById(R.id.scale_tv);
    }

    @Override
    protected void setLister() {
        scaleBtn.setOnClickListener(this);
        scaleTv.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.scale_tv:
            case R.id.scale_btn:
//                codeImp();
                xmlImp(this);
                break;
        }
    }

    private void codeImp() {
        AnimatorSet animatorSet = new AnimatorSet();//组合动画
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(scaleTv,"scaleX",1f,3f,0.5f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(scaleTv, "scaley",1f,3f,0.5f);
        animatorSet.setDuration(10000);
        animatorSet.setInterpolator(new AccelerateInterpolator());
        animatorSet.play(scaleX).with(scaleY);//两个动画同时开始
        animatorSet.start();
    }

    private void xmlImp(Context context) {
        /*Animator animator = AnimatorInflater.loadAnimator(context,R.animator.scale);
        animator.setTarget(scaleTv);
        animator.start();*/

        Animation scaleX = AnimationUtils.loadAnimation(context,R.anim.scale);
        final Animation scaleY = AnimationUtils.loadAnimation(context,R.anim.scaley);
        scaleX.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                scaleTv.startAnimation(scaleY);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        scaleTv.startAnimation(scaleX);
    }
}
