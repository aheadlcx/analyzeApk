package com.facebook.imagepipeline.producers;

import android.content.ContentResolver;
import android.media.ExifInterface;
import android.net.Uri;
import com.facebook.c.b;
import com.facebook.common.internal.ImmutableMap;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.memory.g;
import com.facebook.common.memory.h;
import com.facebook.common.util.d;
import com.facebook.d.a;
import com.facebook.imagepipeline.common.c;
import com.facebook.imagepipeline.g.e;
import com.facebook.imagepipeline.request.ImageRequest;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.Executor;

public class x implements av<e> {
    private final Executor a;
    private final g b;
    private final ContentResolver c;

    public x(Executor executor, g gVar, ContentResolver contentResolver) {
        this.a = executor;
        this.b = gVar;
        this.c = contentResolver;
    }

    public boolean a(c cVar) {
        return aw.a(512, 512, cVar);
    }

    public void a(j<e> jVar, aj ajVar) {
        al c = ajVar.c();
        String b = ajVar.b();
        final ImageRequest a = ajVar.a();
        final Runnable anonymousClass1 = new aq<e>(this, jVar, c, "LocalExifThumbnailProducer", b) {
            final /* synthetic */ x c;

            protected /* synthetic */ void b(Object obj) {
                a((e) obj);
            }

            protected /* synthetic */ Object c() throws Exception {
                return d();
            }

            protected /* synthetic */ Map c(Object obj) {
                return b((e) obj);
            }

            protected e d() throws Exception {
                ExifInterface a = this.c.a(a.b());
                if (a == null || !a.hasThumbnail()) {
                    return null;
                }
                return this.c.a(this.c.b.newByteBuffer(a.getThumbnail()), a);
            }

            protected void a(e eVar) {
                e.d(eVar);
            }

            protected Map<String, String> b(e eVar) {
                return ImmutableMap.of("createdThumbnail", Boolean.toString(eVar != null));
            }
        };
        ajVar.a(new e(this) {
            final /* synthetic */ x b;

            public void a() {
                anonymousClass1.a();
            }
        });
        this.a.execute(anonymousClass1);
    }

    ExifInterface a(Uri uri) throws IOException {
        String a = d.a(this.c, uri);
        if (a(a)) {
            return new ExifInterface(a);
        }
        return null;
    }

    private e a(PooledByteBuffer pooledByteBuffer, ExifInterface exifInterface) {
        int intValue;
        e a = a.a(new h(pooledByteBuffer));
        int a2 = a(exifInterface);
        int intValue2 = a != null ? ((Integer) a.first).intValue() : -1;
        if (a != null) {
            intValue = ((Integer) a.second).intValue();
        } else {
            intValue = -1;
        }
        com.facebook.common.references.a a3 = com.facebook.common.references.a.a(pooledByteBuffer);
        try {
            a = new e(a3);
            a.a(b.a);
            a.c(a2);
            a.b(intValue2);
            a.a(intValue);
            return a;
        } finally {
            com.facebook.common.references.a.c(a3);
        }
    }

    private int a(ExifInterface exifInterface) {
        return com.facebook.d.b.a(Integer.parseInt(exifInterface.getAttribute(android.support.media.ExifInterface.TAG_ORIENTATION)));
    }

    boolean a(String str) throws IOException {
        if (str == null) {
            return false;
        }
        File file = new File(str);
        if (file.exists() && file.canRead()) {
            return true;
        }
        return false;
    }
}
