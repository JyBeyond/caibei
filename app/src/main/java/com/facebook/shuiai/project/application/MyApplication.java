package com.facebook.shuiai.project.application;

import android.app.Application;

import com.facebook.shuiai.project.util.netUtil.HttpRequestManager;

/**
 * @author shuiai@dianjia.io
 * @Company 杭州木瓜科技有限公司
 * @date 2017/8/21
 */

public class MyApplication extends Application {
    /**
     * 是否开启日志 true:open---- false:close
     */
    public static boolean isDebug = true;

    @Override
    public void onCreate() {
        super.onCreate();
        initHttpRequest();
    }

    private void initHttpRequest() {
        HttpRequestManager.init(getApplicationContext());
        HttpRequestManager.setDebug(getApplicationContext(), isDebug);
    }
}
