package com.xiaomi.push.log;

import java.io.File;
import java.util.Date;

class c extends b {
    File a;
    final /* synthetic */ int d;
    final /* synthetic */ Date e;
    final /* synthetic */ Date f;
    final /* synthetic */ String g;
    final /* synthetic */ String h;
    final /* synthetic */ boolean i;
    final /* synthetic */ b j;

    c(b bVar, int i, Date date, Date date2, String str, String str2, boolean z) {
        this.j = bVar;
        this.d = i;
        this.e = date;
        this.f = date2;
        this.g = str;
        this.h = str2;
        this.i = z;
        super(bVar);
    }

    public void b() {
        if (com.xiaomi.channel.commonutils.file.c.d()) {
            try {
                File file = new File(this.j.b.getExternalFilesDir(null) + "/.logcache");
                file.mkdirs();
                if (file.isDirectory()) {
                    a aVar = new a();
                    aVar.a(this.d);
                    this.a = aVar.a(this.j.b, this.e, this.f, file);
                }
            } catch (NullPointerException e) {
            }
        }
    }

    public void c() {
        if (this.a != null && this.a.exists()) {
            this.j.a.add(new c(this.j, this.g, this.h, this.a, this.i));
        }
        this.j.a(0);
    }
}
