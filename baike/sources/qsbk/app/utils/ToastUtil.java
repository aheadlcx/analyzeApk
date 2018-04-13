package qsbk.app.utils;

import android.widget.Toast;
import qsbk.app.QsbkApp;

public class ToastUtil {
    public static void Long(String str) {
        Toast.makeText(QsbkApp.mContext, str, 1).show();
    }

    public static void Long(int i) {
        Toast.makeText(QsbkApp.mContext, i, 1).show();
    }

    public static void Short(String str) {
        Toast.makeText(QsbkApp.mContext, str, 0).show();
    }

    public static void Short(int i) {
        Toast.makeText(QsbkApp.mContext, i, 0).show();
    }
}
