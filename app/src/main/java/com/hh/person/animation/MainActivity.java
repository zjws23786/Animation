package com.hh.person.animation;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.hh.person.animation.base.BaseActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private TextView svgTv;
    private TextView valueAnimatorTv;
    private TextView rotationTv;
    private TextView tranTv;
    private TextView scaleTv;
    private TextView alphaTv;
    private TextView useTv;
    private TextView regionTv;
    private TextView elmTv;
    private TextView canvasTv;
    private TextView paintTv;
    private TextView chargeTv;
    private TextView sinkingTv;
    private TextView bubbleTv;

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void findViewById() {
        svgTv = (TextView) findViewById(R.id.svg_tv);
        valueAnimatorTv = (TextView) findViewById(R.id.value_animator_tv);
        rotationTv = (TextView) findViewById(R.id.rotation_tv);
        tranTv = (TextView) findViewById(R.id.translation_tv);
        scaleTv = (TextView) findViewById(R.id.scale_tv);
        alphaTv = (TextView) findViewById(R.id.alpha_tv);
        useTv = (TextView) findViewById(R.id.use_tv);
        regionTv = (TextView) findViewById(R.id.region_tv);
        elmTv = (TextView) findViewById(R.id.elm_tv);
        canvasTv = (TextView) findViewById(R.id.canvas_tv);
        paintTv = (TextView) findViewById(R.id.paint_tv);
        chargeTv = (TextView) findViewById(R.id.charge_tv);
        sinkingTv = (TextView) findViewById(R.id.sinking_tv);
        bubbleTv = (TextView) findViewById(R.id.bubble_tv);
    }

    @Override
    protected void setLister() {
        svgTv.setOnClickListener(this);
        valueAnimatorTv.setOnClickListener(this);
        rotationTv.setOnClickListener(this);
        tranTv.setOnClickListener(this);
        scaleTv.setOnClickListener(this);
        alphaTv.setOnClickListener(this);
        useTv.setOnClickListener(this);
        regionTv.setOnClickListener(this);
        elmTv.setOnClickListener(this);
        canvasTv.setOnClickListener(this);
        paintTv.setOnClickListener(this);
        chargeTv.setOnClickListener(this);
        sinkingTv.setOnClickListener(this);
        bubbleTv.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()){
            case R.id.svg_tv:
                //获取当前系统版本号
                int sysCode = android.os.Build.VERSION.SDK_INT;
                Log.v("hjz","当前系统版本号："+sysCode);
                if (sysCode > 20){
                    intent = new Intent(this,SvgActivity.class);
                }
                break;
            case R.id.value_animator_tv:
                intent = new Intent(this,ValueActivity.class);
                break;
            case R.id.rotation_tv:
                intent = new Intent(this,RotationActivity.class);
                break;
            case R.id.translation_tv:
                intent = new Intent(this,TranslationActivity.class);
                break;
            case R.id.scale_tv:
                intent = new Intent(this,ScaleActivity.class);
                break;
            case R.id.alpha_tv:
                intent = new Intent(this,AlphaActivity.class);
                break;
            case R.id.use_tv:
                intent = new Intent(this,IntegratedUseActivity.class);
                break;
            case R.id.region_tv:
                intent = new Intent(this,RegionActivity.class);
                break;
            case R.id.elm_tv:
                intent = new Intent(this,ElmActivity.class);
                break;
            case R.id.canvas_tv:
                intent = new Intent(this,CanvasActivity.class);
                break;
            case R.id.paint_tv:
                intent = new Intent(this,PaintActivity.class);
                break;
            case R.id.charge_tv:
                intent = new Intent(this,BatteryChargingProcessActivity.class);
                break;
            case R.id.sinking_tv:
                intent = new Intent(this,SinkingActivity.class);
                break;
            case R.id.bubble_tv:
                intent = new Intent(this,BubbleAnimationActivity.class);
                break;
        }
        if (intent != null){
            startActivity(intent);
        }
    }
}
