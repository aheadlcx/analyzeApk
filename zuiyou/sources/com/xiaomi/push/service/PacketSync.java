package com.xiaomi.push.service;

import android.text.TextUtils;
import com.iflytek.speech.VoiceWakeuperAidl;
import com.xiaomi.network.Fallback;
import com.xiaomi.network.HostManager;
import com.xiaomi.push.protobuf.b.i;
import com.xiaomi.push.protobuf.b.j;
import com.xiaomi.push.protobuf.b.k;
import com.xiaomi.push.service.ap.c;
import com.xiaomi.smack.b;
import com.xiaomi.smack.packet.a;
import com.xiaomi.smack.packet.d;
import com.xiaomi.smack.util.g;
import com.xiaomi.stats.h;
import java.util.Date;

public class PacketSync {
    private XMPushService a;

    PacketSync(XMPushService xMPushService) {
        this.a = xMPushService;
    }

    private void a(a aVar) {
        Object c = aVar.c();
        if (!TextUtils.isEmpty(c)) {
            String[] split = c.split(VoiceWakeuperAidl.PARAMS_SEPARATE);
            Fallback fallbacksByHost = HostManager.getInstance().getFallbacksByHost(b.b(), false);
            if (fallbacksByHost != null && split.length > 0) {
                fallbacksByHost.a(split);
                this.a.a(20, null);
                this.a.a(true);
            }
        }
    }

    private void b(d dVar) {
        Object m = dVar.m();
        Object l = dVar.l();
        if (!TextUtils.isEmpty(m) && !TextUtils.isEmpty(l)) {
            ap.b b = ap.a().b(l, m);
            if (b != null) {
                g.a(this.a, b.a, (long) g.a(dVar.c()), true, System.currentTimeMillis());
            }
        }
    }

    private void c(com.xiaomi.slim.b bVar) {
        Object j = bVar.j();
        Object num = Integer.toString(bVar.c());
        if (!TextUtils.isEmpty(j) && !TextUtils.isEmpty(num)) {
            ap.b b = ap.a().b(num, j);
            if (b != null) {
                g.a(this.a, b.a, (long) bVar.l(), true, System.currentTimeMillis());
            }
        }
    }

    public void a(com.xiaomi.slim.b bVar) {
        if (5 != bVar.c()) {
            c(bVar);
        }
        try {
            b(bVar);
        } catch (Throwable e) {
            com.xiaomi.channel.commonutils.logger.b.a("handle Blob chid = " + bVar.c() + " cmd = " + bVar.a() + " packetid = " + bVar.h() + " failure ", e);
        }
    }

    public void a(d dVar) {
        if (!"5".equals(dVar.l())) {
            b(dVar);
        }
        String l = dVar.l();
        if (TextUtils.isEmpty(l)) {
            l = "1";
            dVar.l(l);
        }
        if (l.equals("0")) {
            com.xiaomi.channel.commonutils.logger.b.a("Received wrong packet with chid = 0 : " + dVar.c());
        }
        a p;
        if (dVar instanceof com.xiaomi.smack.packet.b) {
            p = dVar.p("kick");
            if (p != null) {
                String m = dVar.m();
                String a = p.a("type");
                String a2 = p.a("reason");
                com.xiaomi.channel.commonutils.logger.b.a("kicked by server, chid=" + l + " userid=" + m + " type=" + a + " reason=" + a2);
                if ("wait".equals(a)) {
                    ap.b b = ap.a().b(l, m);
                    if (b != null) {
                        this.a.a(b);
                        b.a(c.unbind, 3, 0, a2, a);
                        return;
                    }
                    return;
                }
                this.a.a(l, m, 3, a2, a);
                ap.a().a(l, m);
                return;
            }
        } else if (dVar instanceof com.xiaomi.smack.packet.c) {
            com.xiaomi.smack.packet.c cVar = (com.xiaomi.smack.packet.c) dVar;
            if ("redir".equals(cVar.a())) {
                p = cVar.p("hosts");
                if (p != null) {
                    a(p);
                    return;
                }
                return;
            }
        }
        this.a.e().a(this.a, l, dVar);
    }

