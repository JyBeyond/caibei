package com.facebook.shuiai.project.fragment;

import android.view.LayoutInflater;
import android.view.View;

import com.facebook.shuiai.project.R;

/**
 * @author shuiai@dianjia.io
 * @Company 杭州木瓜科技有限公司
 * @date 2017/8/21
 */

public class LoansFragment extends BaseFragment {
    @Override
    public View initView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_loans, null);
        return view;
    }
}
