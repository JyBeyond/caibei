package com.facebook.shuiai.project.util.netUtil;


import com.yolanda.nohttp.rest.Response;


public  interface RequestListener<T> {

    /**
     * 成功
     */
     void onTaskFinished(int what, Response<T> result);

    /**
     * 错误
     */
    void onTaskError(int what, Response<T> result);

}
