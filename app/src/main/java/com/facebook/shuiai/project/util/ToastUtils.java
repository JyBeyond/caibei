package com.facebook.shuiai.project.util;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * 
 */
public class ToastUtils {

    private static Toast toast;

    //静态toast
    public static void showToast(Context context, String text) {
        if (toast == null)
            toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        toast.setText(text);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();

    }

    //静态toast
    public static void showLongToast(Context context, String text) {
        if (toast == null)
            toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
        toast.setText(text);
        toast.show();
    }

}
