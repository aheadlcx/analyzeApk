package com.facebook.cache.disk;

import com.facebook.common.internal.i;
import java.io.File;

class b$a$1 implements i<File> {
    final /* synthetic */ b$a a;

    b$a$1(b$a b_a) {
        this.a = b_a;
    }

    public /* synthetic */ Object b() {
        return a();
    }

    public File a() {
        return b$a.k(this.a).getApplicationContext().getCacheDir();
    }
}
