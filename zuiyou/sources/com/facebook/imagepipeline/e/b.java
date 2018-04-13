package com.facebook.imagepipeline.e;

import com.facebook.common.references.a;
import com.facebook.imagepipeline.producers.ai;
import com.facebook.imagepipeline.producers.ap;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class b<T> extends a<a<T>> {
    protected /* synthetic */ void b(Object obj, boolean z) {
        a((a) obj, z);
    }

    @Nullable
    public /* synthetic */ Object d() {
        return j();
    }

    public static <T> com.facebook.datasource.b<a<T>> a(ai<a<T>> aiVar, ap apVar, com.facebook.imagepipeline.h.b bVar) {
        return new b(aiVar, apVar, bVar);
    }

    private b(ai<a<T>> aiVar, ap apVar, com.facebook.imagepipeline.h.b bVar) {
        super(aiVar, apVar, bVar);
    }

    @Nullable
    public a<T> j() {
        return a.b((a) super.d());
    }

    protected void a(a<T> aVar) {
        a.c(aVar);
    }

    protected void a(a<T> aVar, boolean z) {
        super.b(a.b(aVar), z);
    }
}
