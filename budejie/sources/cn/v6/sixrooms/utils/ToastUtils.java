package cn.v6.sixrooms.utils;

import android.os.Handler;
import android.widget.Toast;

public class ToastUtils {
    private static Toast b;
    private Toast a;
    private final Handler c = new ac(this);

    public static void showToast(String str) {
        new Handler().post(new ad(str));
    }

    public static void showToast(int i) {
        new Handler().post(new ae(i));
    }
}
