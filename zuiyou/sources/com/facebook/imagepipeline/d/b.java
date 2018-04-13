package com.facebook.imagepipeline.d;

import com.facebook.cache.disk.c;
import com.facebook.cache.disk.d;
import com.facebook.cache.disk.h;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class b implements f {
    private c a;

    public b(c cVar) {
        this.a = cVar;
    }

    public static d a(com.facebook.cache.disk.b bVar, c cVar) {
        return a(bVar, cVar, Executors.newSingleThreadExecutor());
    }

    public static d a(com.facebook.cache.disk.b bVar, c cVar, Executor executor) {
        return new d(cVar, bVar.g(), new com.facebook.cache.disk.d.b(bVar.f(), bVar.e(), bVar.d()), bVar.i(), bVar.h(), bVar.j(), bVar.k(), executor, bVar.l());
    }

    public h a(com.facebook.cache.disk.b bVar) {
        return a(bVar, this.a.a(bVar));
    }
}
