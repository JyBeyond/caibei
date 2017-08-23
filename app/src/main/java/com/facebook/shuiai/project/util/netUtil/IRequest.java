package com.facebook.shuiai.project.util.netUtil;


import android.content.Context;

import java.util.Map;


/**
 * @author shuiai
 * 2016年12月8日15:34:04
 */
public class IRequest {
    /**
     * post请求
     */
    public static <T> PostRequest post(Context context, String url, int what) {
        return new <T>PostRequest(context, url, what);
    }

    /**
     * get请求
     */
    public static GetRequest get(Context context, String url, int what) {
        return new GetRequest(context, url, what);
    }

    /**
     * 下载请求
     */
    public static DownloadRequest download(Context context, String url, int what) {
        return new DownloadRequest(context, url, what);
    }

    /**
     * 上传请求
     */
    public static <T> UploadRequest upload(Context context, String url, Map<String, Object> params, int what) {
        return new <T>UploadRequest(context, url, params, what);
    }
}
