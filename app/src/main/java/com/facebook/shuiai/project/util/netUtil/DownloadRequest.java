package com.facebook.shuiai.project.util.netUtil;

import android.content.Context;

import com.yolanda.nohttp.download.DownloadListener;


/**
 * @author shuiai
 * 2016年12月8日14:47:22
 */
public class DownloadRequest extends BaseRequest<DownloadRequest> {
    public DownloadRequest(Context context, String url, int what) {
        this.url = url;
        this.context = context;
        this.what = what;
    }

    public void execute(DownloadListener l) {

        HttpRequestManager.loadDownload(context, url, what, fileFolder, fileName, isRange, isDeleteOld, l);
    }
}
