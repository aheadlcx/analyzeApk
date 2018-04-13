package cn.xiaochuan.push.xiaomi;

import android.app.NotificationManager;
import android.content.Context;
import android.util.Log;
import cn.xiaochuan.push.d;
import com.xiaomi.mipush.sdk.MiPushClient;
import java.util.List;

public class a implements d {
    private Context a;

    private a() {
        this.a = null;
        this.a = cn.xiaochuan.push.a.a().c();
        MiPushClient.registerPush(this.a, "2882303761517279567", "5251727936567");
    }

    public void a(long j) {
        List<String> allUserAccount = MiPushClient.getAllUserAccount(this.a);
        if (!(allUserAccount == null || allUserAccount.isEmpty())) {
            for (String str : allUserAccount) {
                if (!str.equalsIgnoreCase(String.valueOf(j))) {
                    Log.e("Xiaomi", "Push unregister account:" + str);
                    MiPushClient.unsetUserAccount(this.a, str, null);
                }
            }
        }
        if (j > 0) {
            Log.e("Xiaomi", "Push init account:" + j);
            MiPushClient.setUserAccount(this.a, String.valueOf(j), null);
        }
    }

    public void b(long j) {
        MiPushClient.unsetUserAccount(this.a, String.valueOf(j), null);
        Log.e("Xiaomi", "Push unregister account:" + j);
    }

    public void a(int i) {
        if (this.a != null) {
            NotificationManager notificationManager = (NotificationManager) this.a.getSystemService("notification");
            if (notificationManager != null) {
                notificationManager.cancel(i);
            }
            MiPushClient.clearNotification(this.a, i);
        }
    }

    public static d a() {
        return new a();
    }
}
