package com.xiaomi.mipush.sdk;

import android.content.Context;
import com.xiaomi.channel.commonutils.android.e;
import com.xiaomi.channel.commonutils.android.h;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.channel.commonutils.string.d;
import com.xiaomi.xmpush.thrift.a;
import com.xiaomi.xmpush.thrift.ae;
import com.xiaomi.xmpush.thrift.o;
import java.util.HashMap;

final class y implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ boolean b;

    y(Context context, boolean z) {
        this.a = context;
        this.b = z;
    }

    public void run() {
        b.a("do sync info");
        ae aeVar = new ae(MiPushClient.a(), false);
        a a = a.a(this.a);
        aeVar.c(o.SyncInfo.N);
        aeVar.b(a.c());
        aeVar.d(this.a.getPackageName());
        aeVar.h = new HashMap();
        h.a(aeVar.h, "app_version", com.xiaomi.channel.commonutils.android.b.a(this.a, this.a.getPackageName()));
        h.a(aeVar.h, Constants.EXTRA_KEY_APP_VERSION_CODE, Integer.toString(com.xiaomi.channel.commonutils.android.b.b(this.a, this.a.getPackageName())));
        h.a(aeVar.h, "push_sdk_vn", "3_2_2");
        h.a(aeVar.h, "push_sdk_vc", Integer.toString(30202));
        h.a(aeVar.h, "token", a.d());
        h.a(aeVar.h, Constants.EXTRA_KEY_IMEI_MD5, d.a(e.c(this.a)));
        h.a(aeVar.h, Constants.EXTRA_KEY_REG_ID, a.e());
        h.a(aeVar.h, Constants.EXTRA_KEY_REG_SECRET, a.f());
        h.a(aeVar.h, Constants.EXTRA_KEY_ACCEPT_TIME, MiPushClient.f(this.a).replace(Constants.ACCEPT_TIME_SEPARATOR_SP, Constants.ACCEPT_TIME_SEPARATOR_SERVER));
        if (this.b) {
            h.a(aeVar.h, Constants.EXTRA_KEY_ALIASES_MD5, x.c(MiPushClient.getAllAlias(this.a)));
            h.a(aeVar.h, Constants.EXTRA_KEY_TOPICS_MD5, x.c(MiPushClient.getAllTopic(this.a)));
            h.a(aeVar.h, Constants.EXTRA_KEY_ACCOUNTS_MD5, x.c(MiPushClient.getAllUserAccount(this.a)));
        } else {
            h.a(aeVar.h, Constants.EXTRA_KEY_ALIASES, x.d(MiPushClient.getAllAlias(this.a)));
            h.a(aeVar.h, Constants.EXTRA_KEY_TOPICS, x.d(MiPushClient.getAllTopic(this.a)));
            h.a(aeVar.h, Constants.EXTRA_KEY_ACCOUNTS, x.d(MiPushClient.getAllUserAccount(this.a)));
        }
        u.a(this.a).a(aeVar, a.Notification, false, null);
    }
}
