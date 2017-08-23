package com.facebook.shuiai.project.task;

import android.content.Context;

import com.facebook.shuiai.project.util.ConstantUtil;
import com.facebook.shuiai.project.util.netUtil.IRequest;
import com.facebook.shuiai.project.util.netUtil.RequestListener;

/**
 * @author shuiai@dianjia.io
 * @Company 杭州木瓜科技有限公司
 * @date 2017/8/22
 * 接口请求层
 */

public class RequestTaskBiz {
    /**
     * 获取首页信息统计实时推送
     *
     * @param what
     * @param listener
     */
    public static void getNewOrderInfoTask(Context mContent, int what, RequestListener listener) {
        IRequest.get(mContent, ConstantUtil.NEWORDERINFO, what)
                .execute(listener);
    }
}
