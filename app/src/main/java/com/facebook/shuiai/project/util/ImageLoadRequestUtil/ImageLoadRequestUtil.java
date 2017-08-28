package com.facebook.shuiai.project.util.ImageLoadRequestUtil;

import android.content.Context;
import android.widget.ImageView;

/**
 * @author shuiai@dianjia.io
 * @Company 杭州木瓜科技有限公司
 * @date 2017/8/25
 * 封装Picasso
 */

public abstract class ImageLoadRequestUtil {
    public static ImageLoadRequest load(Context context, String url, ImageView view) {
        return new ImageLoadRequest(context, url, view);
    }
}
