package com.xiaomi.measite.smack;

import com.xiaomi.smack.a;
import com.xiaomi.smack.d;
import java.util.Date;

class b implements d {
    final /* synthetic */ a a;

    b(a aVar) {
        this.a = aVar;
    }

    public void a(a aVar) {
        com.xiaomi.channel.commonutils.logger.b.c("[Slim] " + this.a.b.format(new Date()) + " Connection reconnected (" + this.a.c.hashCode() + ")");
    }

    public void a(a aVar, int i, Exception exception) {
        com.xiaomi.channel.commonutils.logger.b.c("[Slim] " + this.a.b.format(new Date()) + " Connection closed (" + this.a.c.hashCode() + ")");
    }

    public void a(a aVar, Exception exception) {
        com.xiaomi.channel.commonutils.logger.b.c("[Slim] " + this.a.b.format(new Date()) + " Reconnection failed due to an exception (" + this.a.c.hashCode() + ")");
        exception.printStackTrace();
    }

    public void b(a aVar) {
        com.xiaomi.channel.commonutils.logger.b.c("[Slim] " + this.a.b.format(new Date()) + " Connection started (" + this.a.c.hashCode() + ")");
    }
}
