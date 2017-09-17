package com.facebook.shuiai.project.task;

import android.content.Context;

import com.facebook.shuiai.project.util.ConstantUtil;
import com.facebook.shuiai.project.util.netUtil.IRequest;
import com.facebook.shuiai.project.util.netUtil.PostRequest;
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
    public static void getNewOrderInfoTask(Context mContext, int what, RequestListener listener) {
        IRequest.get(mContext, ConstantUtil.NEWORDERINFO, what)
                .execute(listener);
    }

    /**
     * 借贷产品列表
     *
     * @param mContext
     * @param pageNum
     * @param what
     * @param listener
     */
    public static void getLendPage(Context mContext, String type, int sortTYpe, int pageNum, int what, RequestListener listener) {
        PostRequest request = IRequest.post(mContext, ConstantUtil.LENDPAGE, what)
                .params("number", pageNum);
        switch (type) {
            case "1":
                request.params("throghRate", sortTYpe);
                break;
            case "2":
                request.params("throughputRate", sortTYpe);
                break;
            case "3":
                request.params("monthlyInterestRate ", sortTYpe);
                break;
        }
        request.execute(listener);
    }

    /**
     * 保险产品列表
     *
     * @param mContext
     * @param pageNum
     * @param what
     * @param listener
     */
    public static void getInsuranceList(Context mContext, int pageNum, int what, RequestListener listener) {
        IRequest.post(mContext, ConstantUtil.SAFEPAGE, what)
                .params("number", pageNum)
                .execute(listener);
    }

    /**
     * @param mContext
     * @param pageNum
     * @param what
     * @param listener
     */
    public static void getCreditList(Context mContext, int pageNum, int what, RequestListener listener) {
        IRequest.post(mContext, ConstantUtil.CREDITPAGE, what)
                .params("number", pageNum)
                .execute(listener);

    }

    /**
     * 获取贷款详情
     *
     * @param mContext
     * @param what
     * @param listener
     */
    public static void getLoansDetail(Context mContext, String id, int what, RequestListener listener) {
        IRequest.get(mContext, ConstantUtil.LENDDETAIL, what)
                .params("identifier", id)
                .execute(listener);
    }
}
