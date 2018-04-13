package com.facebook.imagepipeline.f;

import android.graphics.Bitmap.Config;
import com.facebook.c.c;
import com.facebook.c.d;
import com.facebook.common.internal.b;
import com.facebook.imagepipeline.animated.factory.f;
import com.facebook.imagepipeline.g.g;
import com.facebook.imagepipeline.g.h;
import com.facebook.imagepipeline.i.e;
import java.io.InputStream;
import java.util.Map;
import javax.annotation.Nullable;

public class a implements b {
    private final f a;
    private final Config b;
    private final e c;
    private final b d;
    @Nullable
    private final Map<c, b> e;

    public a(f fVar, e eVar, Config config) {
        this(fVar, eVar, config, null);
    }

    public a(f fVar, e eVar, Config config, @Nullable Map<c, b> map) {
        this.d = new a$1(this);
        this.a = fVar;
        this.b = config;
        this.c = eVar;
        this.e = map;
    }

    public com.facebook.imagepipeline.g.c a(com.facebook.imagepipeline.g.e eVar, int i, h hVar, com.facebook.imagepipeline.common.a aVar) {
        if (aVar.g != null) {
            return aVar.g.a(eVar, i, hVar, aVar);
        }
        Object e = eVar.e();
        if (e == null || e == c.a) {
            e = d.c(eVar.d());
            eVar.a((c) e);
        }
        if (this.e != null) {
            b bVar = (b) this.e.get(e);
            if (bVar != null) {
                return bVar.a(eVar, i, hVar, aVar);
            }
        }
        return this.d.a(eVar, i, hVar, aVar);
    }

    public com.facebook.imagepipeline.g.c a(com.facebook.imagepipeline.g.e eVar, com.facebook.imagepipeline.common.a aVar) {
        InputStream d = eVar.d();
        if (d == null) {
            return null;
        }
        try {
            com.facebook.imagepipeline.g.c b;
            if (aVar.e || this.a == null) {
                b = b(eVar, aVar);
                b.a(d);
                return b;
            }
            b = this.a.a(eVar, aVar, this.b);
            return b;
        } finally {
            b.a(d);
        }
    }

    public com.facebook.imagepipeline.g.d b(com.facebook.imagepipeline.g.e eVar, com.facebook.imagepipeline.common.a aVar) {
        com.facebook.common.references.a a = this.c.a(eVar, aVar.f);
        try {
            com.facebook.imagepipeline.g.d dVar = new com.facebook.imagepipeline.g.d(a, g.a, eVar.f());
            return dVar;
        } finally {
            a.close();
        }
    }

    public com.facebook.imagepipeline.g.d b(com.facebook.imagepipeline.g.e eVar, int i, h hVar, com.facebook.imagepipeline.common.a aVar) {
        com.facebook.common.references.a a = this.c.a(eVar, aVar.f, i);
        try {
            com.facebook.imagepipeline.g.d dVar = new com.facebook.imagepipeline.g.d(a, hVar, eVar.f());
            return dVar;
        } finally {
            a.close();
        }
    }

    public com.facebook.imagepipeline.g.c c(com.facebook.imagepipeline.g.e eVar, com.facebook.imagepipeline.common.a aVar) {
        return this.a.b(eVar, aVar, this.b);
    }
}
