package com.hh.person.animation.custom;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.hh.person.animation.bean.Point;
import com.hh.person.animation.common.Env;

/**
 * Created by hjz on 2017/2/27.
 */

public class BeZiTwoView extends View {
    public static final float RADIUS = 25f;

    private PointF currentPoint;

    private Paint mPaint;

    public BeZiTwoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.GREEN);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (currentPoint == null){
            currentPoint = new PointF(Env.screenWidth - RADIUS, RADIUS);
        }
        drawCircle(canvas);
    }

    private void drawCircle(Canvas canvas) {
        float x = currentPoint.x;
        float y = currentPoint.y;
        canvas.drawCircle(x, y, RADIUS, mPaint);
    }

    public void start(){
        currentPoint = new PointF(Env.screenWidth - RADIUS, RADIUS);
        startAnimation();
    }

    private PointF pointF0 = new PointF();  //开始点坐标
    private PointF pointF1 = new PointF();  //控件点坐标
    private PointF pointF2 = new PointF();  //结束点坐标
    private void startAnimation() {

        /**
         * 计算 贝塞尔曲线 的坐标点
         */
        pointF0 = currentPoint;

        pointF2.x = RADIUS;
        pointF2.y = this.getHeight() - RADIUS - 20;

        pointF1.x = pointF2.x;
        pointF1.y = (pointF2.y - pointF0.y)/2;
        //估值器
        BeziTwoEvaluator beziTwoEvaluator = new BeziTwoEvaluator(pointF1);
        //将估值器设置到ValueAnimator
        ValueAnimator valueAnimator = ValueAnimator.ofObject(beziTwoEvaluator,pointF0,pointF2);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentPoint = (PointF) animation.getAnimatedValue();
                invalidate();
                Log.v("hjz","x ="+ currentPoint.x + "   y=" + currentPoint.y);
            }
        });
        valueAnimator.setDuration(3000);
        valueAnimator.start();
    }
}
