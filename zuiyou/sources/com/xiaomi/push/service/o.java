package com.xiaomi.push.service;

import android.text.TextUtils;
import com.sina.weibo.sdk.exception.WeiboAuthException;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.push.service.XMPushService.h;
import com.xiaomi.xmpush.thrift.ai;
import com.xiaomi.xmpush.thrift.au;
import com.xiaomi.xmpush.thrift.f;
import com.xiaomi.xmpush.thrift.u;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.thrift.a;

class o extends h {
    final /* synthetic */ String b;
    final /* synthetic */ List c;
    final /* synthetic */ String d;
    final /* synthetic */ n e;

    o(n nVar, int i, String str, List list, String str2) {
        this.e = nVar;
        this.b = str;
        this.c = list;
        this.d = str2;
        super(i);
    }

    public void a() {
        String a = this.e.a(this.b);
        ArrayList a2 = be.a(this.c, this.b, a, 32768);
        if (a2 != null) {
            Iterator it = a2.iterator();
            while (it.hasNext()) {
                ai aiVar = (ai) it.next();
                aiVar.a("uploadWay", "longXMPushService");
                a a3 = f.a(this.b, a, aiVar, com.xiaomi.xmpush.thrift.a.Notification);
                if (!(TextUtils.isEmpty(this.d) || TextUtils.equals(this.b, this.d))) {
                    if (a3.m() == null) {
                        u uVar = new u();
                        uVar.a(WeiboAuthException.DEFAULT_AUTH_ERROR_CODE);
                        a3.a(uVar);
                    }
                    a3.m().b("ext_traffic_source_pkg", this.d);
                }
                this.e.a.a(this.b, au.a(a3), true);
            }
            for (f d : this.c) {
                b.c("A tinyData uploaded by com.xiaomi.push.service.TinyDataUploader." + d.d());
            }
            return;
        }
        b.d("Get a null XmPushActionNotification list when TinyDataHelper.pack() in XMPushService.");
    }

    public String b() {
        return "Send tiny data.";
    }
}
