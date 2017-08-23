package com.facebook.shuiai.project.util.netUtil;

import android.content.Context;

import java.util.Map;


/**
 * @author shuiai
 * 2016年12月8日14:52:18
 */
public class UploadRequest extends BaseRequest<UploadRequest> {
    public <T> UploadRequest(Context context, String url, Map<String,Object> params, int what) {
        this.url = url;
        this.context = context;
        this.params = params;
        this.what=what;
    }

    public <T> void execute(Class<T> classOfT, OnUploadListener<T> l) {
        HttpRequestManager.loadUploaArray(this, classOfT, l);
    }

    public void execute(OnUploadListener l) {
        HttpRequestManager.loadUploadString(this, l);
    }
}