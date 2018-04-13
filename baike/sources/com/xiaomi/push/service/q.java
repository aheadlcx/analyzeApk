package com.xiaomi.push.service;

import com.xiaomi.mipush.sdk.ErrorCode;
import com.xiaomi.push.service.XMPushService.h;
import com.xiaomi.push.service.ak.b;
import com.xiaomi.push.service.ak.c;
import java.util.Collection;

public class q extends h {
    private XMPushService a;
    private byte[] b;
    private String c;
    private String d;
    private String e;

    public q(XMPushService xMPushService, String str, String str2, String str3, byte[] bArr) {
        super(9);
        this.a = xMPushService;
        this.c = str;
        this.b = bArr;
        this.d = str2;
        this.e = str3;
    }

    public void a() {
        n a;
        Collection c;
        b bVar;
        n a2 = o.a(this.a);
        if (a2 == null) {
            try {
                a = o.a(this.a, this.c, this.d, this.e);
            } catch (Throwable e) {
                com.xiaomi.channel.commonutils.logger.b.a(e);
                a = a2;
            } catch (Throwable e2) {
                com.xiaomi.channel.commonutils.logger.b.a(e2);
            }
            if (a != null) {
                com.xiaomi.channel.commonutils.logger.b.d("no account for mipush");
                r.a(this.a, ErrorCode.ERROR_AUTHERICATION_ERROR, "no account.");
            }
            c = ak.a().c("5");
            if (c.isEmpty()) {
                bVar = (b) c.iterator().next();
            } else {
                bVar = a.a(this.a);
                j.a(this.a, bVar);
                ak.a().a(bVar);
            }
            if (this.a.f()) {
                this.a.a(true);
                return;
            }
            try {
                if (bVar.m == c.binded) {
                    j.a(this.a, this.c, this.b);
                    return;
                } else if (bVar.m == c.unbind) {
                    XMPushService xMPushService = this.a;
                    XMPushService xMPushService2 = this.a;
                    xMPushService2.getClass();
                    xMPushService.a(new a(xMPushService2, bVar));
                    return;
                } else {
                    return;
                }
            } catch (Exception e3) {
                com.xiaomi.channel.commonutils.logger.b.a((Throwable) e3);
                this.a.a(10, e3);
                return;
            }
        }
        a = a2;
        if (a != null) {
            c = ak.a().c("5");
            if (c.isEmpty()) {
                bVar = (b) c.iterator().next();
            } else {
                bVar = a.a(this.a);
                j.a(this.a, bVar);
                ak.a().a(bVar);
            }
            if (this.a.f()) {
                this.a.a(true);
                return;
            } else if (bVar.m == c.binded) {
                j.a(this.a, this.c, this.b);
                return;
            } else if (bVar.m == c.unbind) {
                XMPushService xMPushService3 = this.a;
                XMPushService xMPushService22 = this.a;
                xMPushService22.getClass();
                xMPushService3.a(new a(xMPushService22, bVar));
                return;
            } else {
                return;
            }
        }
        com.xiaomi.channel.commonutils.logger.b.d("no account for mipush");
        r.a(this.a, ErrorCode.ERROR_AUTHERICATION_ERROR, "no account.");
    }

    public String b() {
        return "register app";
    }
}
