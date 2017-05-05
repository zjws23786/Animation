package com.hh.person.animation;


import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.TextView;

import com.hh.person.animation.base.BaseActivity;

public class RotationActivity extends BaseActivity implements View.OnClickListener {
    private Button btn1;
    private TextView rtTv;

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_rotation);
    }

    @Override
    protected void findViewById() {
        btn1 = (Button) findViewById(R.id.rotation_btn);
        rtTv = (TextView) findViewById(R.id.rt_tv);
    }

    @Override
    protected void setLister() {
        btn1.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rotation_btn:
//                codeImp();
                xmlImp(this);
                break;
        }
    }

    private void codeImp() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(rtTv,"rotation",0f,360f);
        animator.setDuration(5000);
        animator.setInterpolator(new AnticipateInterpolator());
        animator.start();
    }

    private void xmlImp(Context context) {
        /*第一种写法*/
//        Animator animator = AnimatorInflater.loadAnimator(context,R.animator.rotation);
//        animator.setTarget(rtTv);
//        animator.start();

        /*第二种写法*/
        Animation animation = AnimationUtils.loadAnimation(context,R.anim.rotation);
        rtTv.startAnimation(animation);
    }
}
