package com.facebook.shuiai.project.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.shuiai.project.R;
import com.facebook.shuiai.project.enitity.InsuranceAtom;
import com.facebook.shuiai.project.util.ConstantUtil;
import com.facebook.shuiai.project.util.ImageLoadRequestUtil.ImageLoadRequestUtil;

import java.util.List;

/**
 * @author shuiai@dianjia.io
 * @Company 杭州木瓜科技有限公司
 * @date 2017/8/26
 */

public class InsuranceAdapter extends BaseQuickAdapter<InsuranceAtom, BaseViewHolder> {
    private Context mContext;

    public InsuranceAdapter(Context mContext, @Nullable List<InsuranceAtom> data) {
        super(R.layout.item_insurance_list, data);
        this.mContext = mContext;
    }

    @Override
    protected void convert(BaseViewHolder helper, InsuranceAtom item) {
        helper.setText(R.id.txt_insurance_title, item.getSafeName());
        ImageLoadRequestUtil.load(mContext, ConstantUtil.BASEPATH + item.getSafePicUrl(), (ImageView) helper.getView(R.id.img_insurance))
                .setPlaceHolder(R.mipmap.placeholder)
                .setErrorId(R.mipmap.placeholder)
                .intoImageView();
    }
}
