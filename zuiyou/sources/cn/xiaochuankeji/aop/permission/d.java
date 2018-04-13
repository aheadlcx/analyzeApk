package cn.xiaochuankeji.aop.permission;

import android.app.Application;

public class d {
    public static Application a;

    public static void a(Application application) {
        if (application == null) {
            throw new IllegalArgumentException("application must not be null");
        }
        a = application;
    }
}
