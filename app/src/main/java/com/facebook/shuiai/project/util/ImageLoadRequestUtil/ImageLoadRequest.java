package com.facebook.shuiai.project.util.ImageLoadRequestUtil;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;

/**
 * @author shuiai@dianjia.io
 * @Company 杭州木瓜科技有限公司
 * @date 2017/8/25
 */

public class ImageLoadRequest {
    private ImageView view;
    private DrawableTypeRequest drawableTypeRequest;
    private DrawableRequestBuilder drawableRequestBuilder;

    public ImageLoadRequest(Context context, String url, ImageView view) {
        this.view = view;
        drawableTypeRequest = Glide.with(context).load(url);
    }

    /**
     * 设置playholder
     *
     * @param placeholder
     */
    public ImageLoadRequest setPlaceHolder(int placeholder) {
        drawableRequestBuilder = drawableTypeRequest.placeholder(placeholder);
        return this;
    }

    public ImageLoadRequest setErrorId(int errorId) {
        drawableRequestBuilder = drawableTypeRequest.error(errorId);
        return this;
    }


    /**
     * 跳过缓存
     *
     * @return
     */
    public ImageLoadRequest skipCache(boolean skip) {
        drawableRequestBuilder = drawableTypeRequest.skipMemoryCache(skip);
        return this;
    }

    /**
     * 设置ImageView
     */
    public void intoImageView() {
        drawableRequestBuilder.into(view);
    }
}
