package com.spriteapp.booklibrary.util;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {
    private static Context context = AppUtil.getAppContext();
    private static Toast mToast;

    public static void showSingleToast(int i) {
        getSingleToast(i, 0).show();
    }

    public static void showSingleToast(String str) {
        getSingleToast(str, 0).show();
    }

    public static void showSingleLongToast(int i) {
        getSingleToast(i, 1).show();
    }

    public static void showSingleLongToast(String str) {
        getSingleToast(str, 1).show();
    }

    public static void showToast(int i) {
        getToast(i, 0).show();
    }

    public static void showToast(String str) {
        getToast(str, 0).show();
    }

    public static void showLongToast(int i) {
        getToast(i, 1).show();
    }

    public static void showLongToast(String str) {
        getToast(str, 1).show();
    }

    public static Toast getSingleToast(int i, int i2) {
        return getSingleToast(context.getResources().getText(i).toString(), i2);
    }

    public static Toast getSingleToast(String str, int i) {
        if (mToast == null) {
            mToast = Toast.makeText(context, str, i);
        } else {
            mToast.setText(str);
        }
        return mToast;
    }

    public static Toast getToast(int i, int i2) {
        return getToast(context.getResources().getText(i).toString(), i2);
    }

    public static Toast getToast(String str, int i) {
        return Toast.makeText(context, str, i);
    }
}
