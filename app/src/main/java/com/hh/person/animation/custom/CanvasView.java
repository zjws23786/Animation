package com.hh.person.animation.custom;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.View;

import com.hh.person.animation.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hjz on 2017/2/28.
 */

public class CanvasView extends View {
    private Paint paint;

    public CanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawARGB(255, 139, 197, 186);
//        drawAxis(canvas);
//        drawText(canvas);
//        drawPoint(canvas);
//        drawLine(canvas);
//        drawRect(canvas);
//        drawCircle(canvas);
//        drawOval(canvas);
//        drawArc(canvas);
        drawPath(canvas);
//        drawBitmap(canvas);
//        drawLayer(canvas);
    }

    private void drawLayer(Canvas canvas) {
        int LAYER_FLAGS = Canvas.MATRIX_SAVE_FLAG | Canvas.CLIP_SAVE_FLAG
                        | Canvas.HAS_ALPHA_LAYER_SAVE_FLAG | Canvas.FULL_COLOR_LAYER_SAVE_FLAG
                       | Canvas.CLIP_TO_LAYER_SAVE_FLAG;
        canvas.drawColor(Color.WHITE);
        canvas.translate(10, 10);
        paint.setColor(Color.RED);
        canvas.drawCircle(75, 75, 75, paint);
        //图层大小
        canvas.saveLayerAlpha(0, 0, 200, 200, 0x88, LAYER_FLAGS); //入栈
        canvas.drawARGB(255, 139, 197, 186);
        paint.setColor(Color.BLUE);
        canvas.drawCircle(125, 125, 75, paint);
        canvas.restore();


        /***************上面去掉saveLayerAlpha方法**********************/
        paint.setColor(Color.RED);
        canvas.drawCircle(300, 75, 75, paint);
        paint.setColor(Color.BLUE);
        canvas.drawCircle(350, 125, 75, paint);

    }


    private void drawBitmap(Canvas canvas){
        BitmapDrawable bitmapDrawable = (BitmapDrawable) getResources().getDrawable(R.mipmap.ic_launcher);
        Bitmap bitmap = bitmapDrawable.getBitmap();
        //如果bitmap不存在，那么就不执行下面的绘制代码
        if(bitmap == null){
            return;
        }

        //直接完全绘制Bitmap
        canvas.drawBitmap(bitmap, 0, 0, paint);

        //绘制Bitmap的一部分，并对其拉伸
        //srcRect定义了要绘制Bitmap的哪一部分
        Rect srcRect = new Rect();
        srcRect.left = 0;
        srcRect.right = bitmap.getWidth();
        srcRect.top = 0;
        srcRect.bottom = (int)(0.33 * bitmap.getHeight());
        float radio = (float)(srcRect.bottom - srcRect.top)  / bitmap.getWidth();
        //dstRecF定义了要将绘制的Bitmap拉伸到哪里
        RectF dstRecF = new RectF();
        dstRecF.left = 0;
        dstRecF.right = canvas.getWidth();
        dstRecF.top = bitmap.getHeight();
        float dstHeight = (dstRecF.right - dstRecF.left) * radio;
        dstRecF.bottom = dstRecF.top + dstHeight;
        canvas.drawBitmap(bitmap, srcRect, dstRecF, paint);
    }

    private void drawPath(Canvas canvas){
        int canvasWidth = canvas.getWidth();
        int deltaX = canvasWidth / 4;
        int deltaY = (int)(deltaX * 0.75);

        paint.setColor(0xff8bc5ba);//设置画笔颜色
        paint.setStrokeWidth(4);//设置线宽

        /*--------------------------用Path画填充面-----------------------------*/
        paint.setStyle(Paint.Style.FILL);//设置画笔为填充模式
        Path path = new Path();
        //向Path中加入Arc
        RectF arcRecF = new RectF(0, 0, deltaX, deltaY);
        path.addArc(arcRecF, 0, 135);
        //向Path中加入Oval
        RectF ovalRecF = new RectF(deltaX, 0, deltaX * 2, deltaY);
        path.addOval(ovalRecF, Path.Direction.CCW);
        //向Path中添加Circle
        path.addCircle((float)(deltaX * 2.5), deltaY / 2, deltaY / 2, Path.Direction.CCW);
        //向Path中添加Rect
        RectF rectF = new RectF(deltaX * 3, 0, deltaX * 4, deltaY);
        path.addRect(rectF, Path.Direction.CCW);
        canvas.drawPath(path, paint);

        /*--------------------------用Path画线--------------------------------*/
        paint.setStyle(Paint.Style.STROKE);//设置画笔为线条模式
        canvas.translate(0, deltaY * 2);
        Path path2 = path;
        canvas.drawPath(path2, paint);

        /*-----------------使用lineTo、arcTo、quadTo、cubicTo画线--------------*/
        paint.setStyle(Paint.Style.STROKE);//设置画笔为线条模式
        canvas.translate(0, deltaY * 2);
        Path path3 = new Path();
        //用pointList记录不同的path的各处的连接点
        List<Point> pointList = new ArrayList<Point>();
        //1. 第一部分，绘制线段
        path3.moveTo(0, 0);
        path3.lineTo(deltaX / 2, 0);//绘制线段
        pointList.add(new Point(0, 0));
        pointList.add(new Point(deltaX / 2, 0));
        //2. 第二部分，绘制椭圆右上角的四分之一的弧线
        RectF arcRecF1 = new RectF(0, 0, deltaX, deltaY);
        path3.arcTo(arcRecF1, 270, 90);//绘制圆弧
        pointList.add(new Point(deltaX, deltaY / 2));
        //3. 第三部分，绘制椭圆左下角的四分之一的弧线
        //注意，我们此处调用了path的moveTo方法，将画笔的移动到我们下一处要绘制arc的起点上
        path3.moveTo(deltaX * 1.5f, deltaY);
        RectF arcRecF2 = new RectF(deltaX, 0, deltaX * 2, deltaY);
        path3.arcTo(arcRecF2, 90, 90);//绘制圆弧
        pointList.add(new Point((int)(deltaX * 1.5), deltaY));
        //4. 第四部分，绘制二阶贝塞尔曲线
        //二阶贝塞尔曲线的起点就是当前画笔的位置，然后需要添加一个控制点，以及一个终点
        //再次通过调用path的moveTo方法，移动画笔
        path3.moveTo(deltaX * 1.5f, deltaY);
        //绘制二阶贝塞尔曲线
        path3.quadTo(deltaX * 2, 0, deltaX * 2.5f, deltaY / 2);
        pointList.add(new Point((int)(deltaX * 2.5), deltaY / 2));
        //5. 第五部分，绘制三阶贝塞尔曲线，三阶贝塞尔曲线的起点也是当前画笔的位置
        //其需要两个控制点，即比二阶贝赛尔曲线多一个控制点，最后也需要一个终点
        //再次通过调用path的moveTo方法，移动画笔
        path3.moveTo(deltaX * 2.5f, deltaY / 2);
        //绘制三阶贝塞尔曲线
        path3.cubicTo(deltaX * 3, 0, deltaX * 3.5f, 0, deltaX * 4, deltaY);
        pointList.add(new Point(deltaX * 4, deltaY));

        //Path准备就绪后，真正将Path绘制到Canvas上
        canvas.drawPath(path3, paint);

        //最后绘制Path的连接点，方便我们大家对比观察
        paint.setStrokeWidth(10);//将点的strokeWidth要设置的比画path时要大
        paint.setStrokeCap(Paint.Cap.ROUND);//将点设置为圆点状
        paint.setColor(0xff0000ff);//设置圆点为蓝色
        for(Point p : pointList){
            //遍历pointList，绘制连接点
            canvas.drawPoint(p.x, p.y, paint);
        }
    }

    private void drawArc(Canvas canvas){
        int canvasWidth = canvas.getWidth();
        int canvasHeight = canvas.getHeight();
        int count = 5;
        float ovalHeight = canvasHeight / (count + 1);
        float left = 10;
        float top = 0;
        float right = canvasWidth - left;
        float bottom= ovalHeight;
        RectF rectF = new RectF(left, top, right, bottom);

        paint.setStrokeWidth(2);//设置线宽
        paint.setColor(0xff8bc5ba);//设置颜色
        paint.setStyle(Paint.Style.FILL);//默认设置画笔为填充模式

        //绘制用drawArc绘制完整的椭圆
        canvas.translate(0, ovalHeight / count);
        canvas.drawArc(rectF, 0, 360, true, paint);

        //绘制椭圆的四分之一,起点是钟表的3点位置，从3点绘制到6点的位置
        canvas.translate(0, (ovalHeight + ovalHeight / count));
        canvas.drawArc(rectF, 0, 90, true, paint);

        //绘制椭圆的四分之一,将useCenter设置为false
        canvas.translate(0, (ovalHeight + ovalHeight / count));
        canvas.drawArc(rectF, 0, 90, false, paint);

        //绘制椭圆的四分之一，只绘制轮廓线
        paint.setStyle(Paint.Style.STROKE);//设置画笔为线条模式
        canvas.translate(0, (ovalHeight + ovalHeight / count));
        canvas.drawArc(rectF, 0, 90, true, paint);

        //绘制带有轮廓线的椭圆的四分之一
        //1. 先绘制椭圆的填充部分
        paint.setStyle(Paint.Style.FILL);//设置画笔为填充模式
        canvas.translate(0, (ovalHeight + ovalHeight / count));
        canvas.drawArc(rectF, 0, 90, true, paint);
        //2. 再绘制椭圆的轮廓线部分
        paint.setStyle(Paint.Style.STROKE);//设置画笔为线条模式
        paint.setColor(0xff0000ff);//设置轮廓线条为蓝色
        canvas.drawArc(rectF, 0, 90, true, paint);
    }

    private void drawOval(Canvas canvas){
        int canvasWidth = canvas.getWidth();
        int canvasHeight = canvas.getHeight();
        float quarter = canvasHeight / 4;
        float left = 10 ;
        float top = 0;
        float right = canvasWidth - left;
        float bottom= quarter;
        RectF rectF = new RectF(left, top, right, bottom);

        //绘制椭圆形轮廓线
        paint.setStyle(Paint.Style.STROKE);//设置画笔为画线条模式
        paint.setStrokeWidth(2);//设置线宽
        paint.setColor(0xff8bc5ba);//设置线条颜色
        canvas.translate(0, quarter / 4);
        canvas.drawOval(rectF, paint);

        //绘制椭圆形填充面
        paint.setStyle(Paint.Style.FILL);//设置画笔为填充模式
        canvas.translate(0, (quarter + quarter / 4));
        canvas.drawOval(rectF, paint);

        //画两个椭圆，形成轮廓线和填充色不同的效果
        canvas.translate(0, (quarter + quarter / 4));
        //1. 首先绘制填充色
        paint.setStyle(Paint.Style.FILL);//设置画笔为填充模式
        canvas.drawOval(rectF, paint);//绘制椭圆形的填充效果
        //2. 将线条颜色设置为蓝色，绘制轮廓线
        paint.setStyle(Paint.Style.STROKE);//设置画笔为线条模式
        paint.setColor(0xff0000ff);//设置填充色为蓝色
        canvas.drawOval(rectF, paint);//设置椭圆的轮廓线
    }

    private void drawCircle(Canvas canvas){
        paint.setColor(0xff8bc5ba);//设置颜色
        paint.setStyle(Paint.Style.FILL);//默认绘图为填充模式
        int canvasWidth = canvas.getWidth();
        int canvasHeight = canvas.getHeight();
        int halfCanvasWidth = canvasWidth / 2;
        int count = 3;
        int D = canvasHeight / (count + 1);
        int R = D / 2;

        //绘制圆
        canvas.translate(0, D / (count + 1));
        canvas.drawCircle(halfCanvasWidth, R, R, paint);

        //通过绘制两个圆形成圆环
        //1. 首先绘制大圆
        canvas.translate(0, D + D / (count + 1));
        canvas.drawCircle(halfCanvasWidth, R, R, paint);
        //2. 然后绘制小圆，让小圆覆盖大圆，形成圆环效果
        int r = (int)(R * 0.75);
        paint.setColor(0xffffffff);//将画笔设置为白色，画小圆
        canvas.drawCircle(halfCanvasWidth, R, r, paint);

        //通过线条绘图模式绘制圆环
        canvas.translate(0, D + D / (count + 1));
        paint.setColor(0xff8bc5ba);//设置颜色
        paint.setStyle(Paint.Style.STROKE);//绘图为线条模式
        float strokeWidth = (float)(R * 0.25);
        paint.setStrokeWidth(strokeWidth);
        canvas.drawCircle(halfCanvasWidth, R, R, paint);
    }

    private void drawRect(Canvas canvas){
        int canvasWidth = canvas.getWidth();
        int canvasHeight = canvas.getHeight();

        //默认画笔的填充色是黑色
        int left1 = 10;
        int top1 = 10;
        int right1 = canvasWidth / 3;
        int bottom1 = canvasHeight /3;
        canvas.drawRect(left1, top1, right1, bottom1, paint);

        //修改画笔颜色
        paint.setColor(0xff8bc5ba);//A:ff,R:8b,G:c5,B:ba
        int left2 = canvasWidth / 3 * 2;
        int top2 = 10;
        int right2 = canvasWidth - 10;
        int bottom2 = canvasHeight / 3;
        canvas.drawRect(left2, top2, right2, bottom2, paint);
    }

    private void drawLine(Canvas canvas) {
        int px = 500;
        int py = 500;
        Paint line = new Paint();
        line.setStrokeWidth(4);
        line.setColor(Color.RED);
        canvas.save();
        canvas.rotate(90, px / 2, py / 2);
        // 画一个向上的箭头
        canvas.drawLine(px / 2, 0, 0, py / 2, line); // 左边的斜杠
        canvas.drawLine(px / 2, 0, px, py / 2, line);// 右边的斜杠
        canvas.drawLine(px / 2, 0, px / 2, py, line);// 垂直的竖杠
        canvas.restore();
        canvas.drawCircle(px - 100, py - 100, 50, line);
    }

    private void drawPoint(Canvas canvas) {
        int canvasWidth = canvas.getWidth();
        int canvasHeight = canvas.getHeight();
        int x = canvasWidth / 2;
        int deltaY = canvasHeight / 3;
        int y = deltaY / 2;
        paint.setColor(0xff8bc5ba);//设置颜色
        paint.setStrokeWidth(50);//设置线宽，如果不设置线宽，无法绘制点

        //绘制Cap为BUTT的点
        paint.setStrokeCap(Paint.Cap.BUTT);
        canvas.drawPoint(x, y, paint);

        //绘制Cap为ROUND的点
        canvas.translate(0, deltaY);
        paint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawPoint(x, y, paint);

        //绘制Cap为SQUARE的点
        canvas.translate(0, deltaY);
        paint.setStrokeCap(Paint.Cap.SQUARE);
        canvas.drawPoint(x, y, paint);
    }

    private void drawAxis(Canvas canvas) {
        int canvasWidth = canvas.getWidth();
        int canvasHeight = canvas.getHeight();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(20);

        //第一次绘制坐标轴
        paint.setColor(0xff00ff00);//绿色
        canvas.drawLine(0, 0, canvasWidth, 0, paint);//绘制x轴
        paint.setColor(0xff0000ff);//蓝色
        canvas.drawLine(0, 0, 0, canvasHeight, paint);//绘制y轴

        //对坐标系平移后，第二次绘制坐标轴
        canvas.translate(canvasWidth / 4, canvasWidth / 4);//把坐标系向右下角平移
        paint.setColor(0xff00ff00);//绿色
        canvas.drawLine(0, 0, canvasWidth, 0, paint);//绘制x轴
        paint.setColor(0xff0000ff);//蓝色
        canvas.drawLine(0, 0, 0, canvasHeight, paint);//绘制y轴

        //再次平移坐标系并在此基础上旋转坐标系，第三次绘制坐标轴
        canvas.translate(canvasWidth / 4, canvasWidth / 4);//在上次平移的基础上再把坐标系向右下角平移
        canvas.rotate(30);//基于当前绘图坐标系的原点旋转坐标系
        paint.setColor(0xff00ff00);//绿色
        canvas.drawLine(0, 0, canvasWidth, 0, paint);//绘制x轴
        paint.setColor(0xff0000ff);//蓝色
        canvas.drawLine(0, 0, 0, canvasHeight, paint);//绘制y轴
    }

    private void drawText(Canvas canvas) {
        int canvasWidth = canvas.getWidth();
        int halfCanvasWidth = canvasWidth / 2;
        float textHeight = 30f;
        float translateY = textHeight;

        //绘制正常文本
        canvas.save();
        canvas.translate(0, translateY);
        canvas.drawText("正常绘制文本", 0, 0, paint);
        canvas.restore();
        translateY += textHeight * 2;

        //绘制绿色文本
        paint.setColor(0xff00ff00);//设置字体为绿色
        canvas.save();
        canvas.translate(0, translateY);//将画笔向下移动
        canvas.drawText("绘制绿色文本", 0, 0, paint);
        canvas.restore();
        paint.setColor(0xff000000);//重新设置为黑色
        translateY += textHeight * 2;

        //设置左对齐
        paint.setTextAlign(Paint.Align.LEFT);//设置左对齐
        canvas.save();
        canvas.translate(halfCanvasWidth, translateY);
        canvas.drawText("左对齐文本", 0, 0, paint);
        canvas.restore();
        translateY += textHeight * 2;

        //设置居中对齐
        paint.setTextAlign(Paint.Align.CENTER);//设置居中对齐
        canvas.save();
        canvas.translate(halfCanvasWidth, translateY);
        canvas.drawText("居中对齐文本", 0, 0, paint);
        canvas.restore();
        translateY += textHeight * 2;

        //设置右对齐
        paint.setTextAlign(Paint.Align.RIGHT);//设置右对齐
        canvas.save();
        canvas.translate(halfCanvasWidth, translateY);
        canvas.drawText("右对齐文本", 0, 0, paint);
        canvas.restore();
        paint.setTextAlign(Paint.Align.LEFT);//重新设置为左对齐
        translateY += textHeight * 2;

        //设置下划线
        paint.setUnderlineText(true);//设置具有下划线
        canvas.save();
        canvas.translate(0, translateY);
        canvas.drawText("下划线文本", 0, 0, paint);
        canvas.restore();
        paint.setUnderlineText(false);//重新设置为没有下划线
        translateY += textHeight * 2;

        //绘制加粗文字
        paint.setFakeBoldText(true);//将画笔设置为粗体
        canvas.save();
        canvas.translate(0, translateY);
        canvas.drawText("粗体文本", 0, 0, paint);
        canvas.restore();
        paint.setFakeBoldText(false);//重新将画笔设置为非粗体状态
        translateY += textHeight * 2;

        //文本绕绘制起点顺时针旋转
        canvas.save();
        canvas.translate(0, translateY);
        canvas.rotate(20);
        canvas.drawText("文本绕绘制起点旋转20度", 0, 0, paint);
        canvas.restore();
    }
}
