package com.bumptech.glide;

import android.content.Context;
import android.os.ParcelFileDescriptor;
import com.bumptech.glide.d.m;
import com.bumptech.glide.f.e;
import com.bumptech.glide.load.b.f;
import com.bumptech.glide.load.b.g;
import com.bumptech.glide.load.b.l;
import com.bumptech.glide.load.resource.a.b;
import com.bumptech.glide.load.resource.d.a;
import com.bumptech.glide.load.resource.e.c;
import java.io.InputStream;

public class d<ModelType> extends c<ModelType> {
    private final l<ModelType, InputStream> g;
    private final l<ModelType, ParcelFileDescriptor> h;
    private final k$c i;

    private static <A, Z, R> e<A, g, Z, R> a(i iVar, l<A, InputStream> lVar, l<A, ParcelFileDescriptor> lVar2, Class<Z> cls, Class<R> cls2, c<Z, R> cVar) {
        if (lVar == null && lVar2 == null) {
            return null;
        }
        if (cVar == null) {
            cVar = iVar.a((Class) cls, (Class) cls2);
        }
        return new e(new f(lVar, lVar2), cVar, iVar.b(g.class, (Class) cls));
    }

    d(Class<ModelType> cls, l<ModelType, InputStream> lVar, l<ModelType, ParcelFileDescriptor> lVar2, Context context, i iVar, m mVar, com.bumptech.glide.d.g gVar, k$c k_c) {
        super(context, cls, a(iVar, lVar, lVar2, a.class, b.class, null), iVar, mVar, gVar);
        this.g = lVar;
        this.h = lVar2;
        this.i = k_c;
    }

    public b<ModelType> j() {
        return (b) this.i.a(new b(this, this.g, this.h, this.i));
    }

    public h<ModelType> k() {
        return (h) this.i.a(new h(this, this.g, this.i));
    }
}
