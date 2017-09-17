package com.facebook.shuiai.project.enitity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * @author shuiai@dianjia.io
 * @Company 杭州木瓜科技有限公司
 * @date 2017/9/9
 * 发现模块数据
 */

public class DiscoverAtom implements MultiItemEntity {
    public static final int INSURANCETYPE = 0;
    public static final int CREDITTYPE = 1;
    public static final int INSURANCETYPE_HEADER = 2;
    public static final int CREDITTYPE_HEADER = 3;
    public static final int CREDIT_SPAN_SIZE = 1;
    public static final int INSURANCE_SPAN_SIZE = 2;
    private int itemType;
    private int spanSize;
    private String identifier;
    private String productName;
    private String productPic;
    private String productUrl;
    private String productSpecial;
    private Integer dataTYpe;//1：信用卡0：保险

    public DiscoverAtom(int itemType, int spanSize) {
        this.itemType = itemType;
        this.spanSize = spanSize;
    }


    public int getSpanSize() {
        return spanSize;
    }

    public void setSpanSize(int spanSize) {
        this.spanSize = spanSize;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPic() {
        return productPic;
    }

    public void setProductPic(String productPic) {
        this.productPic = productPic;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    public String getProductSpecial() {
        return productSpecial;
    }

    public void setProductSpecial(String productSpecial) {
        this.productSpecial = productSpecial;
    }

    public Integer getDataTYpe() {
        return dataTYpe;
    }

    public void setDataTYpe(Integer dataTYpe) {
        this.dataTYpe = dataTYpe;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
