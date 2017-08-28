package com.facebook.shuiai.project.enitity;

/**
 * @author shuiai@dianjia.io
 * @Company 杭州木瓜科技有限公司
 * @date 2017/8/24
 * 借贷产品列表
 */

public class LoansAtom {
    private String lendName;
    private String lendPicUrl;
    private String platformNature;
    private String hasActivity;
    private String totalApply;
    private String lendSpecial;
    private String monthlyInterestRate;
    private String lendMoney;

    public String getLendName() {
        return lendName;
    }

    public void setLendName(String lendName) {
        this.lendName = lendName;
    }

    public String getLendPicUrl() {
        return lendPicUrl;
    }

    public void setLendPicUrl(String lendPicUrl) {
        this.lendPicUrl = lendPicUrl;
    }

    public String getPlatformNature() {
        return platformNature;
    }

    public void setPlatformNature(String platformNature) {
        this.platformNature = platformNature;
    }

    public String getHasActivity() {
        return hasActivity;
    }

    public void setHasActivity(String hasActivity) {
        this.hasActivity = hasActivity;
    }

    public String getTotalApply() {
        return totalApply;
    }

    public void setTotalApply(String totalApply) {
        this.totalApply = totalApply;
    }

    public String getLendSpecial() {
        return lendSpecial;
    }

    public void setLendSpecial(String lendSpecial) {
        this.lendSpecial = lendSpecial;
    }

    public String getMonthlyInterestRate() {
        return monthlyInterestRate;
    }

    public void setMonthlyInterestRate(String monthlyInterestRate) {
        this.monthlyInterestRate = monthlyInterestRate;
    }

    public String getLendMoney() {
        return lendMoney;
    }

    public void setLendMoney(String lendMoney) {
        this.lendMoney = lendMoney;
    }
}
