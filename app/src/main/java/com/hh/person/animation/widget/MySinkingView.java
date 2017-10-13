package com.hh.person.animation.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Region;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;

import com.hh.person.animation.R;

/**
 * Created by Administrator on 2017/9/28 0028.
 */

public class MySinkingView extends FrameLayout {
    //默认字体颜色
    private static final int DEFAULT_TEXTCOLOT = 0xFFFFFFFF;
    //默认字体大小
    private static final int DEFAULT_TEXTSIZE = 250;

    private float mPercent; //浮动的百分比（进度）

    private Paint mPaint = new Paint();
    private Bitmap mBitmap;
    private Bitmap mScaledBitmap; //缩放图片

    private float mLeft, mTop;

    private int mSpeed = 15; //速度

    private int mRepeatCount = 0; //重复次数

    private Status mFlag = Status.NONE;  //状态控制

    private int mTextColor = DEFAULT_TEXTCOLOT;

    private int mTextSize = DEFAULT_TEXTSIZE;

    public MySinkingView(Context context) {
        this(context,null);
    }

    public MySinkingView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MySinkingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setTextColor(int color) {
        mTextColor = color;
    }

    public void setTextSize(int size) {
        mTextSize = size;
    }

    public void setPercent(float percent) {
        mFlag = Status.RUNNING;
        mPercent = percent;
        postInvalidate();

    }

    public void setStatus(Status status) {
        mFlag = status;
    }

    public void clear() {
        mFlag = Status.NONE;
        if (mScaledBitmap != null) {
            mScaledBitmap.recycle();
            mScaledBitmap = null;
        }

        if (mBitmap != null) {
            mBitmap.recycle();
            mBitmap = null;
        }
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        int width = getWidth();
        int height = getHeight();

        //裁剪成圆区域
        Path path = new Path();
        canvas.save();
        path.reset();
        //用指定的路径相交当前的剪辑
//        canvas.clipPath(path);
        //向路径添加一个闭圆轮廓
        path.addCircle(width / 2, height / 2, width / 2, Path.Direction.CCW);
        //使用指定的路径修改当前的剪辑
        canvas.clipPath(path, Region.Op.REPLACE);

        if (mFlag == Status.RUNNING) {
            if (mScaledBitmap == null) {
                mBitmap = BitmapFactory.decodeResource(getContext().getResources(), R.mipmap.wave2);
                /**
                 * 创建一个新的位图，从现有的位图缩放，尽可能。
                 * 如果指定的宽度和高度与源位图的当前宽度和高度相同，
                 * 则返回源位图，并且不会创建新的位图
                 */
                mScaledBitmap = Bitmap.createScaledBitmap(mBitmap, mBitmap.getWidth(), getHeight(), false);
                mBitmap.recycle();
                mBitmap = null;
                Log.v("hjz","getWidth()="+getWidth());
                Log.v("hjz","mScaledBitmap.getWidth()="+mScaledBitmap.getWidth());
                mRepeatCount = (int) Math.ceil(getWidth() / mScaledBitmap.getWidth() + 0.5) + 1;
            }
            for (int idx = 0; idx < mRepeatCount; idx++) {
                canvas.drawBitmap(mScaledBitmap, mLeft + (idx - 1) * mScaledBitmap.getWidth(), (1-mPercent) * getHeight(), null);
            }
            String str = (int) (mPercent * 100) + "%";
            mPaint.setColor(mTextColor);
            mPaint.setTextSize(mTextSize);
            mPaint.setStyle(Paint.Style.FILL);
            canvas.drawText(str, (getWidth() - mPaint.measureText(str)) / 2, getHeight() / 2 + mTextSize / 2, mPaint);

            mLeft += mSpeed;
            if (mLeft >= mScaledBitmap.getWidth()){
                mLeft = 0;
            }

            // 绘制外圆环
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setStrokeWidth(4);
            mPaint.setAntiAlias(true);
            mPaint.setColor(Color.rgb(33, 211, 39));
            canvas.drawCircle(width / 2, height / 2, width / 2 - 2, mPaint);

            postInvalidateDelayed(20);
        }
        canvas.restore();

    }

    public enum Status {
        RUNNING, NONE
    }

}