    public void b(com.xiaomi.slim.b bVar) {
        String a = bVar.a();
        switch (bVar.c()) {
            case 0:
                if ("PING".equals(a)) {
                    byte[] k = bVar.k();
                    if (k != null && k.length > 0) {
                        j b = j.b(k);
                        if (b.f()) {
                            az.a().a(b.g());
                        }
                    }
                    if ("1".equals(bVar.h())) {
                        this.a.a();
                    } else {
                        h.b();
                    }
                    this.a.i();
                    return;
                } else if ("SYNC".equals(a)) {
                    if ("CONF".equals(bVar.b())) {
                        az.a().a(com.xiaomi.push.protobuf.b.b.b(bVar.k()));
                        return;
                    } else if (TextUtils.equals("U", bVar.b())) {
                        k b2 = k.b(bVar.k());
                        com.xiaomi.push.log.b.a(this.a).a(b2.d(), b2.f(), new Date(b2.h()), new Date(b2.j()), b2.n() * 1024, b2.l());
                        com.xiaomi.slim.b bVar2 = new com.xiaomi.slim.b();
                        bVar2.a(0);
                        bVar2.a(bVar.a(), "UCA");
                        bVar2.a(bVar.h());
                        this.a.a(new ay(this.a, bVar2));
                        return;
                    } else if (TextUtils.equals("P", bVar.b())) {
                        i b3 = i.b(bVar.k());
                        com.xiaomi.slim.b bVar3 = new com.xiaomi.slim.b();
                        bVar3.a(0);
                        bVar3.a(bVar.a(), "PCA");
                        bVar3.a(bVar.h());
                        i iVar = new i();
                        if (b3.e()) {
                            iVar.a(b3.d());
                        }
                        bVar3.a(iVar.c(), null);
                        this.a.a(new ay(this.a, bVar3));
                        com.xiaomi.channel.commonutils.logger.b.a("ACK msgP: id = " + bVar.h());
                        return;
                    } else {
                        return;
                    }
                } else if ("NOTIFY".equals(bVar.a())) {
                    com.xiaomi.push.protobuf.b.h b4 = com.xiaomi.push.protobuf.b.h.b(bVar.k());
                    com.xiaomi.channel.commonutils.logger.b.a("notify by server err = " + b4.d() + " desc = " + b4.f());
                    return;
                } else {
                    return;
                }
            default:
                String num = Integer.toString(bVar.c());
                if ("SECMSG".equals(bVar.a())) {
                    if (bVar.d()) {
                        com.xiaomi.channel.commonutils.logger.b.a("Recv SECMSG errCode = " + bVar.e() + " errStr = " + bVar.f());
                        return;
                    } else {
                        this.a.e().a(this.a, num, bVar);
                        return;
                    }
                } else if ("BIND".equals(a)) {
                    com.xiaomi.push.protobuf.b.d b5 = com.xiaomi.push.protobuf.b.d.b(bVar.k());
                    String j = bVar.j();
                    r0 = ap.a().b(num, j);
                    if (r0 == null) {
                        return;
                    }
                    if (b5.d()) {
                        com.xiaomi.channel.commonutils.logger.b.a("SMACK: channel bind succeeded, chid=" + bVar.c());
                        r0.a(c.binded, 1, 0, null, null);
                        return;
                    }
                    r5 = b5.f();
                    if ("auth".equals(r5)) {
                        if ("invalid-sig".equals(b5.h())) {
                            com.xiaomi.channel.commonutils.logger.b.a("SMACK: bind error invalid-sig token = " + r0.c + " sec = " + r0.i);
                            h.a(0, com.xiaomi.push.thrift.a.BIND_INVALID_SIG.a(), 1, null, 0);
                        }
                        r0.a(c.unbind, 1, 5, b5.h(), r5);
                        ap.a().a(num, j);
                    } else if ("cancel".equals(r5)) {
                        r0.a(c.unbind, 1, 7, b5.h(), r5);
                        ap.a().a(num, j);
                    } else if ("wait".equals(r5)) {
                        this.a.a(r0);
                        r0.a(c.unbind, 1, 7, b5.h(), r5);
                    }
                    com.xiaomi.channel.commonutils.logger.b.a("SMACK: channel bind failed, chid=" + num + " reason=" + b5.h());
                    return;
                } else if ("KICK".equals(a)) {
                    com.xiaomi.push.protobuf.b.g b6 = com.xiaomi.push.protobuf.b.g.b(bVar.k());
                    String j2 = bVar.j();
                    r5 = b6.d();
                    String f = b6.f();
                    com.xiaomi.channel.commonutils.logger.b.a("kicked by server, chid=" + num + " userid=" + j2 + " type=" + r5 + " reason=" + f);
                    if ("wait".equals(r5)) {
                        r0 = ap.a().b(num, j2);
                        if (r0 != null) {
                            this.a.a(r0);
                            r0.a(c.unbind, 3, 0, f, r5);
                            return;
                        }
                        return;
                    }
                    this.a.a(num, j2, 3, f, r5);
                    ap.a().a(num, j2);
                    return;
                } else {
                    return;
                }
        }
    }
}
