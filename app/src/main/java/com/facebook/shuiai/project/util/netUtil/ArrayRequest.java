package com.facebook.shuiai.project.util.netUtil;

import android.content.Context;

import com.facebook.shuiai.project.util.GsonUtil;
import com.yolanda.nohttp.Headers;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.RestRequest;
import com.yolanda.nohttp.rest.StringRequest;

import java.lang.reflect.Type;

/**
 * @author shuiai
 * 2016年12月8日14:31:03
 * @param <T>
 */
public class ArrayRequest<T> extends RestRequest<T> {
    private Type classOfT;
    private Context context;

    public ArrayRequest(String url) {
        super(url);
    }

    public ArrayRequest(Context context, String url, RequestMethod requestMethod, Type classOfT) {
        super(url, requestMethod);
        this.classOfT = classOfT;
        this.context = context;
    }


    @Override
    public T parseResponse(Headers responseHeaders, byte[] responseBody) throws Throwable {
        String result = StringRequest.parseResponseString(responseHeaders, responseBody);
        return GsonUtil.getInstance().fromJson(result, classOfT);
    }

    @Override
    public void onPreExecute() {
        super.onPreExecute();
    }
}
