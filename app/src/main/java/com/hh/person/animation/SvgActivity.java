package com.hh.person.animation;

import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Build;
import android.widget.ImageView;

import com.hh.person.animation.base.BaseActivity;

public class SvgActivity extends BaseActivity {
    private ImageView imageView1;
    private ImageView imageView2;

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_svg);
    }

    @Override
    protected void findViewById() {
        imageView1 = (ImageView) findViewById(R.id.iv1);
        imageView2 = (ImageView) findViewById(R.id.iv2);
    }

    @Override
    protected void setLister() {

    }

    @Override
    protected void initData() {
        //得到对应的AnimatedVectorDrawable对象
        AnimatedVectorDrawable mAnimatedVectorDrawable =  (AnimatedVectorDrawable) getResources().getDrawable(R.drawable.arrow_anim);
        AnimatedVectorDrawable mAnimatedVectorDrawable2 =  (AnimatedVectorDrawable) getResources().getDrawable(R.drawable.heart_vector_animator);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            imageView1.setImageDrawable(mAnimatedVectorDrawable);
            if(mAnimatedVectorDrawable!=null){
                mAnimatedVectorDrawable.start();
            }
            imageView2.setImageDrawable(mAnimatedVectorDrawable2);
            if(mAnimatedVectorDrawable2!=null){
                mAnimatedVectorDrawable2.start();
            }
        }
    }
}
