package com.xiaomi.push.service;

import com.xiaomi.smack.b;
import com.xiaomi.smack.e;
import java.util.Map;

class al extends b {
    final /* synthetic */ XMPushService a;

    al(XMPushService xMPushService, Map map, int i, String str, e eVar) {
        this.a = xMPushService;
        super(map, i, str, eVar);
    }

    public byte[] a() {
        try {
            com.xiaomi.push.protobuf.b.b bVar = new com.xiaomi.push.protobuf.b.b();
            bVar.a(at.a().c());
            return bVar.c();
        } catch (Exception e) {
            com.xiaomi.channel.commonutils.logger.b.a("getOBBString err: " + e.toString());
            return null;
        }
    }
}
