package com.hh.person.animation.custom;

import android.animation.TypeEvaluator;
import android.graphics.PointF;

/**
 * Created by hjz on 2017/2/27.
 */

public class BeziTwoEvaluator implements TypeEvaluator<PointF> {
    private PointF pointF1;

    public BeziTwoEvaluator(PointF pointF1){
        this.pointF1 = pointF1;
    }

    @Override
    public PointF evaluate(float t, PointF startValue, PointF endValue) {
        float s = 1-t;
        PointF pointF = new PointF();
        pointF.x = s*s*startValue.x + 2*t*s*pointF1.x + t*t*endValue.x;
        pointF.y = s*s*startValue.y + 2*t*s*pointF1.y + t*t*endValue.y;
        return pointF;
    }
}
