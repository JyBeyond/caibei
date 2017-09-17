package com.facebook.shuiai.project.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.facebook.shuiai.project.R;
import com.facebook.shuiai.project.application.MyApplication;
import com.facebook.shuiai.project.util.netUtil.RequestListener;
import com.yolanda.nohttp.rest.Response;

/**
 * @author shuiai@dianjia.io
 * @Company 杭州木瓜科技有限公司
 * @date 2017/8/21
 */

public abstract class BaseActivity extends FragmentActivity implements RequestListener {

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        View backView = findViewById(R.id.img_return);
        if (backView != null) {
            backView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.activitys.add(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyApplication.activitys.remove(this);
    }

    @Override
    public void onTaskFinished(int what, Response result) {

    }

    @Override
    public void onTaskError(int what, Response result) {

    }
}
