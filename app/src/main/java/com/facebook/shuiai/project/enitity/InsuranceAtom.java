package com.facebook.shuiai.project.enitity;

/**
 * @author shuiai@dianjia.io
 * @Company 杭州木瓜科技有限公司
 * @date 2017/8/26
 * 发现模块的保险列表
 */

public class InsuranceAtom {

    private String safeName;
    private String safePicUrl;
    private String safeUrl;
    private String safeSpecial;


    public String getSafeName() {
        return safeName;
    }

    public void setSafeName(String safeName) {
        this.safeName = safeName;
    }

    public String getSafePicUrl() {
        return safePicUrl;
    }

    public void setSafePicUrl(String safePicUrl) {
        this.safePicUrl = safePicUrl;
    }

    public String getSafeUrl() {
        return safeUrl;
    }

    public void setSafeUrl(String safeUrl) {
        this.safeUrl = safeUrl;
    }

    public String getSafeSpecial() {
        return safeSpecial;
    }

    public void setSafeSpecial(String safeSpecial) {
        this.safeSpecial = safeSpecial;
    }
}
