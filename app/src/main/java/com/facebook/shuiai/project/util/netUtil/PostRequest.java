package com.facebook.shuiai.project.util.netUtil;

import android.content.Context;

import com.facebook.shuiai.project.enitity.ResultDto;
import com.google.gson.reflect.TypeToken;
import com.yolanda.nohttp.RequestMethod;

/**
 * @author shuiai
 *         2016年12月8日14:49:01
 */
public class PostRequest extends BaseRequest<PostRequest> {


    public <T> PostRequest(Context context, String url, int what) {
        this.url = url;
        this.context = context;
        this.what = what;
    }

    public <T> void execute(Class<T> classOfT, RequestListener<T> l) {
        requestMethod(RequestMethod.POST);
        HttpRequestManager.loadArray(this, new TypeToken<ResultDto<T,Object>>() {
        }.getType(), l);
    }

    public void execute(RequestListener l) {
        requestMethod(RequestMethod.POST);
        HttpRequestManager.loadString(this, l);
    }
}
