package com.facebook.shuiai.project.util;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;

import com.facebook.shuiai.project.R;

import java.util.Calendar;


/**
 * Built in Eclipse
 * Created by ZZB on 2013/10/26.
 * Email: id362202@163.com
 */

/**
 * Dialog的工具类
 */
public class DialogUtil {

    private static AlertDialog.Builder builder;
    private static AlertDialog dialog;
    private static Dialog centerDialog;

    private DialogUtil() {
    }

    private static ProgressDialog progressDialog;

    private static ProgressDialog unCancelProgressDialog;

    private static DatePickerDialog datePickerDialog;


    public static void showProgressDialog(Context context) {
        if (context != null) {
            if (progressDialog == null) {
                progressDialog = new ProgressDialog(context);
                progressDialog.setMessage("正在加载");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.show();
            }
        }
    }

    public static void hideUnCancelProgressDialog() {
        if (unCancelProgressDialog != null) {
            unCancelProgressDialog.dismiss();
            unCancelProgressDialog = null;
        }
    }

    public static void hideProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }


    /**
     * 创建一个自定义的对话框
     *
     * @param context         Context
     * @param view            View
     * @param onClickListener DialogInterface.OnClickListener PositiveButton的点击事件
     * @param positiveString  PositiveButton按钮的text
     */
    public static void showAlertDialog(Context context, View view, DialogInterface.OnClickListener onClickListener, String positiveString) {
        builder = new AlertDialog.Builder(context);
        builder.setView(view);
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton(positiveString, onClickListener);
        builder.show();
    }

    /**
     * 显示系统自带的对话框
     *
     * @param context         Context
     * @param positiveStr     PositiveButton按钮的text
     * @param onClickListener DialogInterface.OnClickListener PositiveButton的点击事件
     */
    public static void showAlertDialog(Context context, String positiveStr, String message, DialogInterface.OnClickListener onClickListener) {
        builder = new AlertDialog.Builder(context);
        builder.setMessage(message);
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton(positiveStr, onClickListener);
        builder.show();
    }
    /**
     * 显示位于屏幕中央的对话框
     *
     * @param context Context
     * @param view    View要填充的内容
     */
    public static void showCenterDefineDialog(Context context, View view, Boolean flag) {
        if (centerDialog != null && centerDialog.isShowing()) {
            return;
        }
        centerDialog = new Dialog(context, R.style.dialog);
        centerDialog.setContentView(view);
        centerDialog.setCanceledOnTouchOutside(flag);
        centerDialog.setCancelable(flag);
        centerDialog.show();
//        Window window = centerDialog.getWindow();
//        // 解决AlertDialog不能弹出输入法的问题
//        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
//        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

    public static void hideCenterDefineDialog() {
        if (centerDialog != null) {
            centerDialog.dismiss();
        }
    }

    /**
     * 显示系统自带的日期选择器
     *
     * @param context  Context
     * @param callBack OnDateSetListener 设置日期时的事件监听
     */
    public static void showDatePickDialog(Context context, OnDateSetListener callBack) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int monthOfYear = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        datePickerDialog = new DatePickerDialog(context, callBack, year, monthOfYear, dayOfMonth);
        datePickerDialog.show();
    }

    public static void hideDatePickerDialog() {
        if (datePickerDialog != null) {
            datePickerDialog.dismiss();
        }
    }

    public static void hideDefineDialog() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

}