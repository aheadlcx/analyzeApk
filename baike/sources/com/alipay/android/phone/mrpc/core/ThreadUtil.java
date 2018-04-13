package com.alipay.android.phone.mrpc.core;

import android.os.Looper;

public class ThreadUtil {
    public static boolean checkMainThread() {
        return Looper.myLooper() != null && Looper.myLooper() == Looper.getMainLooper();
    }
}
