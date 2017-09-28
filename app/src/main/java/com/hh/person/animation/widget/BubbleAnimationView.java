package com.hh.person.animation.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.hh.person.animation.R;

import java.util.Random;

/**
 * Created by Administrator on 2017/9/28 0028.
 * 气泡动画
 */

public class BubbleAnimationView extends RelativeLayout {

    private Context mContext;
    private LayoutParams param;
    private Drawable[] drawables = new Drawable[7];
    private Random random = new Random();
    private Interpolator aa = new AccelerateInterpolator();
    private Interpolator bb = new DecelerateInterpolator();
    private Interpolator cc = new AccelerateDecelerateInterpolator();
    private Interpolator dd = new AnticipateInterpolator();
    private Interpolator ee = new LinearInterpolator();
    private Interpolator[] interpolators = new Interpolator[5];

    //view宽度
    private int width;
    private int height;

    //图片的宽、高(这里图片宽高都是一样大小的)
    private int bmpWidth;
    private int bmpHeight;

    private PointF pointF0 = new PointF();  //开始点坐标
    private PointF pointF1 = new PointF();  //控件点坐标
    private PointF pointF2 = new PointF();  //控制点坐标
    private PointF pointF3 = new PointF();  //结束点坐标

    private Paint mPaint = null;

    public BubbleAnimationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init();
    }

    private void init() {
        Drawable one = getResources().getDrawable(R.mipmap.one);
        Drawable two = getResources().getDrawable(R.mipmap.two);
        Drawable three = getResources().getDrawable(R.mipmap.three);
        Drawable four = getResources().getDrawable(R.mipmap.four);
        Drawable five = getResources().getDrawable(R.mipmap.five);
        Drawable six = getResources().getDrawable(R.mipmap.six);
        Drawable eight = getResources().getDrawable(R.mipmap.eight);
        //图片赋值
        drawables[0] = one;
        drawables[1] = two;
        drawables[2] = three;
        drawables[3] = four;
        drawables[4] = five;
        drawables[5] = six;
        drawables[6] = eight;
        //插入器赋值
        interpolators[0] = aa;
        interpolators[1] = bb;
        interpolators[2] = cc;
        interpolators[3] = dd;
        interpolators[4] = ee;
        //获取图片 宽、高
        bmpWidth = one.getIntrinsicWidth();
        bmpHeight = one.getIntrinsicHeight();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);

        //设置图片 相关属性
        param = new LayoutParams(bmpWidth, bmpHeight);
        param.addRule(RelativeLayout.CENTER_HORIZONTAL, TRUE);
        param.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, TRUE);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //测量 Layout 宽高
        width = getMeasuredWidth();
        height = getMeasuredHeight();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    /**
     * 显示效果图
     */
    public void addImageView() {
        final ImageView imageView = new ImageView(mContext);
        imageView.setLayoutParams(param);
        imageView.setImageDrawable(drawables[random.nextInt(7)]);
        addView(imageView);
        imageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"点中了，疼疼疼",Toast.LENGTH_SHORT).show();
            }
        });

        //实现 渐变色：无到有   缩放大小：从小到大
        AnimatorSet animatorSet = setAnimationSet(imageView);
        animatorSet.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                //动画结束，移除掉imageView
                removeView(imageView);
            }
        });
        animatorSet.start();
    }

    private AnimatorSet setAnimationSet(ImageView imageView) {
        //渐变色
        ObjectAnimator alpha = ObjectAnimator.ofFloat(imageView, "alpha", 0.3f, 1f);
        //缩放效果
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(imageView, "scaleX", 0.2f, 1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(imageView, "scaleY", 0.2f, 1f);
        //动画组合效果
        AnimatorSet enter = new AnimatorSet();
        enter.playTogether(alpha, scaleX, scaleY); //上面动画一起工作
        enter.setDuration(500);
        enter.setTarget(imageView);   //动画作用于imageview

        //贝塞尔曲线动画的核心，不断改变ImageView 的坐标
        ValueAnimator valueAnimator = bezierValueAnimation(imageView);
        AnimatorSet bezierSer = new AnimatorSet();
        //按照顺序执行动画
        bezierSer.playSequentially(enter, valueAnimator);
        bezierSer.setTarget(imageView);
        //加速器
        bezierSer.setInterpolator(interpolators[random.nextInt(5)]);
        return bezierSer;
    }

    private ValueAnimator bezierValueAnimation(final ImageView imageView) {
        /**
         * 计算 贝塞尔曲线 的坐标点
         */
        pointF0.x = (width - bmpWidth) / 2;
        pointF0.y = height - bmpHeight;

        pointF1.x = random.nextInt(width);
        pointF1.y = random.nextInt((height / 2) - bmpHeight) + height / 2;

        pointF2.x = random.nextInt(width);
        pointF2.y = random.nextInt(height / 2);

        pointF3.x = random.nextInt(width);
        pointF3.y = 0.0f;

        //估值器
        BezierEvaluator bezierEvaluator = new BezierEvaluator(pointF1, pointF2);
        //将估值器设置到 ValueAnimator 中，
        ValueAnimator valueAnimator = ValueAnimator.ofObject(bezierEvaluator, pointF0, pointF3);
        valueAnimator.setTarget(imageView);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            //不断在刷新
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF pointF = (PointF) animation.getAnimatedValue();
                imageView.setX(pointF.x);
                imageView.setY(pointF.y);
                //getAnimatedFraction 得到百分比
                imageView.setAlpha(1 - animation.getAnimatedFraction());
            }
        });
        valueAnimator.setDuration(3000);
        return valueAnimator;
    }
}
