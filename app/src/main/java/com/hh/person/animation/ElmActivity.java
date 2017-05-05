package com.hh.person.animation;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ListView;
import android.widget.TextView;

import com.hh.person.animation.adapter.ElmAdapter;
import com.hh.person.animation.common.Env;
import com.hh.person.animation.custom.AnimShopButton;
import com.hh.person.animation.custom.BeZiTwoNewView;
import com.hh.person.animation.inter.IOnAddDelListener;

import java.util.ArrayList;
import java.util.List;

public class ElmActivity extends Activity implements IOnAddDelListener,BeZiTwoNewView.AnimtorEndListener{
    private ListView listView;
    private ElmAdapter adapter;
    private TextView textView;
    private BeZiTwoNewView beZiTwoNewView;
    private List<String> list = new ArrayList<>();
    private float endRawX,endRawY;
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        /*setContentView(R.layout.activity_elm);
        final AnimShopButton animShopButton = (AnimShopButton) findViewById(R.id.btnReplenish);
        animShopButton.setReplenish(true);*/
        setLayout();
        findViewById();
        setLister();
        initData();
    }

    private void setLayout() {
        setContentView(R.layout.activity_elm_new);
    }

    private void findViewById() {
        listView = (ListView) findViewById(R.id.elm_lv);
        textView = (TextView) findViewById(R.id.textView);
        beZiTwoNewView = (BeZiTwoNewView) findViewById(R.id.elm_be_zi);
    }

    private void setLister() {
        beZiTwoNewView.setAnimtorEndListener(this);
    }

    private void initData() {
        for (int i=0; i<30; i++){
            list.add("content"+i);
        }
        adapter = new ElmAdapter(this,list);
        adapter.setListener(this);
        listView.setAdapter(adapter);
        endRawX = 20;
        endRawY = Env.screenHeight - Env.statusBarHeight - 20;

    }


    @Override
    public void onAddSuccess(int count,float rawX, float rawY) {
        Log.v("hjz","endRawX ="+ endRawX + "   endRawY=" + endRawY);
        this.count = count;
        beZiTwoNewView.setVisibility(View.VISIBLE);
        beZiTwoNewView.start(rawX,rawY,endRawX,endRawY);
    }

    @Override
    public void onAddFailed(int count, FailType failType) {

    }

    @Override
    public void onDelSuccess(int count) {
        textView.setText(count+"");
    }

    @Override
    public void onDelFaild(int count, FailType failType) {

    }

    @Override
    public void succuessEnd() {
        beZiTwoNewView.setVisibility(View.GONE);
        textView.setText(count + "");
    }
}
