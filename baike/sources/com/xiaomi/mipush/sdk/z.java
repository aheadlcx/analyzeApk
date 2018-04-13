package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import com.xiaomi.channel.commonutils.android.e;
import com.xiaomi.channel.commonutils.android.h;
import com.xiaomi.channel.commonutils.misc.f;
import com.xiaomi.push.service.ah;
import com.xiaomi.push.service.k;
import com.xiaomi.push.service.k.a;
import com.xiaomi.xmpush.thrift.ae;
import com.xiaomi.xmpush.thrift.o;
import java.util.HashMap;
import java.util.Map;

public class z implements a {
    public z(Context context) {
        k.a(context).a((a) this);
    }

    private void b(String str, Context context) {
        org.apache.thrift.a aeVar = new ae();
        aeVar.c(o.ClientMIIDUpdate.N);
        aeVar.b(a.a(context).c());
        aeVar.a(MiPushClient.a());
        Map hashMap = new HashMap();
        h.a(hashMap, Constants.EXTRA_KEY_MIID, str);
        aeVar.a(hashMap);
        int b = e.b();
        if (b >= 0) {
            aeVar.i().put("space_id", Integer.toString(b));
        }
        u.a(context).a(aeVar, com.xiaomi.xmpush.thrift.a.Notification, true, null);
    }

    public void a(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(MiPushClient.PREF_EXTRA, 0);
        long j = sharedPreferences.getLong("last_sync_miid_time", -1);
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        int a = ah.a(context).a(com.xiaomi.xmpush.thrift.e.SyncMIIDFrequency.a(), 21600);
        if (j == -1) {
            sharedPreferences.edit().putLong("last_sync_miid_time", currentTimeMillis).commit();
        } else if (Math.abs(currentTimeMillis - j) > ((long) a)) {
            f.a(context).a(new aa(context), a);
            sharedPreferences.edit().putLong("last_sync_miid_time", currentTimeMillis).commit();
        }
    }

    public void a(String str, Context context) {
        b(str, context);
    }
}
