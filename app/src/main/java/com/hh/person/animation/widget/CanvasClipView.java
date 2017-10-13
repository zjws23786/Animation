package com.hh.person.animation.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Region;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by Administrator on 2017/10/13 0013.
 */

public class CanvasClipView extends FrameLayout {
    private Paint mPaint;

    public CanvasClipView(Context context) {
        this(context,null);
    }

    public CanvasClipView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CanvasClipView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.parseColor("#ff00ff"));
    }

    /**
     * 值得注意一点：
     * ViewGroup容器组件的绘制，
     * 当它没有背景时直接调用的是dispatchDraw()方法, 而绕过了draw()方法
     *
     * 当它有背景的时候就调用draw()方法，
     * 而draw()方法里包含了dispatchDraw()方法的调用。
     * 因此要在ViewGroup上绘制东西的时候往往重写的是dispatchDraw()方法而不是onDraw()方法，
     * 或者自定制一个Drawable，重写它的draw(Canvas c)和 getIntrinsicWidth()，getIntrinsicHeight()方法，
     * 然后设为背景
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画布有背景颜色就会绘制这个圆，如果没有就不会调这个方法
        mPaint.setColor(Color.RED);
        canvas.drawCircle(125, 125, 75, mPaint);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
//        mPaint.setColor(Color.BLUE);
//        canvas.drawCircle(80, 80, 75, mPaint);
        int width = getWidth();
        int height = getHeight();
        canvas.drawRect(0,0,width,height,mPaint);  //绘制一个矩形



        canvas.save(); //保存画布当前状态
        Path path = new Path();  //路径
        path.reset(); //清空路径相关设置
//        //向路径添加一个闭圆轮廓
        path.addCircle(200, 200, 200, Path.Direction.CCW);
//        //使用指定的路径修改当前的剪辑
        canvas.clipPath(path, Region.Op.REPLACE);
        canvas.drawColor(Color.BLUE); //设置当前裁剪区域背景颜色
        canvas.restore();

        canvas.save();
        canvas.clipRect(new Rect(410,100,710,300));
        canvas.drawColor(Color.GREEN);//裁剪区域的rect变为蓝色
        canvas.drawRect(new Rect(380,200,450,250), mPaint);//在裁剪的区域之外，不能显示
        canvas.drawCircle(460,150, 50, mPaint);//在裁剪区域之内，能显示
        canvas.restore();


    }
}
