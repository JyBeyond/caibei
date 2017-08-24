package com.facebook.shuiai.project.activity;

import android.content.Intent;
import android.os.CountDownTimer;

import com.facebook.shuiai.project.R;
import com.facebook.shuiai.project.util.StatusBarUtil;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

/**
 * @author shuiai@dianjia.io
 * @Company 杭州木瓜科技有限公司
 * @date 2017/8/21
 */
@EActivity(R.layout.activity_splash)
public class SplashActivity extends BaseActivity {
    private CountDownTimer countDownTimer = new CountDownTimer(2000, 1000) {

        @Override
        public void onTick(long millisUntilFinished) {
        }

        @Override
        public void onFinish() {
            actionActivity();
        }
    };

    @AfterViews
    public void initViews() {
        StatusBarUtil.setStatusBar(this);
        countDownTimer.start();
    }

    private void actionActivity() {
        Intent intent = new Intent(this, GuideActivity_.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}
