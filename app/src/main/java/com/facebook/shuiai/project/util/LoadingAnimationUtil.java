package com.facebook.shuiai.project.util;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.shuiai.project.R;


/**
 * @author shuiai@dianjia.io
 * @Company 杭州木瓜科技有限公司
 * @date 2017/3/7
 * 设置加载动画
 */

public class LoadingAnimationUtil {
    private static ImageView imageView;
    private static TextView txtInfo;
    private static Dialog loadingDialog;

    /**
     * 显示加载框
     *
     * @param context Context
     * @param flag    是否 需要点击外部取消
     */
    public static void showLoaingDialog(Context context, Boolean flag, String showInfo) {
        if (loadingDialog!=null&&loadingDialog.isShowing()){
            return;
        }
        View view = LayoutInflater.from(context).inflate(R.layout.loading, null);
        imageView = (ImageView) view.findViewById(R.id.img_loading);
        txtInfo = (TextView) view.findViewById(R.id.txt_info);
        if (!StringUtil.isNull(showInfo)){
            txtInfo.setText(showInfo);
        }else {
            txtInfo.setText("加载中...");
        }
        loadingDialog = new Dialog(context, R.style.loading_dialog);
        loadingDialog.setContentView(view);
        loadingDialog.setCanceledOnTouchOutside(flag);
        Window window = loadingDialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.alpha = 0.8f;
        window.setAttributes(lp);
        loadingDialog.show();
        Animation operatingAnim = AnimationUtils.loadAnimation(context, R.anim.loading);
        LinearInterpolator linearInterpolator = new LinearInterpolator();
        operatingAnim.setInterpolator(linearInterpolator);
        imageView.startAnimation(operatingAnim);
    }

    public static void hideLoadingDialoge() {
        if (loadingDialog != null) {
            imageView.clearAnimation();
            loadingDialog.dismiss();
            loadingDialog=null;
        }
    }

}
