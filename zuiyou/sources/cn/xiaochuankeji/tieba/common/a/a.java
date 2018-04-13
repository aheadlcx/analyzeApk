package cn.xiaochuankeji.tieba.common.a;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.util.Log;
import cn.xiaochuankeji.badge.b;
import cn.xiaochuankeji.tieba.background.h.d;

public class a {
    private static final String a = a.class.getSimpleName();

    public static void a(Context context, int i, Notification notification) {
        b(context, i, notification);
    }

    public static void a(Context context) {
        b.a(context);
    }

    private static void b(Context context, int i, Notification notification) {
        if (context != null) {
            int h = d.a().h();
            if (cn.xiaochuan.a.a.b()) {
                b.a(context, notification, h);
            } else {
                a(context, h);
            }
            a(context, notification, i);
        }
    }

    private static void a(Context context, int i) {
        Log.d(a, "SetBadgeCount result:" + b.a(context, Math.max(0, Math.min(i, 99))));
    }

    private static void a(Context context, Notification notification, int i) {
        if (notification != null) {
            ((NotificationManager) context.getSystemService("notification")).notify(i, notification);
        }
    }
}
