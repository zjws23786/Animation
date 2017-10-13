package com.hh.person.animation.widget.gif;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.hh.person.animation.R;


public class CustomGifView extends View implements Runnable {
    private GifOpenHelper gHelper;
    private boolean isStop = true;
    private int delta;
    private String title;

    private Paint mPaint;
    private Bitmap firstBmp;
    private Bitmap bmp;

    // construct - refer for java
    public CustomGifView(Context context) {
        this(context, null);

    }

    // construct - refer for xml
    public CustomGifView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        //添加属性
        TypedArray ta = context.obtainStyledAttributes(attrs,R.styleable.gifView);
        int n = ta.getIndexCount();

        for (int i = 0; i < n; i++) {
            int attr = ta.getIndex(i);
            switch (attr) {
                case R.styleable.gifView_src:
                    int id = ta.getResourceId(R.styleable.gifView_src, 0);
                    setSrc(id);
                    break;

                case R.styleable.gifView_delay:
                    int idelta = ta.getInteger(R.styleable.gifView_delay, 1);
                    setDelta(idelta);
                    break;

                case R.styleable.gifView_stop:
                    boolean sp = ta.getBoolean(R.styleable.gifView_stop, false);
                    if (!sp) {
                        setStop();
                    }
                    break;
            }
        }

        ta.recycle();
    }

    /**
     * 设置停止
     *
     * @param
     */
    public void setStop() {
        bmp = firstBmp;
        isStop = false;
        this.postInvalidate();
    }

    /**
     * 设置启动
     */
    public void setStart() {
        isStop = true;
        bmp = firstBmp;
        Thread updateTimer = new Thread(this);
        updateTimer.start();
    }

    /**
     * 通过下票设置第几张图片显示
     *
     * @param id
     */
    public void setSrc(int id) {
        gHelper = new GifOpenHelper();
        gHelper.read(CustomGifView.this.getResources().openRawResource(id));
        bmp = gHelper.getImage();// 得到第一张图片
        firstBmp = bmp;
    }

    public void setDelta(int is) {
        delta = is;
    }

    // to meaure its Width & Height
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureWidth(widthMeasureSpec),
                measureHeight(heightMeasureSpec));
    }

    private int measureWidth(int measureSpec) {
        return gHelper.getWidth();
    }

    private int measureHeight(int measureSpec) {
        return gHelper.getHeigh();
    }

    protected void onDraw(Canvas canvas) {
        if (mPaint == null) {
            mPaint = new Paint();
        }
        if (isStop) {
            canvas.drawBitmap(bmp, 0, 0, mPaint);
            bmp = gHelper.nextBitmap();
        } else {
            canvas.drawBitmap(bmp, 0, 0, mPaint);
        }


    }

    public void run() {
        // TODO Auto-generated method stub
        while (isStop) {
            try {
                this.postInvalidate();
                Thread.sleep(gHelper.nextDelay() / delta);
            } catch (Exception ex) {

            }
        }
    }

}
