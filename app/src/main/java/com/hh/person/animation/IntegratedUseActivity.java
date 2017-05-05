package com.hh.person.animation;

import android.view.View;
import android.widget.Button;
import com.hh.person.animation.base.BaseActivity;
import com.hh.person.animation.custom.BeZiTwoView;
import com.hh.person.animation.custom.LineSportAnimView;

/**
 * 动画综合应用
 */
public class IntegratedUseActivity extends BaseActivity implements View.OnClickListener {
    private Button btn1;
    private LineSportAnimView line_sport_view;
    private BeZiTwoView be_zi_view;

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_integrated_use);
    }

    @Override
    protected void findViewById() {
        btn1 = (Button) findViewById(R.id.btn1);
        line_sport_view = (LineSportAnimView) findViewById(R.id.line_sport_view);
        be_zi_view = (BeZiTwoView) findViewById(R.id.be_zi_view);
    }

    @Override
    protected void setLister() {
        btn1.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn1:
                lineSport();
                beZiTwoSport();
                break;
        }
    }

    private void beZiTwoSport() {
        be_zi_view.start();
    }

    private void lineSport() {
        line_sport_view.start();
    }
}
