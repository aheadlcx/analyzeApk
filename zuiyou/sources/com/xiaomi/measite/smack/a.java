package com.xiaomi.measite.smack;

import com.xiaomi.channel.commonutils.android.h;
import com.xiaomi.slim.b;
import com.xiaomi.smack.d;
import com.xiaomi.smack.f;
import java.text.SimpleDateFormat;
import java.util.Date;

public class a implements com.xiaomi.smack.a.a {
    public static boolean a;
    private SimpleDateFormat b = new SimpleDateFormat("hh:mm:ss aaa");
    private com.xiaomi.smack.a c = null;
    private a d = null;
    private a e = null;
    private d f = null;
    private final String g = "[Slim] ";

    class a implements com.xiaomi.smack.b.a, f {
        String a;
        final /* synthetic */ a b;

        a(a aVar, boolean z) {
            this.b = aVar;
            this.a = z ? " RCV " : " Sent ";
        }

        public void a(b bVar) {
            if (a.a) {
                com.xiaomi.channel.commonutils.logger.b.c("[Slim] " + this.b.b.format(new Date()) + this.a + bVar.toString());
            } else {
                com.xiaomi.channel.commonutils.logger.b.c("[Slim] " + this.b.b.format(new Date()) + this.a + " Blob [" + bVar.a() + "," + bVar.c() + "," + bVar.h() + "]");
            }
        }

        public boolean a(com.xiaomi.smack.packet.d dVar) {
            return true;
        }

        public void b(com.xiaomi.smack.packet.d dVar) {
            if (a.a) {
                com.xiaomi.channel.commonutils.logger.b.c("[Slim] " + this.b.b.format(new Date()) + this.a + " PKT " + dVar.c());
            } else {
                com.xiaomi.channel.commonutils.logger.b.c("[Slim] " + this.b.b.format(new Date()) + this.a + " PKT [" + dVar.l() + "," + dVar.k() + "]");
            }
        }
    }

    static {
        boolean z = true;
        if (h.c() != 1) {
            z = false;
        }
        a = z;
    }

    public a(com.xiaomi.smack.a aVar) {
        this.c = aVar;
        a();
    }

    private void a() {
        this.d = new a(this, true);
        this.e = new a(this, false);
        this.c.a(this.d, this.d);
        this.c.b(this.e, this.e);
        this.f = new b(this);
    }
}
