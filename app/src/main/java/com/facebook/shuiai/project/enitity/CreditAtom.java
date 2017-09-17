package com.facebook.shuiai.project.enitity;

/**
 * @author shuiai@dianjia.io
 * @Company 杭州木瓜科技有限公司
 * @date 2017/8/26
 * 发现模块的信用卡
 */

public class CreditAtom {

   private String identifier;
    private String criditName;
    private String criditPicUrl;
    private String criditUrl;
    private String criditSpecial;

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getCriditName() {
        return criditName;
    }

    public void setCriditName(String criditName) {
        this.criditName = criditName;
    }

    public String getCriditPicUrl() {
        return criditPicUrl;
    }

    public void setCriditPicUrl(String criditPicUrl) {
        this.criditPicUrl = criditPicUrl;
    }

    public String getCriditUrl() {
        return criditUrl;
    }

    public void setCriditUrl(String criditUrl) {
        this.criditUrl = criditUrl;
    }

    public String getCriditSpecial() {
        return criditSpecial;
    }

    public void setCriditSpecial(String criditSpecial) {
        this.criditSpecial = criditSpecial;
    }
}
