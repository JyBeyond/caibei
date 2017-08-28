package com.facebook.shuiai.project.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.shuiai.project.R;
import com.facebook.shuiai.project.enitity.CreditAtom;
import com.facebook.shuiai.project.util.ConstantUtil;
import com.facebook.shuiai.project.util.ImageLoadRequestUtil.ImageLoadRequestUtil;

import java.util.List;

/**
 * @author shuiai@dianjia.io
 * @Company 杭州木瓜科技有限公司
 * @date 2017/8/26
 */

public class CreditAdapter extends BaseQuickAdapter<CreditAtom, BaseViewHolder> {
    private Context mContext;

    public CreditAdapter(Context mContext, @Nullable List<CreditAtom> data) {
        super(R.layout.item_discover_cridit, data);
        this.mContext = mContext;
    }

    @Override
    protected void convert(BaseViewHolder helper, CreditAtom item) {
        helper.setText(R.id.txt_credit_title, item.getCriditName());
        ImageLoadRequestUtil.load(mContext, ConstantUtil.BASEPATH + item.getCriditPicUrl(), (ImageView) helper.getView(R.id.img_credit))
                .setPlaceHolder(R.mipmap.placeholder)
                .setErrorId(R.mipmap.placeholder)
                .intoImageView();
    }
}
