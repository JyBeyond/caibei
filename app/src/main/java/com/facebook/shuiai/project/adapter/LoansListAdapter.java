package com.facebook.shuiai.project.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.shuiai.project.R;
import com.facebook.shuiai.project.enitity.LoansAtom;
import com.facebook.shuiai.project.util.ConstantUtil;

import java.util.List;

/**
 * @author shuiai@dianjia.io
 * @Company 杭州木瓜科技有限公司
 * @date 2017/8/24
 */

public class LoansListAdapter extends BaseQuickAdapter<LoansAtom, BaseViewHolder> {
    private Context mContext;

    public LoansListAdapter(Context mContext, @Nullable List<LoansAtom> data) {
        super(R.layout.item_loans_list, data);
        this.mContext = mContext;
    }

    @Override
    protected void convert(BaseViewHolder helper, LoansAtom item) {
        helper.setText(R.id.txt_loans_title, item.getLendName())
                .setText(R.id.txt_loans_person, item.getTotalApply())
                .setText(R.id.txt_loans_desc, item.getLendSpecial())
                .setText(R.id.txt_loans_money, item.getLendMoney())
                .setText(R.id.txt_loans_limit, item.getPlatformNature());
        Glide.with(mContext).load(ConstantUtil.BASEPATH + item.getLendPicUrl())
                .placeholder(R.mipmap.placeholder)
                .centerCrop()
                .into((ImageView) helper.getView(R.id.img_loans));


    }
}
