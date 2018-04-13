package com.facebook.imagepipeline.animated.factory;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Build.VERSION;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.imagepipeline.animated.base.i;
import com.facebook.imagepipeline.animated.base.k;
import com.facebook.imagepipeline.animated.impl.AnimatedImageCompositor;
import com.facebook.imagepipeline.animated.impl.b;
import com.facebook.imagepipeline.b.f;
import com.facebook.imagepipeline.common.a;
import com.facebook.imagepipeline.g.c;
import com.facebook.imagepipeline.g.d;
import com.facebook.imagepipeline.g.e;
import java.util.ArrayList;
import java.util.List;

public class g implements f {
    static e a;
    static e b;
    private final b c;
    private final f d;

    static {
        a = null;
        b = null;
        a = a("com.facebook.animated.gif.GifImage");
        b = a("com.facebook.animated.webp.WebPImage");
    }

    private static e a(String str) {
        try {
            return (e) Class.forName(str).newInstance();
        } catch (Throwable th) {
            return null;
        }
    }

    public g(b bVar, f fVar) {
        this.c = bVar;
        this.d = fVar;
    }

    public c a(e eVar, a aVar, Config config) {
        if (a == null) {
            throw new UnsupportedOperationException("To encode animated gif please add the dependency to the animated-gif module");
        }
        Object c = eVar.c();
        com.facebook.common.internal.g.a(c);
        try {
            PooledByteBuffer pooledByteBuffer = (PooledByteBuffer) c.a();
            c a = a(aVar, a.b(pooledByteBuffer.getNativePtr(), pooledByteBuffer.size()), config);
            return a;
        } finally {
            com.facebook.common.references.a.c(c);
        }
    }

    public c b(e eVar, a aVar, Config config) {
        if (b == null) {
            throw new UnsupportedOperationException("To encode animated webp please add the dependency to the animated-webp module");
        }
        Object c = eVar.c();
        com.facebook.common.internal.g.a(c);
        try {
            PooledByteBuffer pooledByteBuffer = (PooledByteBuffer) c.a();
            c a = a(aVar, b.b(pooledByteBuffer.getNativePtr(), pooledByteBuffer.size()), config);
            return a;
        } finally {
            com.facebook.common.references.a.c(c);
        }
    }

    private c a(a aVar, i iVar, Config config) {
        Iterable a;
        Throwable th;
        com.facebook.common.references.a aVar2 = null;
        try {
            c dVar;
            int c = aVar.c ? iVar.c() - 1 : 0;
            if (aVar.e) {
                dVar = new d(a(iVar, config, c), com.facebook.imagepipeline.g.g.a, 0);
                com.facebook.common.references.a.c(null);
                com.facebook.common.references.a.a(null);
            } else {
                if (aVar.d) {
                    a = a(iVar, config);
                    try {
                        aVar2 = com.facebook.common.references.a.b((com.facebook.common.references.a) a.get(c));
                    } catch (Throwable th2) {
                        th = th2;
                        com.facebook.common.references.a.c(null);
                        com.facebook.common.references.a.a(a);
                        throw th;
                    }
                }
                a = null;
                if (aVar.b && r1 == null) {
                    aVar2 = a(iVar, config, c);
                }
                dVar = new com.facebook.imagepipeline.g.a(k.b(iVar).a(aVar2).a(c).a(r2).e());
                com.facebook.common.references.a.c(aVar2);
                com.facebook.common.references.a.a(r2);
            }
            return dVar;
        } catch (Throwable th3) {
            th = th3;
            a = null;
            com.facebook.common.references.a.c(null);
            com.facebook.common.references.a.a(a);
            throw th;
        }
    }

    private com.facebook.common.references.a<Bitmap> a(i iVar, Config config, int i) {
        com.facebook.common.references.a<Bitmap> a = a(iVar.a(), iVar.b(), config);
        new AnimatedImageCompositor(this.c.a(k.a(iVar), null), new AnimatedImageCompositor.a(this) {
            final /* synthetic */ g a;

            {
                this.a = r1;
            }

            public void a(int i, Bitmap bitmap) {
            }

            public com.facebook.common.references.a<Bitmap> a(int i) {
                return null;
            }
        }).a(i, (Bitmap) a.a());
        return a;
    }

    private List<com.facebook.common.references.a<Bitmap>> a(i iVar, Config config) {
        com.facebook.imagepipeline.animated.base.d a = this.c.a(k.a(iVar), null);
        final List<com.facebook.common.references.a<Bitmap>> arrayList = new ArrayList(a.c());
        AnimatedImageCompositor animatedImageCompositor = new AnimatedImageCompositor(a, new AnimatedImageCompositor.a(this) {
            final /* synthetic */ g b;

            public void a(int i, Bitmap bitmap) {
            }

            public com.facebook.common.references.a<Bitmap> a(int i) {
                return com.facebook.common.references.a.b((com.facebook.common.references.a) arrayList.get(i));
            }
        });
        for (int i = 0; i < a.c(); i++) {
            com.facebook.common.references.a a2 = a(a.e(), a.f(), config);
            animatedImageCompositor.a(i, (Bitmap) a2.a());
            arrayList.add(a2);
        }
        return arrayList;
    }

    @SuppressLint({"NewApi"})
    private com.facebook.common.references.a<Bitmap> a(int i, int i2, Config config) {
        com.facebook.common.references.a<Bitmap> a = this.d.a(i, i2, config);
        ((Bitmap) a.a()).eraseColor(0);
        if (VERSION.SDK_INT >= 12) {
            ((Bitmap) a.a()).setHasAlpha(true);
        }
        return a;
    }
}
