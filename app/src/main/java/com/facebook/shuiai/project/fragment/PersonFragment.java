package com.facebook.shuiai.project.fragment;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.facebook.shuiai.project.R;

/**
 * @author shuiai@dianjia.io
 * @Company 杭州木瓜科技有限公司
 * @date 2017/8/21
 */

public class PersonFragment extends BaseFragment implements View.OnClickListener {
    private Button txtGetCode;
    private CountDownTimer countDownTimer = new CountDownTimer(60000, 1000) {
        @Override
        public void onTick(long l) {
            txtGetCode.setText(l / 1000 + "秒");
        }

        @Override
        public void onFinish() {
            txtGetCode.setText("获取验证码");
            txtGetCode.setEnabled(true);
        }
    };

    @Override
    public View initView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_person, null);
        txtGetCode = (Button) view.findViewById(R.id.txt_get_code);
        txtGetCode.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txt_get_code:
                downTime();
                break;
        }
    }

    private void downTime() {
        txtGetCode.setEnabled(false);
        countDownTimer.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}
