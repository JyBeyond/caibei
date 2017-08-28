package com.facebook.shuiai.project.util.netUtil;

import android.content.Context;

import com.facebook.shuiai.project.enitity.ResultDto;
import com.facebook.shuiai.project.util.GsonUtil;
import com.facebook.shuiai.project.util.LoadingAnimationUtil;
import com.facebook.shuiai.project.util.LogUtils;
import com.yanzhenjie.nohttp.OkHttpNetworkExecutor;
import com.yolanda.nohttp.FileBinary;
import com.yolanda.nohttp.Headers;
import com.yolanda.nohttp.Logger;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.OnUploadListener;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.download.DownloadListener;
import com.yolanda.nohttp.download.DownloadQueue;
import com.yolanda.nohttp.download.DownloadRequest;
import com.yolanda.nohttp.rest.OnResponseListener;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.RequestQueue;
import com.yolanda.nohttp.rest.Response;

import java.lang.reflect.Type;
import java.util.List;


/**
 * @author shuiai
 *         2016年12月8日14:47:57
 */
public class HttpRequestManager {

    private static RequestQueue mRequestQueue;
    private static DownloadQueue mDownloadQueue;

    /**
     * Nohttp的初始化默认
     *
     * @param context
     */
    public static void init(Context context) {
        NoHttp.initialize(context, new NoHttp.Config().setNetworkExecutor(new OkHttpNetworkExecutor()));
    }

    /**
     * 设置请求的log日志
     *
     * @param context
     * @param isShowing
     */
    public static void setDebug(Context context, boolean isShowing) {
        Logger.setDebug(isShowing); // 开启NoHttp调试模式。
        Logger.setTag("RESPONSEINFO"); // 设置NoHttp打印Log的TAG
    }


    /**
     * 数据请求的Queue,设置10个线程
     *
     * @return
     */
    public static RequestQueue getInstance() {
        if (mRequestQueue == null) {
            synchronized (HttpRequestManager.class) {
                mRequestQueue = NoHttp.newRequestQueue(10);
            }
        }

        return mRequestQueue;
    }

    /**
     * 文件下载的Queue，设置线程的最大个数是5
     *
     * @return
     */
    static DownloadQueue getInstance1() {
        if (mDownloadQueue == null) {
            synchronized (HttpRequestManager.class) {
                mDownloadQueue = NoHttp.newDownloadQueue(5);
            }
        }
        return mDownloadQueue;
    }

    private HttpRequestManager() {

    }

    /**
     * 统一添加头部
     *
     * @param request
     * @param <T>
     */
    private static <T> void getHeader(Request<T> request) {
//        request.addHeader("appinfo", getBaseHeaderInfo());
    }

    /**
     * 反回对象
     *
     * @param params 参数
     * @param l      回调
     * @param <T>    有问题暂时无法使用
     */
    public static <T> void loadArray(final BaseRequest params, Type classOfT, final RequestListener<T> l) {
        final Request<T> request = new ArrayRequest(params.context, params.url, params.requestMethod, classOfT);
        if (params.params != null && params.params.size() > 0) {
            request.add(params.params);
        }
        request.setConnectTimeout(params.timeOut);
        request.setRetryCount(params.retry);
        request.setTag(params.context);
        request.setCancelSign(params.context);
        /**
         * 如果有头部请求则调用getHeader
         * 把自己需要定义的参数都传过去即可
         */
        getHeader(request);
        getInstance().add(params.what, request, new OnResponseListener<T>() {
            @Override
            public void onStart(int what) {
                if (params.isLoading) {
                    LoadingAnimationUtil.showLoaingDialog(params.context, params.isdialogeCanCancle, params.dialogShowInfo);
                }
            }

            @Override
            public void onSucceed(int what, Response<T> response) {
                //判断返回的状态是否成功
                boolean status = responseArrayStatus(response);
                if (status) {
                    if (l != null) {
                        l.onTaskFinished(what, response);
                    }
                } else {
                    if (l != null) {
                        l.onTaskError(what, response);
                    }
                }
            }

            @Override
            public void onFailed(int what, Response<T> response) {
                if (l != null) {
                    l.onTaskError(what, response);
                }
            }

            @Override
            public void onFinish(int what) {
                if (params.isLoading) {
                    LoadingAnimationUtil.hideLoadingDialoge();
                }
                if (params.params == null) {
                    return;
                }
                params.params.clear();
            }
        });
    }

