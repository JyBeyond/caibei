package com.facebook.shuiai.project.util;

import java.util.List;

/**
 * @author shuiai@dianjia.io
 * @Company 杭州木瓜科技有限公司
 * @date 2017/8/26
 * 集合的宏基類
 */

public class CollectionUtil {
    /**
     * 判断集合是否为空
     *
     * @param list
     * @return
     */
    public static boolean isEmpity(List list) {
        if (list == null || list.size() == 0) {
            return true;
        }
        return false;
    }
}
