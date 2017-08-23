package com.facebook.shuiai.project.enitity;

/**
 * @author shuiai@dianjia.io
 * @Company 杭州木瓜科技有限公司
 * @date 2017/8/22
 * 首页数据的Atom
 */

public class HomeInfoAtom {
    private String createDate;
    private String phone;
    private String lendMoney;
    private String servicePersonTime;
    private String totalLendMoney;

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLendMoney() {
        return lendMoney;
    }

    public void setLendMoney(String lendMoney) {
        this.lendMoney = lendMoney;
    }

    public String getServicePersonTime() {
        return servicePersonTime;
    }

    public void setServicePersonTime(String servicePersonTime) {
        this.servicePersonTime = servicePersonTime;
    }

    public String getTotalLendMoney() {
        return totalLendMoney;
    }

    public void setTotalLendMoney(String totalLendMoney) {
        this.totalLendMoney = totalLendMoney;
    }
}