    /**
     * 返回对象
     * 判断返回的状态
     *
     * @param response
     * @param <T>
     * @return
     */
    private static <T> boolean responseArrayStatus(Response<T> response) {
        try {
            ResultDto resultDto = (ResultDto) response.get();
            String resultCode = resultDto.getResultCode();
            if ("0000".equals(resultCode)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 反回String
     */
    public static void loadString(final BaseRequest params, final RequestListener l) {
        final Request<String> request = NoHttp.createStringRequest(params.url, params.requestMethod);
        if (params.params != null && params.params.size() > 0) {
            request.add(params.params);
        }
        /**
         * 如果是https就打开
         */
//        SSLContext sslContext = SSLContextUtil.getSSLContext();
//        if (sslContext != null) {
//            request.setSSLSocketFactory(sslContext.getSocketFactory());
//        }
        /**
         * 如果有头部请求则调用getHeader
         * 把自己需要定义的参数都传过去即可
         */
        getHeader(request);
        request.setConnectTimeout(params.timeOut);
        request.setReadTimeout(params.timeOut);
        request.setRetryCount(params.retry);
        request.setTag(params.context);
        request.setCancelSign(params.context);
        getInstance().add(params.what, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {
                if (params.isLoading) {
                    LoadingAnimationUtil.showLoaingDialog(params.context, params.isdialogeCanCancle, params.dialogShowInfo);
                }
            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                if (response.get().equals("")) {
                    //返回结果为""不做处理
                    return;
                }
                LogUtils.d("RESPONSEINFO", params.url + ":" + response.get());
                //判断返回的状态是否成功
                boolean status = responseStringStatus(params.context, response);
                if (status) {
                    if (l != null) {
                        l.onTaskFinished(what, response);

                    }
                } else {
                    if (l != null) {
                        l.onTaskError(what, response);
                    }
                }
                params.params.clear();
            }

            @Override
            public void onFailed(int what, Response<String> response) {
                LogUtils.d("RESPONSEINFO", params.url + ":" + response.get());
                if (l != null) {
                    l.onTaskError(what, response);
                }
                params.params.clear();
            }

            @Override
            public void onFinish(int what) {
                if (params.isLoading) {
                    LoadingAnimationUtil.hideLoadingDialoge();
                }
                if (params.params == null) {
                    return;
                }
            }
        });
    }

    /**
     * 返回数据是String类型
     * f判断请求到数据之后返回的状态
     *
     * @param response
     * @return
     */
    private static boolean responseStringStatus(Context context, Response<String> response) {
        try {
            ResultDto resultDto = GsonUtil.getInstance().fromJson(response.get(), ResultDto.class);
            String resultCode = resultDto.getResultCode();
            if ("0000".equals(resultCode)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 下载文件
     *
     * @param context
     * @param url         下载地址
     * @param what        标记
     * @param fileFolder  保存的文件夹
     * @param filename    文件名
     * @param isRange     是否断点续传下载
     * @param isDeleteOld 如果发现存在同名文件，是否删除后重新下载，如果不删除，则直接下载成功
     * @param l           回调
     */

    public static void loadDownload(Context context, String url, int what, String fileFolder, String filename, boolean isRange, boolean isDeleteOld, final DownloadListener l) {
        // url 下载地址。
        // fileFolder 保存的文件夹。
        // fileName 文件名。
        // isRange 是否断点续传下载。
        // isDeleteOld 如果发现存在同名文件，是否删除后重新下载，如果不删除，则直接下载成功
        final DownloadRequest request = NoHttp.createDownloadRequest(url, fileFolder, filename, isRange, isDeleteOld);
        request.setTag(context);
        getInstance1().add(what, request, new DownloadListener() {

            @Override
            public void onDownloadError(int what, Exception exception) {
                if (l != null) {
                    l.onDownloadError(what, exception);
                }
            }

            @Override
            public void onStart(int what, boolean isResume, long rangeSize, Headers responseHeaders, long allCount) {
                if (l != null) {
                    l.onStart(what, isResume, rangeSize, responseHeaders, allCount);
                }
            }

            @Override
            public void onProgress(int what, int progress, long fileCount) {
                if (l != null) {
                    l.onProgress(what, progress, fileCount);
                }
            }

            @Override
            public void onFinish(int what, String filePath) {
                if (l != null) {
                    l.onFinish(what, filePath);
                }
            }

            @Override
            public void onCancel(int what) {
                if (l != null) {
                    l.onCancel(what);
                }
            }
        });
    }

    /**
     * 反回String
     *
     * @param params 参数
     * @param l
     */
    public static void loadUploadString(final BaseRequest params, final com.facebook.shuiai.project.util.netUtil.OnUploadListener l) {
        final Request<String> request = NoHttp.createStringRequest(params.url, RequestMethod.POST);
        request.add(params.params);
        /**
         * 给上传文件做个监听，可以不需要
         */
        List<UploadFile> files = params.uploadFiles;
        for (UploadFile file : files) {
            /**
             * 需要注意
             * FileBinary这里支持多种上传方式
             * 如：FileBinary
             *    BitmapBinary
             *    InputStreamBinary
             *    这里只处理一种 因为一个项目应该不会出现2种上传类型的方式
             *    如果自己的项目用的BitmapBinary 只需要把 UploadFile里面的类型修改就行了
             *
             * BasicBinary binary3 = new InputStreamBinary(new FileInputStream(file3), file3.getName());
             * BasicBinary binary2 = new BitmapBinary(file2, "userHead.jpg");// 或者：BasicBinary binary2 = new BitmapBinary(file2, null);
             *
             */
            FileBinary fileBinary = new FileBinary(file.getFile());
            request.add(file.getKey(), file.getFile());
            fileBinary.setUploadListener(file.getWhat(), new OnUploadListener() {
                @Override
                public void onStart(int what) {
                    if (l != null) {
                        l.onStart(what);
                    }
                }

                @Override
                public void onCancel(int what) {
                    if (l != null) {
                        l.onCancel(what);
                    }
                }

                @Override
                public void onProgress(int what, int progress) {
                    if (l != null) {
                        l.onProgress(what, progress);
                    }
                }

                @Override
                public void onFinish(int what) {
                    if (l != null) {
                        l.onFinish(what);
                    }
                }

                @Override
                public void onError(int what, Exception exception) {
                    if (l != null) {
                        l.onError(what, exception);
                    }
                }
            });
            /**
             * 这个key可以不传
             * 目前没发现有什么不一样的
             */
            request.add("", fileBinary);
        }
        /**
         * 如果有头部请求则调用getHeader
         * 把自己需要定义的参数都传过去即可
         */
        getHeader(request);
        request.setTag(params.context);
        getInstance().add(params.what, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {
                if (params.isLoading) {
                    LoadingAnimationUtil.showLoaingDialog(params.context, params.isdialogeCanCancle, params.dialogShowInfo);
                }
            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                if (l != null) {
                    l.onTaskFinished(what, response);
                }
            }

            @Override
            public void onFailed(int what, Response<String> response) {
                if (l != null) {
                    l.onTaskError(what, response);
                }
            }

            @Override
            public void onFinish(int what) {
                if (params.isLoading) {
                    LoadingAnimationUtil.hideLoadingDialoge();
                }
            }
        });
    }

    /**
     * 反回对象
     *
     * @param params 参数
     * @param l
     */
    public static <T> void loadUploaArray(final BaseRequest params, Class<T> tClass, final com.facebook.shuiai.project.util.netUtil.OnUploadListener<T> l) {
        final Request<T> request = new ArrayRequest(params.context, params.url, RequestMethod.POST, tClass);
        request.add(params.params);
        /**
         * 给上传文件做个监听，可以不需要
         */
        List<UploadFile> files = params.uploadFiles;
        for (UploadFile file : files) {
            /**
             * 需要注意
             * FileBinary这里支持多种上传方式
             * 如：FileBinary
             *    BitmapBinary
             *    InputStreamBinary
             *    这里只处理一种 因为一个项目应该不会出现2种上传类型的方式
             *    如果自己的项目用的BitmapBinary 只需要把 UploadFile里面的类型修改就行了
             *
             * BasicBinary binary3 = new InputStreamBinary(new FileInputStream(file3), file3.getName());
             * BasicBinary binary2 = new BitmapBinary(file2, "userHead.jpg");// 或者：BasicBinary binary2 = new BitmapBinary(file2, null);
             *
             */
            FileBinary fileBinary = new FileBinary(file.getFile());
            request.add(file.getKey(), file.getFile());
            fileBinary.setUploadListener(file.getWhat(), new OnUploadListener() {
                @Override
                public void onStart(int what) {
                    if (l != null) {
                        l.onStart(what);
                    }
                }

                @Override
                public void onCancel(int what) {
                    if (l != null) {
                        l.onCancel(what);
                    }
                }

                @Override
                public void onProgress(int what, int progress) {
                    if (l != null) {
                        l.onProgress(what, progress);
                    }
                }

                @Override
                public void onFinish(int what) {
                    if (l != null) {
                        l.onFinish(what);
                    }
                }

                @Override
                public void onError(int what, Exception exception) {
                    if (l != null) {
                        l.onError(what, exception);
                    }
                }
            });
            /**
             * 这个key可以不传
             * 目前没发现有什么不一样的
             */
            request.add("", fileBinary);
        }
        /**
         * 如果有头部请求则调用getHeader
         * 把自己需要定义的参数都传过去即可
         */
        getHeader(request);
        request.setTag(params.context);
        getInstance().add(params.what, request, new OnResponseListener<T>() {
            @Override
            public void onStart(int what) {
                if (params.isLoading) {
                    LoadingAnimationUtil.showLoaingDialog(params.context, params.isdialogeCanCancle, params.dialogShowInfo);
                }
            }

            @Override
            public void onSucceed(int what, Response<T> response) {
                if (l != null) {

                    l.onTaskFinished(what, response);
                }
            }

            @Override
            public void onFailed(int what, Response<T> response) {
                if (l != null) {
                    l.onTaskError(what, response);
                }
            }

            @Override
            public void onFinish(int what) {
                if (params.isLoading) {
                    LoadingAnimationUtil.hideLoadingDialoge();
                }

            }
        });
    }
}
