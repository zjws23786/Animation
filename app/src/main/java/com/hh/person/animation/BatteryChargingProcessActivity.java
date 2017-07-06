package com.hh.person.animation;

import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import com.hh.person.animation.base.BaseActivity;

import java.util.Timer;
import java.util.TimerTask;

//电池充电
public class BatteryChargingProcessActivity extends BaseActivity {
    private ImageView chargeIv;
    //电池动画控制器
    private Timer mTimer=null;
    //电池动画控制任务
    private TimerTask mTimerTask=null;
    //记录电池动画图片
    private int index=0;
    //电池充电状态
    private boolean running = true;
    private ChargeAnimationHandler chargeAnimationHandler =null;

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_battery_charging_process);
    }

    @Override
    protected void findViewById() {
        chargeIv = (ImageView) findViewById(R.id.charge_state_iv);
    }

    @Override
    protected void setLister() {

    }

    @Override
    protected void initData() {
        playChargeAnimation(chargeIv);
    }

    /**
     * 充电图标动画
     */
    private void playChargeAnimation(final ImageView imageView) {
        //定时器检查充电状态
        mTimer = new Timer();

        chargeAnimationHandler = new ChargeAnimationHandler(imageView);
        mTimerTask = new TimerTask() {
            @Override
            public void run() {
                if (running){
                    index = (index+1)%11;
                    Message msg = chargeAnimationHandler.obtainMessage();
                    msg.what = index;
                    chargeAnimationHandler.sendMessage(msg);
                }else{
//                    Message msg = chargeAnimationHandler.obtainMessage();
//                    msg.what=11;
//                    chargeAnimationHandler.sendMessage(msg);
                    stopTimer();
                }
            }
        };
        //调用频率为500毫秒一次
        mTimer.schedule(mTimerTask, 0, 500);
    }

    /**
     * 停止
     */
    private void stopTimer(){
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }

        if (mTimerTask != null) {
            mTimerTask.cancel();
            mTimerTask = null;
        }

    }

    static class ChargeAnimationHandler extends Handler{
        ImageView imageView;

        public ChargeAnimationHandler(ImageView imageView)
        {
            this.imageView = imageView;
        }
        @Override
        public void handleMessage(Message msg) {
//            Log.v("hjz","index="+msg.what);
            //根据msg.what来替换图片，达到动画效果
            switch (msg.what) {
                case 0 :
                    imageView.setImageResource(R.mipmap.charge_0);
                    break;
                case 1 :
                    imageView.setImageResource(R.mipmap.charge_1);
                    break;
                case 2 :
                    imageView.setImageResource(R.mipmap.charge_2);
                    break;
                case 3 :
                    imageView.setImageResource(R.mipmap.charge_3);
                    break;
                case 4 :
                    imageView.setImageResource(R.mipmap.charge_4);
                    break;
                case 5 :
                    imageView.setImageResource(R.mipmap.charge_5);
                    break;
                case 6 :
                    imageView.setImageResource(R.mipmap.charge_6);
                    break;
                case 7 :
                    imageView.setImageResource(R.mipmap.charge_7);
                    break;
                case 8 :
                    imageView.setImageResource(R.mipmap.charge_8);
                    break;
                case 9 :
                    imageView.setImageResource(R.mipmap.charge_9);
                    break;
                case 10 :
                    imageView.setImageResource(R.mipmap.charge_10);
                    break;
            }
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        running = false;
        if (chargeAnimationHandler != null){
            chargeAnimationHandler.removeCallbacksAndMessages(null);
        }
    }
}


