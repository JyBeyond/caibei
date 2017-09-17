package com.facebook.shuiai.project.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.shuiai.project.R;
import com.facebook.shuiai.project.enitity.DiscoverAtom;
import com.facebook.shuiai.project.util.ConstantUtil;
import com.facebook.shuiai.project.util.ImageLoadRequestUtil.ImageLoadRequestUtil;

import java.util.List;

/**
 * @author shuiai@dianjia.io
 * @Company 杭州木瓜科技有限公司
 * @date 2017/8/26
 */

public class InsuranceAdapter extends BaseMultiItemQuickAdapter<DiscoverAtom, BaseViewHolder> {


    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public InsuranceAdapter(List<DiscoverAtom> data) {
        super(data);
        addItemType(DiscoverAtom.CREDITTYPE, R.layout.item_discover_cridit);
        addItemType(DiscoverAtom.INSURANCETYPE, R.layout.item_insurance_list);
        addItemType(DiscoverAtom.INSURANCETYPE_HEADER, R.layout.item_discover_list_header);
        addItemType(DiscoverAtom.CREDITTYPE_HEADER, R.layout.item_discover_list_header);
    }

    @Override
    protected void convert(BaseViewHolder helper, DiscoverAtom item) {

        switch (helper.getItemViewType()) {
            case DiscoverAtom.CREDITTYPE:
                helper.setText(R.id.txt_credit_title, item.getProductName());
                ImageLoadRequestUtil.load(mContext, ConstantUtil.BASEPATH + item.getProductPic(), (ImageView) helper.getView(R.id.img_credit))
                        .setPlaceHolder(R.mipmap.placeholder)
                        .setErrorId(R.mipmap.placeholder)
                        .intoImageView();
                break;
            case DiscoverAtom.INSURANCETYPE:
                helper.setText(R.id.txt_insurance_title, item.getProductName());
                ImageLoadRequestUtil.load(mContext, ConstantUtil.BASEPATH + item.getProductPic(), (ImageView) helper.getView(R.id.img_insurance))
                        .setPlaceHolder(R.mipmap.placeholder)
                        .setErrorId(R.mipmap.placeholder)
                        .intoImageView();
                break;
            case DiscoverAtom.INSURANCETYPE_HEADER:
                helper.setText(R.id.txt_discover_header, item.getProductName());
                break;
            case DiscoverAtom.CREDITTYPE_HEADER:
                helper.setText(R.id.txt_discover_header, item.getProductName());
                break;
        }
    }

}
