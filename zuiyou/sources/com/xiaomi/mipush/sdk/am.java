package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.android.g;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.channel.commonutils.string.d;
import com.xiaomi.xmpush.thrift.ai;
import com.xiaomi.xmpush.thrift.r;
import java.util.HashMap;
import org.apache.thrift.a;

final class am implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ boolean b;

    am(Context context, boolean z) {
        this.a = context;
        this.b = z;
    }

    public void run() {
        b.a("do sync info");
        a aiVar = new ai(MiPushClient.generatePacketID(), false);
        c a = c.a(this.a);
        aiVar.c(r.SyncInfo.W);
        aiVar.b(a.c());
        aiVar.d(this.a.getPackageName());
        aiVar.h = new HashMap();
        g.a(aiVar.h, com.umeng.analytics.b.g.d, com.xiaomi.channel.commonutils.android.a.a(this.a, this.a.getPackageName()));
        g.a(aiVar.h, "app_version_code", Integer.toString(com.xiaomi.channel.commonutils.android.a.b(this.a, this.a.getPackageName())));
        g.a(aiVar.h, "push_sdk_vn", "3_5_2");
        g.a(aiVar.h, "push_sdk_vc", Integer.toString(30502));
        g.a(aiVar.h, "token", a.d());
        String a2 = d.a(com.xiaomi.channel.commonutils.android.d.c(this.a));
        Object e = com.xiaomi.channel.commonutils.android.d.e(this.a);
        if (!TextUtils.isEmpty(e)) {
            a2 = a2 + "," + e;
        }
        g.a(aiVar.h, "imei_md5", a2);
        g.a(aiVar.h, "reg_id", a.e());
        g.a(aiVar.h, "reg_secret", a.f());
        g.a(aiVar.h, "accept_time", MiPushClient.getAcceptTime(this.a).replace(",", "-"));
        if (this.b) {
            g.a(aiVar.h, "aliases_md5", w.c(MiPushClient.getAllAlias(this.a)));
            g.a(aiVar.h, "topics_md5", w.c(MiPushClient.getAllTopic(this.a)));
            g.a(aiVar.h, "accounts_md5", w.c(MiPushClient.getAllUserAccount(this.a)));
        } else {
            g.a(aiVar.h, "aliases", w.d(MiPushClient.getAllAlias(this.a)));
            g.a(aiVar.h, "topics", w.d(MiPushClient.getAllTopic(this.a)));
            g.a(aiVar.h, "user_accounts", w.d(MiPushClient.getAllUserAccount(this.a)));
        }
        ac.a(this.a).a(aiVar, com.xiaomi.xmpush.thrift.a.Notification, false, null);
    }
}
