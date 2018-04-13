package com.xiaomi.mipush.sdk;

import android.content.Context;
import com.xiaomi.channel.commonutils.android.h;
import com.xiaomi.channel.commonutils.misc.f.a;
import com.xiaomi.push.service.k;
import com.xiaomi.xmpush.thrift.ae;
import com.xiaomi.xmpush.thrift.o;
import java.util.HashMap;
import java.util.Map;

public class aa extends a {
    private Context a;

    public aa(Context context) {
        this.a = context;
    }

    public int a() {
        return 13;
    }

    public void run() {
        org.apache.thrift.a aeVar = new ae(MiPushClient.a(), false);
        a a = a.a(this.a);
        aeVar.c(o.SyncMIID.N);
        aeVar.b(a.c());
        aeVar.d(this.a.getPackageName());
        Map hashMap = new HashMap();
        h.a(hashMap, Constants.EXTRA_KEY_MIID, k.a(this.a).c());
        aeVar.h = hashMap;
        u.a(this.a).a(aeVar, com.xiaomi.xmpush.thrift.a.Notification, true, null);
    }
}
