package com.facebook.shuiai.project.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.shuiai.project.R;
import com.facebook.shuiai.project.enitity.LoanNumber;

import java.util.List;

/**
 * @author shuiai@dianjia.io
 * @Company 杭州木瓜科技有限公司
 * @date 2017/8/22
 */

public class LoanNumberAdapter extends BaseQuickAdapter<LoanNumber,BaseViewHolder> {


    public LoanNumberAdapter(Context mContent, List<LoanNumber> data) {
        super(R.layout.item_loan_number, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, LoanNumber item) {
        helper.setText(R.id.txt_number, item.getLoanNumber()+"");
    }
}
