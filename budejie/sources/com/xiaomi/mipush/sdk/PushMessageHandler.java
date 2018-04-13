package com.xiaomi.mipush.sdk;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import com.ixintui.pushsdk.SdkConstants;
import com.xiaomi.a.a;
import java.util.ArrayList;
import java.util.List;

public class PushMessageHandler extends IntentService {
    private static List a = new ArrayList();
    private Class b;

    public PushMessageHandler() {
        super("mipush message handler");
    }

    private Object a() {
        return a.a(this, "com.xiaomi.mipush.sdk.PushMessageHandler");
    }

    public void onCreate() {
        super.onCreate();
        b();
    }

    private void b() {
        try {
            this.b = a.c(this).loadClass("com.xiaomi.mipush.sdk.PushMessageHandler$a");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void onHandleIntent(Intent intent) {
        a(intent);
    }

    public void onDestroy() {
        super.onDestroy();
    }

    private void a(Intent intent) {
        try {
            Object a = a();
            if (1 != ((Integer) com.ixintui.smartlink.a.a.a(a.b(this), "getPushMode", new Class[]{Context.class}, new Object[]{this})).intValue()) {
                Intent intent2 = new Intent("com.xiaomi.mipush.RECEIVE_MESSAGE");
                intent2.setPackage(getPackageName());
                intent2.putExtras(intent);
                List<ResolveInfo> queryBroadcastReceivers = getPackageManager().queryBroadcastReceivers(intent2, 32);
                if (queryBroadcastReceivers != null) {
                    for (ResolveInfo resolveInfo : queryBroadcastReceivers) {
                        if (resolveInfo.activityInfo != null && resolveInfo.activityInfo.packageName.equals(getPackageName())) {
                            break;
                        }
                    }
                }
                ResolveInfo resolveInfo2 = null;
                if (resolveInfo2 != null) {
                    ((PushMessageReceiver) Class.forName(resolveInfo2.activityInfo.name).newInstance()).onReceive(this, intent2);
                } else {
                    com.ixintui.smartlink.a.a.b(SdkConstants.TAG, "no miReceiver");
                }
            } else if ((a == null || !((Boolean) com.ixintui.smartlink.a.a.a(a.getClass(), null, "b", null, null)).booleanValue()) && com.ixintui.smartlink.a.a.a(this, intent) != null) {
                com.ixintui.smartlink.a.a.a(a.getClass(), a, "a", new Class[]{Context.class, this.b.getClass()}, new Object[]{this, com.ixintui.smartlink.a.a.a(this, intent)});
            }
        } catch (Exception e) {
            com.ixintui.smartlink.a.a.a(e);
        } catch (Throwable th) {
            com.ixintui.smartlink.a.a.a((Exception) th);
        }
    }
}
