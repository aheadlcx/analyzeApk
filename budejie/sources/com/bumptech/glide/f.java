package com.bumptech.glide;

import android.content.Context;
import com.bumptech.glide.d.g;
import com.bumptech.glide.d.m;
import com.bumptech.glide.f.e;
import com.bumptech.glide.load.b.l;
import com.bumptech.glide.load.resource.e.c;

public class f<ModelType, DataType, ResourceType> extends e<ModelType, DataType, ResourceType, ResourceType> {
    private final l<ModelType, DataType> g;
    private final Class<DataType> h;
    private final Class<ResourceType> i;
    private final k$c j;

    private static <A, T, Z, R> com.bumptech.glide.f.f<A, T, Z, R> a(i iVar, l<A, T> lVar, Class<T> cls, Class<Z> cls2, c<Z, R> cVar) {
        return new e(lVar, cVar, iVar.b(cls, cls2));
    }

    f(Context context, i iVar, Class<ModelType> cls, l<ModelType, DataType> lVar, Class<DataType> cls2, Class<ResourceType> cls3, m mVar, g gVar, k$c k_c) {
        super(context, cls, a(iVar, lVar, cls2, cls3, com.bumptech.glide.load.resource.e.e.b()), cls3, iVar, mVar, gVar);
        this.g = lVar;
        this.h = cls2;
        this.i = cls3;
        this.j = k_c;
    }
}
