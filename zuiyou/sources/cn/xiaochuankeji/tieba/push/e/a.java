package cn.xiaochuankeji.tieba.push.e;

import android.content.Context;
import cn.xiaochuan.push.b.b;
import cn.xiaochuankeji.tieba.background.utils.h;
import cn.xiaochuankeji.tieba.widget.PushNotifyPermissionDialog;
import java.text.SimpleDateFormat;
import java.util.Date;

public class a implements cn.xiaochuankeji.tieba.widget.PushNotifyPermissionDialog.a {
    private static int a = 3;
    private static int b = 3;
    private static a f;
    private Context c;
    private int d = 4;
    private int e;

    public static a a() {
        if (f == null) {
            f = new a();
        }
        return f;
    }

    public boolean a(Context context, int i) {
        if (cn.xiaochuankeji.tieba.background.utils.c.a.c().C().push_notification_dialog != 1) {
            com.izuiyou.a.a.a.c("notificationPermission", "online check false");
            return true;
        }
        this.c = context;
        this.e = i;
        boolean a = b.a(context);
        com.izuiyou.a.a.a.c("notificationPermission", "check push notify permission " + a);
        e();
        if (a) {
            return true;
        }
        return c(context, i);
    }

    private void e() {
        cn.xiaochuankeji.tieba.background.a.b().edit().putInt("app_open_times", cn.xiaochuankeji.tieba.background.a.b().getInt("app_open_times", 0) + 1).apply();
    }

    private boolean b(Context context, int i) {
        if (j()) {
            g();
        }
        if (a(context)) {
            cn.xiaochuankeji.tieba.background.a.b().edit().putBoolean("permisson_first_time_open", false).apply();
            com.izuiyou.a.a.a.c("notificationPermission", "zuiyou start first time");
            return true;
        } else if (f()) {
            com.izuiyou.a.a.a.c("notificationPermission", "user forbidden");
            return false;
        } else if (!i() && h()) {
            com.izuiyou.a.a.a.c("notificationPermission", "guest coming first time today");
            cn.xiaochuankeji.tieba.background.a.b().edit().putInt("permmisson_not_show_times_", 0).apply();
            return true;
        } else if (!i() || !h() || i == 1) {
            return false;
        } else {
            com.izuiyou.a.a.a.c("notificationPermission", "user has login, type = " + i);
            cn.xiaochuankeji.tieba.background.a.b().edit().putInt("permmisson_not_show_times_", 0).apply();
            return true;
        }
    }

    private boolean f() {
        int i = cn.xiaochuankeji.tieba.background.a.b().getInt("permissson_force_close_notification", 0);
        int i2 = cn.xiaochuankeji.tieba.background.a.b().getInt("permisson_close_times", 0);
        com.izuiyou.a.a.a.c("notificationPermission", "forbidden " + i + " closeTimes " + i2);
        if (i > 0 || i2 > a) {
            return true;
        }
        return false;
    }

    private void g() {
        int i = cn.xiaochuankeji.tieba.background.a.b().getInt("permmisson_not_show_times_", 0);
        com.izuiyou.a.a.a.c("notificationPermission", "addNoNotificationTimes " + String.valueOf(i + 1));
        cn.xiaochuankeji.tieba.background.a.b().edit().putInt("permmisson_not_show_times_", i + 1).apply();
        cn.xiaochuankeji.tieba.background.a.b().edit().putBoolean(a("permmisson_is_first_comming_today_"), false).apply();
    }

    private boolean h() {
        if (cn.xiaochuankeji.tieba.background.a.b().getInt("permmisson_not_show_times_", 0) > b + 1) {
            return true;
        }
        return false;
    }

    private boolean i() {
        return !cn.xiaochuankeji.tieba.background.a.g().d();
    }

    private boolean j() {
        return cn.xiaochuankeji.tieba.background.a.b().getBoolean(a("permmisson_is_first_comming_today_"), true);
    }

    private String a(String str) {
        return str + new SimpleDateFormat("yyyyMMdd").format(new Date(System.currentTimeMillis()));
    }

    private boolean a(Context context) {
        return cn.xiaochuankeji.tieba.background.a.b().getBoolean("permisson_first_time_open", true);
    }

    private boolean c(Context context, int i) {
        if (b(context, i)) {
            int i2 = (cn.xiaochuankeji.tieba.background.a.b().getInt("permisson_close_times", 0) + 1) % this.d;
            switch (i) {
                case 1:
                    b(context);
                    h.a(this.c, "zy_event_homepage_push_alert" + i2, "tag1");
                    return false;
                case 2:
                    c(context);
                    h.a(this.c, "zy_event_common_push_alert" + i2, "tag1");
                    return false;
                case 3:
                    d(context);
                    h.a(this.c, "zy_event_post_push_alert" + i2, "tag1");
                    return false;
                default:
                    return false;
            }
        }
        com.izuiyou.a.a.a.c("notificationPermission", "is need check false");
        return true;
    }

    private void a(Context context, String str, String str2) {
        PushNotifyPermissionDialog pushNotifyPermissionDialog = new PushNotifyPermissionDialog(context, this);
        pushNotifyPermissionDialog.a(str);
        pushNotifyPermissionDialog.b(str2);
        pushNotifyPermissionDialog.show();
    }

    private void b(Context context) {
        a(context, "消息通知还没打开哦", "好多消息不能及时告诉你");
    }

    private void c(Context context) {
        a(context, "评论发布成功", "打开消息通知，获得实时的点赞消息吧~");
    }

    private void d(Context context) {
        a(context, "帖子发布成功", "打开消息通知，能更及时地看到帖子的回复哦~");
    }

    public void b() {
        cn.xiaochuankeji.tieba.background.a.b().edit().putInt("permisson_close_times", cn.xiaochuankeji.tieba.background.a.b().getInt("permisson_close_times", 0) + 1).apply();
    }

    public void c() {
        int i = cn.xiaochuankeji.tieba.background.a.b().getInt("permissson_force_close_notification", 0) + 1;
        cn.xiaochuankeji.tieba.background.a.b().edit().putInt("permissson_force_close_notification", i).apply();
        if (this.c != null) {
            b(i);
        }
    }

    public void d() {
        b.a();
        int i = cn.xiaochuankeji.tieba.background.a.b().getInt("permission_user_confirmed", 0) + 1;
        cn.xiaochuankeji.tieba.background.a.b().edit().putInt("permission_user_confirmed", i).apply();
        if (this.c != null) {
            a(i);
        }
    }

    private void a(int i) {
        switch (this.e) {
            case 1:
                h.a(this.c, "zy_event_homepage_push_alert" + (i % this.d), "tag2");
                return;
            case 2:
                h.a(this.c, "zy_event_common_push_alert" + (i % this.d), "tag2");
                return;
            case 3:
                h.a(this.c, "zy_event_post_push_alert" + (i % this.d), "tag2");
                return;
            default:
                return;
        }
    }

    private void b(int i) {
        switch (this.e) {
            case 1:
                h.a(this.c, "zy_event_homepage_push_alert" + (i % this.d), "tag3");
                return;
            case 2:
                h.a(this.c, "zy_event_common_push_alert" + (i % this.d), "tag3");
                return;
            case 3:
                h.a(this.c, "zy_event_post_push_alert" + (i % this.d), "tag3");
                return;
            default:
                return;
        }
    }
}
