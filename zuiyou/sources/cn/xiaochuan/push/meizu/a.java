package cn.xiaochuan.push.meizu;

import android.app.NotificationManager;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import cn.xiaochuan.push.d;
import com.meizu.cloud.pushsdk.PushManager;

public class a implements d {
    private Context a;

    private a() {
        this.a = null;
        this.a = cn.xiaochuan.push.a.a().c();
        Object pushId = PushManager.getPushId(this.a);
        com.meizu.cloud.a.a.a(true);
        if (TextUtils.isEmpty(pushId)) {
            PushManager.register(this.a, "111020", "5161e95866e24160a56741162cae3980");
        } else {
            cn.xiaochuan.push.a.a().a("mz", pushId);
        }
    }

    public void a(long j) {
        Object pushId = PushManager.getPushId(this.a);
        if (TextUtils.isEmpty(pushId)) {
            PushManager.register(this.a, "111020", "5161e95866e24160a56741162cae3980");
        } else {
            cn.xiaochuan.push.a.a().a("mz", pushId);
        }
    }

    public void b(long j) {
        Log.e("Meizu", "Push unregister account:" + j);
    }

    public void a(int i) {
        if (this.a != null) {
            NotificationManager notificationManager = (NotificationManager) this.a.getSystemService("notification");
            if (notificationManager != null) {
                notificationManager.cancel(i);
            }
        }
    }

    public static d a() {
        return new a();
    }
}
