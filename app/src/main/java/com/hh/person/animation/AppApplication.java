package com.hh.person.animation;

import android.app.Application;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.hh.person.animation.common.Env;

import java.lang.reflect.Field;

/**
 * Created by hjz on 2017/2/27.
 */

public class AppApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initDisplayMetrics();
    }

    private void initDisplayMetrics() {
        WindowManager wm = (WindowManager)this.getApplicationContext().getSystemService("window");
        int rotation = wm.getDefaultDisplay().getRotation();
        DisplayMetrics metrics = this.getApplicationContext().getResources().getDisplayMetrics();
        Env.screenWidth = rotation == 0 ? metrics.widthPixels:metrics.heightPixels;
        Env.screenHeight = rotation == 0 ? metrics.heightPixels:metrics.heightPixels;
        Env.density = metrics.density;
        Env.statusBarHeight = getStatusBarHeight();
    }

    /***
     * 状态栏高度（像素）
     * @return
     */
    private int getStatusBarHeight() {
        Class c = null;
        Object obj = null;
        Field field = null;
        boolean x = false;
        int statusBarHeight = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            int x1 = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = this.getResources().getDimensionPixelSize(x1);
        } catch (Exception var7) {
            var7.printStackTrace();
        }
        return statusBarHeight;
    }
}
