package com.facebook.imagepipeline.producers;

import com.facebook.common.internal.b;
import com.facebook.common.memory.g;
import com.facebook.common.references.a;
import com.facebook.imagepipeline.g.e;
import com.facebook.imagepipeline.request.ImageRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Executor;

public abstract class y implements ai<e> {
    private final Executor a;
    private final g b;

    protected abstract e a(ImageRequest imageRequest) throws IOException;

    protected abstract String a();

    protected y(Executor executor, g gVar) {
        this.a = executor;
        this.b = gVar;
    }

    public void a(j<e> jVar, aj ajVar) {
        al c = ajVar.c();
        String b = ajVar.b();
        final ImageRequest a = ajVar.a();
        final al alVar = c;
        final String str = b;
        final Runnable anonymousClass1 = new aq<e>(this, jVar, c, a(), b) {
            final /* synthetic */ y e;

            protected /* synthetic */ void b(Object obj) {
                a((e) obj);
            }

            protected /* synthetic */ Object c() throws Exception {
                return d();
            }

            protected e d() throws Exception {
                e a = this.e.a(a);
                if (a == null) {
                    alVar.a(str, this.e.a(), false);
                    return null;
                }
                a.l();
                alVar.a(str, this.e.a(), true);
                return a;
            }

            protected void a(e eVar) {
                e.d(eVar);
            }
        };
        ajVar.a(new e(this) {
            final /* synthetic */ y b;

            public void a() {
                anonymousClass1.a();
            }
        });
        this.a.execute(anonymousClass1);
    }

    protected e a(InputStream inputStream, int i) throws IOException {
        a aVar = null;
        if (i <= 0) {
            try {
                aVar = a.a(this.b.newByteBuffer(inputStream));
            } catch (Throwable th) {
                b.a(inputStream);
                a.c(aVar);
            }
        } else {
            aVar = a.a(this.b.newByteBuffer(inputStream, i));
        }
        e eVar = new e(aVar);
        b.a(inputStream);
        a.c(aVar);
        return eVar;
    }

    protected e b(InputStream inputStream, int i) throws IOException {
        return a(inputStream, i);
    }
}
