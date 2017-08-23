package com.facebook.shuiai.project.util.netUtil;


/**
 * @author shuiai
 *  2016年12月8日14:48:34
 */
public interface OnUploadListener<T> extends RequestListener<T> {
    /**
     * 上传开始可以做一些操作
     * @param what
     */
    void onStart(int what);

    /**
     * 取消
     * @param what
     */
    void onCancel(int what);

    /**
     * 显示进度
     * @param what
     * @param progress
     */
    void onProgress(int what, int progress);

    /**
     * 完成
     * @param what
     */
    void onFinish(int what);

    /**
     * 请求错误
     * @param what
     * @param exception
     */
    void onError(int what, Exception exception);
}
