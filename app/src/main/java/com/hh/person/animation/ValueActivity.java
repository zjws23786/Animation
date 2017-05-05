package com.hh.person.animation;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.CycleInterpolator;
import android.widget.TextView;

/**
 * Created by hjz on 2017/2/27.
 */

public class ValueActivity extends Activity {

    private static final int RED = 0xffFF8080;

    private static final int BLUE = 0xff8080FF;

    private static final int CYAN = 0xff80ffff;

    private static final int GREEN = 0xff80ff80;

    String tag = "ValueActivity";
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.value_act);
        tv = (TextView) findViewById(R.id.textView1);
         valueAnimator();

//        valueAnimator2();

//         objectValueEvalute();

//         animiSet();

//        interceptorAnimator();
    }

    private void valueAnimator2() {
        ValueAnimator anim = ValueAnimator.ofFloat(0f, 1f);
        anim.setDuration(3000);
        anim.start();
    }

    @SuppressLint("NewApi")
    private void valueAnimator() {
        ValueAnimator anim = ValueAnimator.ofFloat(0f, 1f);

        anim.setDuration(3000);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator arg0) {
                Log.e(tag, "ValueAnimator is:"
                        + arg0.getAnimatedValue().toString());
                float ad = Float.parseFloat(arg0.getAnimatedValue().toString());
                tv.setAlpha(ad);
            }

        });

        anim.setInterpolator(new CycleInterpolator(3));
        anim.start();
    }

    /**
     * IntEvaluator：属性的值类型为int； FloatEvaluator：属性的值类型为float；
     * ArgbEvaluator：属性的值类型为十六进制颜色值； TypeEvaluator：一个接口，可以通过实现该接口自定义Evaluator。
     *
     *
     * objectAnimator extends from value Animator, a simple and easier to use,but it set the animator
     *
     * property must has the setter method.
     *
     */

    @SuppressLint("NewApi")
    private void objectValueEvalute() {

        /**
         * "alpha" comes from the target's setter and getter method , etc setAlpha(float arg0)
         */
        ValueAnimator colorAnim = ObjectAnimator.ofFloat(tv, "alpha", 0, 1f);
        colorAnim.setDuration(9000L);

        // arg0 is between 0 and 1
        colorAnim.setEvaluator(new TypeEvaluator<Object>() {

            @Override
            public Object evaluate(float arg0, Object arg1, Object arg2) {
                // TODO Auto-generated method stub

                Log.e(tag,
                        "evaluateevaluate+arg0 = " + arg0 + "   "
                                + arg1.toString() + "  " + arg2.toString());

                float a1 = Float.parseFloat(arg1.toString());
                float a2 = Float.parseFloat(arg2.toString());
                float a3 = a1 + arg0 * (a2 - a1);
                Log.e(tag, "the result is :" + a3);
                return a3;
            }

        });

        colorAnim.setRepeatCount(100);

        colorAnim.setRepeatMode(ValueAnimator.RESTART);

        colorAnim.start();

    }

    @SuppressLint("NewApi")
    private void animiSet() {

        ValueAnimator a1 = ObjectAnimator.ofFloat(tv, "alpha", 0, 1f);
        ValueAnimator a2 = ObjectAnimator.ofFloat(tv, "textSize", 0, 19f);
        ValueAnimator a3 = ObjectAnimator.ofFloat(tv, "x", 0, 200f);

        AnimatorSet set = new AnimatorSet();
        set.setDuration(8000);
        set.play(a1).before(a2);
        set.play(a2).with(a3);
        set.start();

    }

    @SuppressLint("NewApi")
    private void interceptorAnimator() {
        ValueAnimator a2 = ObjectAnimator.ofFloat(tv, "textSize", 0, 19f);

        /**
         * arg0 between 0 and 1 ,0 means start ,and 1 means end
         */
        a2.setInterpolator(new TimeInterpolator() {

            @Override
            public float getInterpolation(float arg0) {
                return 1.5f;
            }

        });

        // arg0 in the evalutor comes from the return value of getInterpolation
        a2.setEvaluator(new TypeEvaluator<Object>() {

            @Override
            public Object evaluate(float arg0, Object arg1, Object arg2) {
                // TODO Auto-generated method stub

                Log.e(tag,
                        "evaluateevaluate+arg0 = " + arg0 + "   "
                                + arg1.toString() + "  " + arg2.toString());

                float a1 = Float.parseFloat(arg1.toString());
                float a2 = Float.parseFloat(arg2.toString());
                float a3 = a1 + arg0 * (a2 - a1);
                Log.e(tag, "the result is :" + a3);
                return a3;
            }

        });

        a2.setDuration(6000);
        a2.start();
    }

}

