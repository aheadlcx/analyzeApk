package com.xiaomi.mipush.sdk;

import android.content.Context;
import com.sina.weibo.sdk.exception.WeiboAuthException;
import com.xiaomi.channel.commonutils.misc.h.a;
import com.xiaomi.push.service.am;
import com.xiaomi.push.service.k;
import com.xiaomi.xmpush.thrift.ab;
import com.xiaomi.xmpush.thrift.ai;
import com.xiaomi.xmpush.thrift.au;
import com.xiaomi.xmpush.thrift.h;
import com.xiaomi.xmpush.thrift.r;

public class t extends a {
    private Context a;

    public t(Context context) {
        this.a = context;
    }

    public int a() {
        return 2;
    }

    public void run() {
        am a = am.a(this.a);
        org.apache.thrift.a abVar = new ab();
        abVar.a(k.a(a, h.MISC_CONFIG));
        abVar.b(k.a(a, h.PLUGIN_CONFIG));
        org.apache.thrift.a aiVar = new ai(WeiboAuthException.DEFAULT_AUTH_ERROR_CODE, false);
        aiVar.c(r.DailyCheckClientConfig.W);
        aiVar.a(au.a(abVar));
        ac.a(this.a).a(aiVar, com.xiaomi.xmpush.thrift.a.Notification, null);
    }
}
