package com.ak.android.other.news;

import android.content.Context;
import com.ak.android.bridge.IBridge;
import com.ak.android.bridge.c;

public class DownloadUtil {
    private static final int TYPE_ACTION_CD = 23;
    private static final int TYPE_ACTION_PD = 22;
    private static final int TYPE_ACTION_SD = 21;
    private static final int TYPE_CALLBACK_AA = 6;
    private static final int TYPE_CALLBACK_AI = 5;
    private static final int TYPE_CALLBACK_DC = 3;
    private static final int TYPE_CALLBACK_DE = 4;
    private static final int TYPE_CALLBACK_DP = 2;
    private static final int TYPE_CALLBACK_DS = 1;
    private static IBridge bridge;

    public static void onDownloadStarted(Context context, int i, String str) {
        action(context, Integer.valueOf(1), Integer.valueOf(i), str);
    }

    public static void onDownloadProgress(Context context, int i, String str, int i2) {
        action(context, Integer.valueOf(2), Integer.valueOf(i), str, Integer.valueOf(i2));
    }

    public static void onDownloadCompleted(Context context, int i, String str) {
        action(context, Integer.valueOf(3), Integer.valueOf(i), str);
    }

    public static void onDownloadError(Context context, int i, String str, int i2, String str2) {
        action(context, Integer.valueOf(4), Integer.valueOf(i), str, Integer.valueOf(i2), str2);
    }

    public static void onApkInstalled(Context context, int i, String str) {
        action(context, Integer.valueOf(5), Integer.valueOf(i), str);
    }

    public static void onApkActived(Context context, int i, String str) {
        action(context, Integer.valueOf(6), Integer.valueOf(i), str);
    }

    public static void startDownload(Context context, int i, String str) {
        action(context, Integer.valueOf(21), Integer.valueOf(i), str);
    }

    public static void pauseDownload(Context context, int i, String str) {
        action(context, Integer.valueOf(22), Integer.valueOf(i), str);
    }

    public static void cancelDownload(Context context, int i, String str) {
        action(context, Integer.valueOf(23), Integer.valueOf(i), str);
    }

    private static void action(Context context, Object... objArr) {
        if (bridge == null) {
            bridge = c.a(context);
        }
        if (bridge != null) {
            bridge.initSdk(context);
            bridge.downloadRelevant(objArr);
        }
    }
}
