package com.danikula.videocache;

import com.danikula.videocache.a.a;
import com.danikula.videocache.b.b;
import java.io.File;

class c {
    public final File a;
    public final com.danikula.videocache.a.c b;
    public final a c;
    public final com.danikula.videocache.d.c d;
    public final b e;

    c(File file, com.danikula.videocache.a.c cVar, a aVar, com.danikula.videocache.d.c cVar2, b bVar) {
        this.a = file;
        this.b = cVar;
        this.c = aVar;
        this.d = cVar2;
        this.e = bVar;
    }

    File a(String str) {
        return new File(this.a, this.b.a(str));
    }
}
