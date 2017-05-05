package com.hh.person.animation.custom;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.ComposePathEffect;
import android.graphics.ComposeShader;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.LightingColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.SumPathEffect;
import android.graphics.SweepGradient;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.View;

import com.hh.person.animation.R;

/**
 * Created by hjz on 2017/2/28.
 */

public class PaintView extends View {
    private Context mContext;
    private Paint paint;

    public PaintView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);


        //创建,并初始化Path
//        path = new Path();
//        path.moveTo(0, 0);
//        for(int i = 1; i<= 15; i++)
//        {
//            //生成15个点,随机生成它们的坐标,并将它们连成一条Path
//            path.lineTo(i*20, (float)Math.random()*60);
//        }
//        //初始化七个颜色
//        colors = new int[] {
//                Color.BLACK,Color.BLUE,Color.CYAN,
//                Color.GREEN,Color.MAGENTA,Color.RED,Color.YELLOW
//        };
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        setARGB(canvas);
//        setColorFilter(canvas);
//        drawBitmap(canvas);
//        setLightingColorFilter(canvas);
//        setPorterDuffColorFilter(canvas);
//        setPorterDuffColorFilter1(canvas);
//        setPathEffect(canvas);
//        setPathEffect1(canvas);

//        setLinearGradient(canvas);
//        setRadialGradient(canvas);
//        setSweepGradient(canvas);
//        setBitmapShader(canvas);
//        setComposeShader(canvas);
//        setSrokeJoin(canvas);
        setStrokeCap(canvas);

    }

    private void setStrokeCap(Canvas canvas) {
        canvas.save();
        paint.setStrokeWidth(50);
        paint.setColor(Color.BLACK);
        paint.setTextSize(40);
        canvas.drawText("没用StrokeCap", 550,40,paint);
        canvas.drawLine(40,20,500,20,paint);
        canvas.restore();

        canvas.save();
        canvas.translate(0,100);
        paint.setStrokeWidth(50);
        paint.setColor(Color.BLACK);
        canvas.drawText("Paint.Cap.ROUND", 550,40,paint);
        paint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawLine(40,20,500,20,paint);
        canvas.restore();

        canvas.save();
        canvas.translate(0,200);
        paint.setStrokeWidth(50);
        paint.setColor(Color.BLACK);
        canvas.drawText("Paint.Cap.SQUARE", 550,40,paint);
        paint.setStrokeCap(Paint.Cap.SQUARE);
        canvas.drawLine(40,20,500,20,paint);
        canvas.restore();

        canvas.save();
        canvas.translate(0,300);
        paint.setStrokeWidth(50);
        paint.setColor(Color.BLACK);
        canvas.drawText("Paint.Cap.BUTT", 550,40,paint);
        paint.setStrokeCap(Paint.Cap.BUTT);
        canvas.drawLine(40,20,500,20,paint);
        canvas.restore();
    }

    private void setSrokeJoin(Canvas canvas) {
        canvas.save();
        paint.setStrokeWidth(10);
        paint.setColor(Color.BLACK);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setTextSize(50);
        canvas.drawText("SrokeJoin_ROUND",40,40,paint);
        //创建位图
        Bitmap mBitmap = ((BitmapDrawable) getResources().getDrawable(R.mipmap.fj_img)).getBitmap();
        canvas.drawBitmap(mBitmap,10,10,paint);
        canvas.restore();

        canvas.save();
        canvas.translate(0,400);
        paint.setStrokeWidth(10);
        paint.setColor(Color.BLACK);
        paint.setStrokeJoin(Paint.Join.BEVEL);
        paint.setTextSize(50);
        canvas.drawText("SrokeJoin_BEVEL",40,40,paint);
        mBitmap = ((BitmapDrawable) getResources().getDrawable(R.mipmap.fj_img)).getBitmap();
        canvas.drawBitmap(mBitmap,10,10,paint);
        canvas.restore();

        canvas.save();
        canvas.translate(0,800);
        paint.setStrokeWidth(10);
        paint.setColor(Color.BLACK);
        paint.setStrokeJoin(Paint.Join.MITER);
        paint.setTextSize(50);
        canvas.drawText("SrokeJoin_MITER",40,40,paint);
        mBitmap = ((BitmapDrawable) getResources().getDrawable(R.mipmap.fj_img)).getBitmap();
        canvas.drawBitmap(mBitmap,10,10,paint);
        canvas.restore();
    }

    private void setComposeShader(Canvas canvas) {

        canvas.save();
        //创建位图
        Bitmap mBitmap = ((BitmapDrawable) getResources().getDrawable(R.mipmap.fj_img)).getBitmap();
        //将图scale成我们想要的大小
        mBitmap = Bitmap.createScaledBitmap(mBitmap, 400, 400, false);

        // 创建位图渲染
        BitmapShader bitmapShader = new BitmapShader(mBitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        paint.setShader(bitmapShader);
        canvas.drawCircle(200, 200, 200, paint);
        canvas.restore();

        canvas.save();
        canvas.translate(0,450);

        // 创建位图渲染
        bitmapShader = new BitmapShader(mBitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        // 创建环形渐变
        RadialGradient radialGradient = new RadialGradient(200, 200, 200,
                Color.TRANSPARENT, Color.WHITE, Shader.TileMode.MIRROR);
        // 创建组合渐变，由于直接按原样绘制就好，所以选择Mode.SRC_OVER
        ComposeShader composeShader = new ComposeShader(bitmapShader, radialGradient,
                PorterDuff.Mode.SRC_OVER);
        // 将组合渐变设置给paint
        paint.setShader(composeShader);
        canvas.drawCircle(200, 200, 200, paint);
        canvas.restore();
    }

    private void setBitmapShader(Canvas canvas) {
        canvas.save();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.fj_img);
        Shader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP,
                Shader.TileMode.CLAMP);
        paint.setShader(bitmapShader);
        canvas.drawRect(0, 0, getWidth(), 400,paint);
        paint.reset();
        paint.setColor(Color.RED);
        paint.setTextSize(36);
        canvas.drawText("tileX=CLAMP;tileY=CLAMP",20,60,paint);
        canvas.restore();

        canvas.save();
        canvas.translate(0,450);
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.fj_img);
        bitmapShader = new BitmapShader(bitmap, Shader.TileMode.MIRROR,
                Shader.TileMode.MIRROR);
        paint.setShader(bitmapShader);
        canvas.drawRect(0, 0, getWidth(), 400,paint);
        paint.reset();
        paint.setColor(Color.RED);
        paint.setTextSize(36);
        canvas.drawText("tileX=MIRROR;tileY=MIRROR",20,60,paint);
        canvas.restore();

        canvas.save();
        canvas.translate(0,900);
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.fj_img);
        bitmapShader = new BitmapShader(bitmap, Shader.TileMode.REPEAT,
                Shader.TileMode.REPEAT);
        paint.setShader(bitmapShader);
        canvas.drawRect(0, 0, getWidth(), 400,paint);
        paint.reset();
        paint.setColor(Color.RED);
        paint.setTextSize(36);
        canvas.drawText("tileX=REPEAT;tileY=REPEAT",20,60,paint);
        canvas.restore();

        canvas.save();
        canvas.translate(0,1350);
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.fj_img);
        bitmapShader = new BitmapShader(bitmap, Shader.TileMode.MIRROR,
                Shader.TileMode.REPEAT);
        paint.setShader(bitmapShader);
        canvas.drawRect(0, 0, getWidth(), 400,paint);
        paint.reset();
        paint.setColor(Color.RED);
        paint.setTextSize(36);
        canvas.drawText("tileX=MIRROR;tileY=REPEAT",20,60,paint);
        canvas.restore();
    }

    private void setSweepGradient(Canvas canvas) {
        Shader sweepGradient = new SweepGradient(400, 400, new int[] { Color.YELLOW,
                Color.RED, Color.BLUE, Color.GREEN }, new float[] { 0, 0.2f,0.6f, 0.9f });
        paint.setShader(sweepGradient);
        canvas.drawCircle(400, 400, 300, paint);
    }

    private void setRadialGradient(Canvas canvas) {
        int rightBottomY = 400;
        int rightBottomX = 400;

        canvas.save();
        paint.setColor(Color.BLACK);
        paint.setTextSize(36);
        canvas.drawText("Shader.TileMode.CLAMP",rightBottomX+30,rightBottomY/2,paint);
        Shader radialGradient = new RadialGradient(rightBottomX/2, rightBottomY/2, 200, new int[] {
                Color.YELLOW, Color.RED, Color.BLUE, Color.GREEN }, null,
                Shader.TileMode.CLAMP);
        paint.setShader(radialGradient);
        canvas.drawRect(0, 0, rightBottomX, rightBottomY,paint);
        canvas.restore();

        canvas.save();
        canvas.translate(0,450);
        paint.setColor(Color.BLACK);
        paint.setTextSize(36);
        canvas.drawText("Shader.TileMode.MIRROR",rightBottomX+30,rightBottomY/2,paint);
        radialGradient = new RadialGradient(rightBottomX/2, rightBottomY/2, 200, new int[] {
                Color.YELLOW, Color.RED, Color.BLUE, Color.GREEN }, null,
                Shader.TileMode.MIRROR);
        paint.setShader(radialGradient);
        canvas.drawRect(0, 0, rightBottomX, rightBottomY,paint);
        canvas.restore();

        canvas.save();
        canvas.translate(0,900);
        paint.setColor(Color.BLACK);
        paint.setTextSize(36);
        canvas.drawText("Shader.TileMode.REPEAT",rightBottomX+30,rightBottomY/2,paint);
        radialGradient = new RadialGradient(rightBottomX/2, rightBottomY/2, 200, new int[] {
                Color.YELLOW, Color.RED, Color.BLUE, Color.GREEN }, null,
                Shader.TileMode.REPEAT);
        paint.setShader(radialGradient);
        canvas.drawRect(0, 0, rightBottomX, rightBottomY,paint);
        canvas.restore();
    }

    private void setLinearGradient(Canvas canvas) {
        // 定义起始和最终颜色
        int mStartColor = 0xff0000ff; //蓝色
        int mToColor = 0xffff0000;  //红色
        int rightBottomY = 400;
        int rightBottomX = 400;

        canvas.save();
        paint.setColor(Color.BLACK);
        paint.setTextSize(36);
        canvas.drawText("Shader.TileMode.CLAMP",rightBottomX+30,rightBottomY/2,paint);
        Shader linearGradient = new LinearGradient(80, rightBottomY/2, rightBottomX, rightBottomY/2, mStartColor, mToColor, Shader.TileMode.CLAMP);
        paint.setShader(linearGradient);
        canvas.drawRect(0, 0, rightBottomX, rightBottomY,paint);
        canvas.restore();

        canvas.save();
        canvas.translate(0,450);
        paint.setColor(Color.BLACK);
        paint.setTextSize(36);
        canvas.drawText("Shader.TileMode.MIRROR",rightBottomX+30,rightBottomY/2,paint);
        linearGradient = new LinearGradient(80, rightBottomY/2, rightBottomX, rightBottomY/2, mStartColor, mToColor, Shader.TileMode.MIRROR);
        paint.setShader(linearGradient);
        canvas.drawRect(0, 0, rightBottomX, rightBottomY,paint);
        canvas.restore();

        canvas.save();
        canvas.translate(0,900);
        paint.setColor(Color.BLACK);
        paint.setTextSize(36);
        canvas.drawText("Shader.TileMode.REPEAT",rightBottomX+30,rightBottomY/2,paint);
        linearGradient = new LinearGradient(80, rightBottomY/2, rightBottomX, rightBottomY/2, mStartColor, mToColor, Shader.TileMode.REPEAT);
        paint.setShader(linearGradient);
        canvas.drawRect(0, 0, rightBottomX, rightBottomY,paint);
        canvas.restore();
    }

    float phase;
    PathEffect[] effects = new PathEffect[7];
    int[] colors;
    Path path;
    private void setPathEffect1(Canvas canvas) {
        //使用路径效果
        effects[0] = null;
        //使用CornerPathEffect路径效果
        effects[1] = new CornerPathEffect(10);
        //初始化DiscretePathEffect
        effects[2] = new DiscretePathEffect(3.0f,5.0f);
        //初始化DashPathEffect
        effects[3] = new DashPathEffect(new float[]{20,10,5,10},phase);
        //初始化PathDashPathEffect
        Path p = new Path();
        p.addRect(0, 0, 8, 8, Path.Direction.CCW);
        effects[4] = new PathDashPathEffect(p,12,phase,PathDashPathEffect.Style.ROTATE);
        //初始化PathDashPathEffect
        effects[5] = new ComposePathEffect(effects[2],effects[4]);
        effects[6] = new SumPathEffect(effects[4],effects[3]);
        //将画布移到8,8处开始绘制
        canvas.translate(8, 8);
        //依次使用7中不同路径效果,7种不同的颜色来绘制路径
        for(int i = 0; i < effects.length; i++)
        {
            paint.setPathEffect(effects[i]);
            paint.setColor(colors[i]);
            canvas.drawPath(path, paint);
            canvas.translate(0, 60);
        }
        //改变phase值,形成动画效果
        phase += 1;
        invalidate();
    }

    private float mPhase;
    private void setPathEffect(Canvas canvas) {
        // 使用CornerPathEffect路径效果--参数为圆角半径
//        setCornerPathEffect(canvas);

        // 初始化DiscretePathEffect －－ segmentLength指定最大的段长，deviation指定偏离量
//        setDiscretePathEffect(canvas);

        // 初始化DashPathEffect －－intervals为虚线的ON和OFF数组，offset为绘制时的偏移量
        setDashPathEffect(canvas);

        // 初始化PathDashPathEffect
//        Path p = new Path();
//        p.addRect(0, 0, 8, 8, Path.Direction.CCW);
        // shape则是指填充图形，advance指每个图形间的间距，phase为绘制时的偏移量，style为该类自由的枚举值
//        setPathDashPathEffect(canvas);

        // 组合效果
//        setComposePathEffect(canvas);
//        PathEffect mEffects = new ComposePathEffect(mEffects, mEffects);

        // 叠加效果
//        setSumPathEffect(canvas);


        // 依次使用7种不同路径效果,7种不同的颜色来绘制路径
        /*for (int i = 0; i < mEffects.length; i++)
        {
            paint.setPathEffect(mEffects[i]);
            paint.setColor(mColors[i]);
            canvas.drawPath(mPath, paint);
            canvas.translate(0, 60);
        }*/
    }

    private void setSumPathEffect(Canvas canvas) {
        // 创建,并初始化Path
        Path mPath = new Path();
        mPath.moveTo(0, 0);
        for (int i = 1; i <= 15; i++)
        {
            // 生成15个点,随机生成它们的坐标,并将它们连成一条Path
            mPath.lineTo(i * 20, (float) Math.random() * 60);
        }

        Path p = new Path();
        p.addRect(0, 0, 8, 8, Path.Direction.CCW);
        // shape则是指填充图形，advance指每个图形间的间距，phase为绘制时的偏移量，style为该类自由的枚举值
        PathEffect mEffect1 = new PathDashPathEffect(p, 12, 0f, PathDashPathEffect.Style.ROTATE);
        // 初始化DashPathEffect －－intervals为虚线的ON和OFF数组，offset为绘制时的偏移量
        PathEffect mEffect2 = new DashPathEffect(new float[] {
                20, 10, 5, 10
        }, 0f);
        // 叠加效果
        PathEffect mEffects = new SumPathEffect(mEffect1, mEffect2);
        paint.setPathEffect(mEffects);
        paint.setColor(Color.GREEN);
        canvas.drawPath(mPath, paint);
    }

    private void setComposePathEffect(Canvas canvas) {
        // 创建,并初始化Path
        Path mPath = new Path();
        mPath.moveTo(0, 0);
        for (int i = 1; i <= 15; i++)
        {
            // 生成15个点,随机生成它们的坐标,并将它们连成一条Path
            mPath.lineTo(i * 20, (float) Math.random() * 60);
        }
        // 初始化DiscretePathEffect －－ segmentLength指定最大的段长，deviation指定偏离量
        PathEffect mEffect1 = new DiscretePathEffect(3.0f, 5.0f);

        Path p = new Path();
        p.addRect(0, 0, 8, 8, Path.Direction.CCW);
        // shape则是指填充图形，advance指每个图形间的间距，phase为绘制时的偏移量，style为该类自由的枚举值
        PathEffect mEffect2 = new PathDashPathEffect(p, 12, mPhase, PathDashPathEffect.Style.ROTATE);
        // 组合效果
        PathEffect mEffects = new ComposePathEffect(mEffect1, mEffect2);
        paint.setPathEffect(mEffects);
        paint.setColor(Color.GREEN);
        canvas.drawPath(mPath, paint);

    }

    private void setPathDashPathEffect(Canvas canvas) {

        // 创建,并初始化Path
        Path mPath = new Path();
        mPath.moveTo(0, 0);
        for (int i = 1; i <= 15; i++)
        {
            // 生成15个点,随机生成它们的坐标,并将它们连成一条Path
            mPath.lineTo(i * 20, (float) Math.random() * 60);
        }
        paint.setColor(Color.BLACK);
        canvas.drawPath(mPath, paint);
        canvas.translate(0, 100);

        Path p = new Path();
        p.addRect(0, 0, 8, 8, Path.Direction.CCW);
        // shape则是指填充图形，advance指每个图形间的间距，phase为绘制时的偏移量，style为该类自由的枚举值
        PathEffect mEffects = new PathDashPathEffect(p, 12, mPhase, PathDashPathEffect.Style.ROTATE);
        paint.setPathEffect(mEffects);
        paint.setColor(Color.GREEN);
        canvas.drawPath(mPath, paint);
    }

    private void setDashPathEffect(Canvas canvas) {

        // 创建,并初始化Path
        Path mPath = new Path();
        mPath.moveTo(0, 0);
        for (int i = 1; i <= 15; i++)
        {
            // 生成15个点,随机生成它们的坐标,并将它们连成一条Path
            mPath.lineTo(i * 20, (float) Math.random() * 60);
        }
        paint.setColor(Color.BLACK);
        canvas.drawPath(mPath, paint);

        canvas.save();
        canvas.translate(0, 100);

        // 初始化DashPathEffect －－intervals为虚线的ON和OFF数组，offset为绘制时的偏移量
        PathEffect mEffects = new DashPathEffect(new float[]{20,10,5,10},phase);
        paint.setPathEffect(mEffects);
        paint.setColor(Color.RED);
        canvas.drawPath(mPath, paint);
        canvas.restore();
    }

    private void setDiscretePathEffect(Canvas canvas) {

        // 创建,并初始化Path
        Path mPath = new Path();
        mPath.moveTo(0, 0);
        for (int i = 1; i <= 15; i++)
        {
            // 生成15个点,随机生成它们的坐标,并将它们连成一条Path
            mPath.lineTo(i * 20, (float) Math.random() * 100);
        }
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(20);
        canvas.drawPath(mPath, paint);
        canvas.translate(0, 100);

        // 初始化DiscretePathEffect －－ segmentLength指定最大的段长，deviation指定偏离量
        PathEffect mEffects = new DiscretePathEffect(3.0f, 5.0f);
        paint.setPathEffect(mEffects);
        paint.setColor(Color.RED);
        canvas.drawPath(mPath, paint);
    }


    private void setCornerPathEffect(Canvas canvas) {

        // 创建,并初始化Path
        Path mPath = new Path();
        mPath.moveTo(0, 0);
        for (int i = 1; i <= 15; i++)
        {
            // 生成15个点,随机生成它们的坐标,并将它们连成一条Path
            mPath.lineTo(i * 20, (float) Math.random() * 100);
        }
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(40);
        canvas.drawPath(mPath, paint);
        canvas.translate(0, 200);

        //使用CornerPathEffect路径效果--参数为圆角半径
        PathEffect mEffects = new CornerPathEffect(10);
        paint.setPathEffect(mEffects);
        paint.setColor(Color.BLUE);
        canvas.drawPath(mPath, paint);
    }

    private void setPorterDuffColorFilter(Canvas canvas) {

        int canvasWidth = canvas.getWidth();
        int canvasHeight = canvas.getHeight();
        int circleColor = 0xffffcc44;//黄色
        int rectColor = 0xff66aaff;//蓝色

        /*第一行*/
        canvas.save();
        int clearLy = canvas.saveLayer(0, 0, canvasWidth, canvasHeight, null, Canvas.ALL_SAVE_FLAG);
        drawText("clear",canvas);
        //clear所绘制源图像不会提交到画布上
        drawRect(canvas, rectColor,PorterDuff.Mode.CLEAR);
        drawCircle(canvas,circleColor);
        canvas.restoreToCount(clearLy);

        canvas.save();
        paint.reset();
        int srcLy = canvas.saveLayer(0, 0, canvasWidth, canvasHeight, null, Canvas.ALL_SAVE_FLAG);
        canvas.translate(300,0);
        drawText("src",canvas);
        //src只显示源图像
        drawCircle(canvas,circleColor);
        drawRect(canvas, rectColor,PorterDuff.Mode.SRC);
        canvas.restoreToCount(srcLy);

        canvas.save();
        paint.reset();
        int dstLy = canvas.saveLayer(0, 0, canvasWidth, canvasHeight, null, Canvas.ALL_SAVE_FLAG);
        canvas.translate(600,0);
        drawCircle(canvas,circleColor);
        drawText("dst",canvas);
        //dst只显示目标图像
        drawRect(canvas, rectColor,PorterDuff.Mode.DST,dstLy);

        /*第二行*/
        canvas.save();
        paint.reset();
        int srcOverLy = canvas.saveLayer(0, 0, canvasWidth, canvasHeight, null, Canvas.ALL_SAVE_FLAG);
        canvas.translate(0,360);
        drawText("src_over",canvas);
        drawCircle(canvas,circleColor);
        // src_over正常绘制显示，源图像居上显示
        drawRect(canvas, rectColor,PorterDuff.Mode.SRC_OVER,srcOverLy);

        canvas.save();
        paint.reset();
        int dstOverLy = canvas.saveLayer(0, 0, canvasWidth, canvasHeight, null, Canvas.ALL_SAVE_FLAG);
        canvas.translate(300,360);
        drawText("dst_over",canvas);
        drawCircle(canvas,circleColor);
        //dst_over上下层都显示。目标图像居上显示
        drawRect(canvas, rectColor,PorterDuff.Mode.DST_OVER,dstOverLy);

        canvas.save();
        paint.reset();
        int srcInLy = canvas.saveLayer(0, 0, canvasWidth, canvasHeight, null, Canvas.ALL_SAVE_FLAG);
        canvas.translate(600,360);
        drawText("src_in",canvas);
        // src_in取两层绘制交集中的源图像
        drawRect(canvas, rectColor);
        drawCircle(canvas,circleColor,PorterDuff.Mode.SRC_IN);
        canvas.restoreToCount(srcInLy);


         /*第三行*/
        canvas.save();
        paint.reset();
        int dstInLy = canvas.saveLayer(0, 0, canvasWidth, canvasHeight, null, Canvas.ALL_SAVE_FLAG);
        canvas.translate(0,720);
        drawText("dst_in",canvas);
        //dst_in取两层绘制交集中的目标图像
        drawCircle(canvas,circleColor);
        drawRect(canvas, rectColor, PorterDuff.Mode.DST_IN);
        canvas.restoreToCount(dstInLy);

        canvas.save();
        paint.reset();
        int srcOutLy = canvas.saveLayer(0, 0, canvasWidth, canvasHeight, null, Canvas.ALL_SAVE_FLAG);
        canvas.translate(300,720);
        drawText("src_out",canvas);
        //src_out 只在源图像和目标图像不相交的地方绘制源图像
        drawRect(canvas, rectColor,PorterDuff.Mode.SRC_OUT);
        drawCircle(canvas,circleColor, PorterDuff.Mode.DST_OUT);
        canvas.restoreToCount(srcOutLy);

        canvas.save();
        paint.reset();
        int dstOutLy = canvas.saveLayer(0, 0, canvasWidth, canvasHeight, null, Canvas.ALL_SAVE_FLAG);
        canvas.translate(600,720);
        drawText("dst_out",canvas);
        //dst_out只在源图像和目标图像不相交的地方绘制目标图像
        drawCircle(canvas,circleColor,PorterDuff.Mode.SRC_OUT);
        drawRect(canvas, rectColor,PorterDuff.Mode.DST_OUT);
        canvas.restoreToCount(dstOutLy);

         /*第四行*/
        canvas.save();
        paint.reset();
        int srcAtopLy = canvas.saveLayer(0, 0, canvasWidth, canvasHeight, null, Canvas.ALL_SAVE_FLAG);
        canvas.translate(0,1080);
        drawCircle(canvas,circleColor);
        drawText("src_Atop",canvas);
        //src_atop在源图像和目标图像相交的地方绘制源图像，在不相交的地方绘制目标图像
        drawRect(canvas, rectColor,PorterDuff.Mode.SRC_ATOP,srcAtopLy);

        canvas.save();
        paint.reset();
        int dstAtopLy = canvas.saveLayer(0, 0, canvasWidth, canvasHeight, null, Canvas.ALL_SAVE_FLAG);
        canvas.translate(300,1080);
        drawText("dst_Atop",canvas);
        //dst_atop在源图像和目标图像相交的地方绘制目标图像而在不相交的地方绘制源图像
        drawRect(canvas, rectColor,PorterDuff.Mode.DST_ATOP);
        drawCircle(canvas,circleColor,PorterDuff.Mode.SRC_ATOP);
        canvas.restoreToCount(dstAtopLy);

        canvas.save();
        paint.reset();
        int xorLy = canvas.saveLayer(0, 0, canvasWidth, canvasHeight, null, Canvas.ALL_SAVE_FLAG);
        canvas.translate(600,1080);
        drawCircle(canvas,circleColor);
        drawText("xor",canvas);
        //xor异或：去除两图层交集部分
        drawRect(canvas, rectColor,PorterDuff.Mode.XOR,xorLy);

         /*第五行*/
        canvas.save();
        paint.reset();
        int darkenLy = canvas.saveLayer(0, 0, canvasWidth, canvasHeight, null, Canvas.ALL_SAVE_FLAG);
        canvas.translate(0,1440);
        drawCircle(canvas,circleColor);
        drawText("darken",canvas);
        // darken取两图层全部区域，交集部分颜色加深
        drawRect(canvas, rectColor,PorterDuff.Mode.DARKEN,darkenLy);

        canvas.save();
        paint.reset();
        int lighterLy = canvas.saveLayer(0, 0, canvasWidth, canvasHeight, null, Canvas.ALL_SAVE_FLAG);
        canvas.translate(300,1440);
        drawCircle(canvas,circleColor);
        drawText("lighter",canvas);
        //lighter取两图层全部，点亮交集部分颜色
        drawRect(canvas, rectColor,PorterDuff.Mode.LIGHTEN,lighterLy);

        canvas.save();
        paint.reset();
        int multiplyLy = canvas.saveLayer(0, 0, canvasWidth, canvasHeight, null, Canvas.ALL_SAVE_FLAG);
        canvas.translate(600,1440);
        drawText("multiply",canvas);
        // multiply取两图层交集部分叠加后颜色
        drawCircle(canvas,circleColor);
        drawRect(canvas, rectColor,PorterDuff.Mode.MULTIPLY,multiplyLy);

         /*第六行*/
        canvas.save();
        paint.reset();
        int screenLy = canvas.saveLayer(0, 0, canvasWidth, canvasHeight, null, Canvas.ALL_SAVE_FLAG);
        canvas.translate(0,1800);
        drawText("screen",canvas);
        //screen滤色
        drawCircle(canvas,circleColor,PorterDuff.Mode.SCREEN);
        drawRect(canvas, rectColor,PorterDuff.Mode.SCREEN);
        canvas.restoreToCount(screenLy);


    }

    private void drawCircle(Canvas canvas,int color){
        paint.setColor(color);
        canvas.drawCircle(80,80,80,paint);
        paint.setXfermode(null);
    }
    private void drawCircle(Canvas canvas,int color,PorterDuff.Mode xferMode){
        paint.setXfermode(new PorterDuffXfermode(xferMode));
        paint.setColor(color);
        canvas.drawCircle(80,80,80,paint);
        paint.setXfermode(null);
    }

    private void drawCircle(Canvas canvas,int color,PorterDuff.Mode xferMode, int saveCount){
        paint.setXfermode(new PorterDuffXfermode(xferMode));
        paint.setColor(color);
        canvas.drawCircle(80,80,80,paint);
        paint.setXfermode(null);
        canvas.restoreToCount(saveCount);

    }

    private void drawRect(Canvas canvas, int color) {
        paint.setColor(color);
        canvas.drawRect(80,80,240,240,paint);
        paint.setXfermode(null);
    }

    private void drawRect(Canvas canvas, int color, PorterDuff.Mode xferMode) {
        paint.setXfermode(new PorterDuffXfermode(xferMode));
        paint.setColor(color);
        canvas.drawRect(80,80,240,240,paint);
        paint.setXfermode(null);
    }

    private void drawRect(Canvas canvas, int color, PorterDuff.Mode xferMode, int saveCount) {
        paint.setXfermode(new PorterDuffXfermode(xferMode));
        paint.setColor(color);
        canvas.drawRect(80,80,240,240,paint);
        paint.setXfermode(null);
        canvas.restoreToCount(saveCount);
    }

    private void drawText(String str,Canvas canvas){
        paint.setTextSize(56);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(2);
        canvas.drawText(str,100,280,paint);
        paint.setXfermode(null);
    }

    private void setPorterDuffColorFilter1(Canvas canvas) {
        PorterDuffColorFilter colorFilter = new PorterDuffColorFilter(0XFF0000FF, PorterDuff.Mode.DARKEN);
        paint.setColorFilter(colorFilter);
        //获取图片
        Bitmap mBitmap = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.fj_img);
        canvas.drawBitmap(mBitmap, 50, 50, paint);
    }

    private void setLightingColorFilter(Canvas canvas) {
        LightingColorFilter colorFilter = new LightingColorFilter(0xFFFFFF00, 0x00000000);
        paint.setColorFilter(colorFilter);
        //获取图片
        Bitmap mBitmap = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.fj_img);
        canvas.drawBitmap(mBitmap, 50, 50, paint);
    }

    private void drawBitmap(Canvas canvas) {
        ColorMatrixColorFilter colorFilter = new ColorMatrixColorFilter(new float[]{
                0.33F, 0.59F, 0.11F, 0, 0,
                0.33F, 0.59F, 0.11F, 0, 0,
                0.33F, 0.59F, 0.11F, 0, 0,
                0, 0, 0, 1, 0,
        });
        paint.setColorFilter(colorFilter);
        //获取图片
        Bitmap mBitmap = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.fj_img);
        canvas.drawBitmap(mBitmap, 50, 50, paint);
    }

    public void setARGB(Canvas canvas) {
        paint.setARGB(88,255,0,0);
        paint.setStrokeWidth(2);
//        paint.setColor();
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawRect(20,20,80,80,paint);
    }


    public void setColorFilter(Canvas canvas) {
        paint.setColor(Color.argb(255, 255, 128, 102));
        ColorMatrixColorFilter colorFilter = new ColorMatrixColorFilter(new float[]{
                0.5F, 0, 0, 0, 0,
                0, 0.5F, 0, 0, 0,
                0, 0, 0.5F, 0, 0,
                0, 0, 0, 1, 0
        });
        paint.setColorFilter(colorFilter);
        //画一个圆形
        canvas.drawCircle(200,
                200, 100, paint);
    }
}
