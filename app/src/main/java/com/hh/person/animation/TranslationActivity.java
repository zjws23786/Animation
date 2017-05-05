package com.hh.person.animation;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Point;
import android.graphics.PointF;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateInterpolator;
import android.widget.Button;
import android.widget.TextView;

import com.hh.person.animation.base.BaseActivity;
import com.hh.person.animation.common.Env;
import com.hh.person.animation.custom.BeziTwoEvaluator;


public class TranslationActivity extends BaseActivity implements View.OnClickListener {
    private Button tranBtn;
    private TextView tranTv;
    private TextView redPointTv;
    private TextView bseTv;

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_translation);
    }

    @Override
    protected void findViewById() {
        tranBtn = (Button) findViewById(R.id.tran_btn);
        tranTv = (TextView) findViewById(R.id.tran_tv);
        redPointTv = (TextView) findViewById(R.id.red_point_tv);
        bseTv = (TextView) findViewById(R.id.bse_tv);
    }

    @Override
    protected void setLister() {
        tranBtn.setOnClickListener(this);
        tranTv.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tran_tv:
            case R.id.tran_btn:
                codeImp();
//                XmlImp(this);
                break;
        }
    }

    private void codeImp() {
        float curTranslationX = tranTv.getTranslationX();
//        ObjectAnimator animator = ObjectAnimator.ofFloat(tranTv,"translationX",curTranslationX,curTranslationX+200);
      /*  ObjectAnimator animator = ObjectAnimator.ofFloat(tranTv,"translationX",curTranslationX+200,-50);
        animator.setDuration(5000);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.start();*/

        ValueAnimator valueAnimator = ValueAnimator.ofFloat(curTranslationX+200,-50);
        valueAnimator.setDuration(5000);
        valueAnimator.start();


        AnimatorSet set = new AnimatorSet();
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(redPointTv,"translationX",redPointTv.getTranslationX(),-redPointTv.getLeft()+20);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(redPointTv,"translationY",
                redPointTv.getTranslationY(), Env.screenHeight - redPointTv.getTop() - Env.statusBarHeight - 100);
//        Log.v("hjz","height="+ Env.screenHeight);
//        Log.v("hjz","heightT="+ redPointTv.getTop());
//        Log.v("hjz","heightY="+ redPointTv.getTranslationY());
//        Log.v("hjz","height="+(Env.screenHeight - 20 - redPointTv.getTop() - Env.statusBarHeight));
        set.setDuration(5000);
        set.setInterpolator(new AccelerateDecelerateInterpolator());
        set.play(animatorX).with(animatorY);
        set.start();

       /* int x = redPointTv.getLeft();
        Log.v("hjz","x="+x);
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(redPointTv,"translationX",redPointTv.getTranslationX(),-redPointTv.getLeft());
        animator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float f = (float) animation.getAnimatedValue();
                Log.v("hjz","xx="+f);
            }
        });
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator1.setDuration(5000);
        animator1.start();*/
        bseMethod();  //二阶贝塞尔曲线
    }

    private void XmlImp(Context context) {
//        Animator animator = AnimatorInflater.loadAnimator(context,R.animator.translation);
//        animator.setTarget(tranTv);
//        animator.start();

        Animation animation = AnimationUtils.loadAnimation(context,R.anim.translation);
        tranTv.startAnimation(animation);
    }

    private void bseMethod() {
        //贝塞尔曲线动画的核心，不断改变ImageView 的坐标
        ValueAnimator valueAnimator = bezierValueAnimation(bseTv);
        valueAnimator.setTarget(bseTv);
        valueAnimator.setDuration(6000);

        AnimatorSet set = new AnimatorSet();
        set.setDuration(6000);
        set.setTarget(bseTv);
        set.play(valueAnimator);
        set.start();
    }

    private PointF pointF0 = new PointF();  //开始点坐标
    private PointF pointF1 = new PointF();  //控件点坐标
    private PointF pointF2 = new PointF();  //结束点坐标
    private ValueAnimator bezierValueAnimation(TextView bseTv) {
        /**
         * 计算 贝塞尔曲线 的坐标点
         */
        pointF0.x = bseTv.getLeft();
        pointF0.y = bseTv.getTop() + Env.statusBarHeight;

        pointF2.x = 20;
        pointF2.y = Env.screenHeight - bseTv.getTop() - Env.statusBarHeight - 100;

        pointF1.x = pointF2.x;
        pointF1.y = (pointF2.y - pointF0.y)/2;
        //估值器
        BeziTwoEvaluator beziTwoEvaluator = new BeziTwoEvaluator(pointF1);
        //将估值器设置到ValueAnimator
        ValueAnimator valueAnimator = ValueAnimator.ofObject(beziTwoEvaluator,pointF0,pointF2);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF pointF = (PointF) animation.getAnimatedValue();
                Log.v("hjz","x ="+ pointF.x + "   y=" + pointF.y);
            }
        });
        return valueAnimator;
    }
}
