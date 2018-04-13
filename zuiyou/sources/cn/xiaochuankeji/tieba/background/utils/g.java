package cn.xiaochuankeji.tieba.background.utils;

import android.os.Looper;
import android.text.TextUtils;
import android.widget.Toast;
import cn.htjyb.ui.widget.a;
import cn.xiaochuan.base.BaseApplication;

public class g {
    public static void a(String str) {
        if (!TextUtils.isEmpty(str) && a()) {
            a.a(BaseApplication.getAppContext(), (CharSequence) str, 0).show();
        }
    }

    public static void b(String str) {
        if (!TextUtils.isEmpty(str) && a()) {
            a.a(BaseApplication.getAppContext(), (CharSequence) str, 1).show();
        }
    }

    public static void c(String str) {
        if (!TextUtils.isEmpty(str) && a()) {
            Toast a = a.a(BaseApplication.getAppContext(), (CharSequence) str, 0);
            a.setGravity(17, 0, 0);
            a.show();
        }
    }

    private static boolean a() {
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            return true;
        }
        return false;
    }
}
