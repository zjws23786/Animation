package com.hh.person.animation.widget;

import android.animation.TypeEvaluator;
import android.graphics.PointF;

/**
 * Created by Administrator on 2017/9/28 0028.
 */

public class BezierEvaluator implements TypeEvaluator<PointF> {
    private PointF pointF1;
    private PointF pointF2;

    public BezierEvaluator(PointF pointF1, PointF pointF2) {
        this.pointF1 = pointF1;
        this.pointF2 = pointF2;
    }

    @Override
    public PointF evaluate(float t, PointF pointF0, PointF pointF3) {
        float s = 1-t;
        PointF pointF = new PointF();
        pointF.x = pointF0.x*s*s*s + 3*pointF1.x*t*s*s + 3*pointF2.x*t*t*s+pointF3.x*t*t*t;
        pointF.y = pointF0.y*s*s*s + 3*pointF1.y*t*s*s + 3*pointF2.y*t*t*s+pointF3.y*t*t*t;
        return pointF;
    }
}
