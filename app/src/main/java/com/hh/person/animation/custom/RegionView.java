package com.hh.person.animation.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.RegionIterator;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * Created by hjz on 2017/2/28.
 */

public class RegionView extends View {
    private Paint mPaint;
    private Region region,region1,region2;

    public RegionView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(3);

        Path path = new Path();  //路径
        /***
         * Path.Direction有两个值：
            Path.Direction.CCW：是counter-clockwise缩写，指创建逆时针方向的矩形路径；
            Path.Direction.CW：是clockwise的缩写，指创建顺时针方向的矩形路径；
         */
        path.addCircle(310,310,300, Path.Direction.CW);
        mPaint.setColor(Color.GRAY);
        canvas.drawPath(path,mPaint);

        canvas.save();
        canvas.translate(0,650);
        mPaint.setColor(Color.YELLOW);
        region = new Region(); //定义一个区域对象
        //setPath主要是将path区域和clip区域取它们的交集
        region.setPath(path,new Region(0,0,1000,1000));

        //绘制
        RegionIterator iterator = new RegionIterator(region);
        Rect rect = new Rect();  //定义一个矩形
        while (iterator.next(rect)){
            canvas.drawRect(rect,mPaint);
        }
        canvas.restore();


        mPaint.setStrokeWidth(1);
        Path path1 = new Path();
        Path path2 = new Path();
        path1.addCircle(820, 220, 200, Path.Direction.CW);
        path2.addCircle(820, 420, 200, Path.Direction.CW);

        mPaint.setColor(Color.RED);
        canvas.drawPath(path1,mPaint);
        canvas.drawPath(path2,mPaint);

        region1 = new Region();
        region2 = new Region();
        region1.setPath(path1, new Region(0, 0, 1500, 1500));
        region2.setPath(path2, new Region(0, 0, 1500, 1500));

//        region1.op(region2, Region.Op.DIFFERENCE); //取补集
//        region1.op(region2, Region.Op.INTERSECT); // 交集
//        region1.op(region2, Region.Op.UNION);  //并集
        region1.op(region2, Region.Op.XOR);   //异并集
//        region1.op(region2, Region.Op.REVERSE_DIFFERENCE);   //反转补集
//        region1.op(region2, Region.Op.REPLACE);  //后者区域替代前者

        //绘制
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL);
        RegionIterator iterator1 = new RegionIterator(region1);
        Rect rect1 = new Rect();
        while (iterator1.next(rect1)) {
            canvas.drawRect(rect1, mPaint);
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if (region.contains((int) event.getX(),(int) event.getY())){
                    Toast.makeText(getContext(),"region",Toast.LENGTH_SHORT).show();
                    return true;
                }
                if (region1.contains((int) event.getX(),(int) event.getY())){
                    Toast.makeText(getContext(),"region1",Toast.LENGTH_SHORT).show();
                    return true;
                }
                if (region2.contains((int) event.getX(),(int) event.getY())){
                    Toast.makeText(getContext(),"region2",Toast.LENGTH_SHORT).show();
                    return true;
                }
                break;
        }
        return super.onTouchEvent(event);
    }
}
