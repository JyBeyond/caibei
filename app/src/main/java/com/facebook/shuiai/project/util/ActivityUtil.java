package com.facebook.shuiai.project.util;

import android.app.Activity;

import java.util.List;

/**
 * @author shuiai@dianjia.io
 * @Company 杭州木瓜科技有限公司
 * @date 2017/8/24
 * ActivityUtil
 */

public class ActivityUtil {
    /**
     * 退出app
     *
     * @param activitys
     */
    public static void exitApp(List<Activity> activitys) {
        finishAllActivity(activitys);
        System.exit(0);
    }

    /**
     * 关闭所有的activity
     */
    private static void finishAllActivity(List<Activity> activitys) {
        for (Activity activity : activitys) {
            activity.finish();
        }
    }
}
