package com.facebook.shuiai.project.fragment;

import android.view.View;
import android.widget.TextView;

/**
 * @author shuiai@dianjia.io
 * @Company 杭州木瓜科技有限公司
 * @date 2017/8/21
 */

public class LoansFragment extends BaseFragment {
    @Override
    public View initView() {
        TextView textView = new TextView(mContext);
        textView.setText("貸款");
        return textView;
    }
}
