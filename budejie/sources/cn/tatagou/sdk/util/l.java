package cn.tatagou.sdk.util;

import android.content.Context;
import android.widget.Toast;

public class l {
    private static final String a = l.class.getSimpleName();

    public static void showToast(Context context, String str, int i, int i2, int i3) {
        if (context != null) {
            Toast.makeText(context, str, 1).show();
        }
    }

    public static void showToastCenter(Context context, String str) {
        showToast(context, str, 17, 0, 0);
    }

    public static void showToastTop(Context context, String str) {
        showToast(context, str, 48, 0, 0);
    }
}
