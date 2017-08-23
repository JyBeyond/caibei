package com.facebook.shuiai.project.util.netUtil;


import android.content.Context;

import com.yolanda.nohttp.RequestMethod;


/**
 * @author shuiai
 * 2016年12月9日14:29:10
 */
public class GetRequest extends BaseRequest<GetRequest> {


    public GetRequest(Context context, String url, int what) {
        this.url = url;
        this.context = context;
        this.what=what;
    }

    public <T> void execute(Class<T> classOfT, RequestListener<T> l) {
        requestMethod(RequestMethod.GET);
        HttpRequestManager.loadArray(this, classOfT, l);
    }

    public void execute(RequestListener l) {
        requestMethod(RequestMethod.GET);
        HttpRequestManager.loadString(this, l);
    }
}
