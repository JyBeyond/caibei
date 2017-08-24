package com.facebook.shuiai.project.activity;

import android.content.Intent;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.shuiai.project.R;
import com.facebook.shuiai.project.util.StatusBarUtil;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * @author shuiai@dianjia.io
 * @Company 杭州木瓜科技有限公司
 * @date 2017/8/24
 */
@EActivity(R.layout.activity_guide)
public class GuideActivity extends BaseActivity implements View.OnClickListener {
    @ViewById(R.id.turn_activity_layout)
    LinearLayout turnActivityLayout;
    @ViewById(R.id.txt_down_time)
    TextView txtDownTime;
    private CountDownTimer countDownTimer = new CountDownTimer(4000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            txtDownTime.setText(millisUntilFinished / 1000 + "秒");
        }

        @Override
        public void onFinish() {
            actionActivity();
        }
    };

    @AfterViews
    public void initViews() {
        StatusBarUtil.setStatusBar(this);
        turnActivityLayout.setOnClickListener(this);
        startDownTime();
    }

    private void actionActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void startDownTime() {
        countDownTimer.start();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.turn_activity_layout:
                actionActivity();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}
