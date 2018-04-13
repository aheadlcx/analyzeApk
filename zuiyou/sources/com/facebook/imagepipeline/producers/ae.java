package com.facebook.imagepipeline.producers;

import android.os.SystemClock;
import com.facebook.common.memory.a;
import com.facebook.common.memory.g;
import com.facebook.common.memory.i;
import com.facebook.imagepipeline.g.e;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import javax.annotation.Nullable;

public class ae implements ai<e> {
    private final g a;
    private final a b;
    private final af c;

    public ae(g gVar, a aVar, af afVar) {
        this.a = gVar;
        this.b = aVar;
        this.c = afVar;
    }

    public void a(j<e> jVar, aj ajVar) {
        ajVar.c().a(ajVar.b(), "NetworkFetchProducer");
        s b = this.c.b((j) jVar, ajVar);
        this.c.a(b, new ae$1(this, b));
    }

    private void a(s sVar, InputStream inputStream, int i) throws IOException {
        i newOutputStream;
        if (i > 0) {
            newOutputStream = this.a.newOutputStream(i);
        } else {
            newOutputStream = this.a.newOutputStream();
        }
        byte[] bArr = (byte[]) this.b.get(16384);
        while (true) {
            try {
                int read = inputStream.read(bArr);
                if (read < 0) {
                    break;
                } else if (read > 0) {
                    newOutputStream.write(bArr, 0, read);
                    a(newOutputStream, sVar);
                    sVar.a().b(a(newOutputStream.size(), i));
                }
            } finally {
                this.b.release(bArr);
                newOutputStream.close();
            }
        }
        this.c.b(sVar, newOutputStream.size());
        b(newOutputStream, sVar);
    }

    private static float a(int i, int i2) {
        return i2 > 0 ? ((float) i) / ((float) i2) : 1.0f - ((float) Math.exp(((double) (-i)) / 50000.0d));
    }

    private void a(i iVar, s sVar) {
        long uptimeMillis = SystemClock.uptimeMillis();
        if (b(sVar) && uptimeMillis - sVar.f() >= 100) {
            sVar.a(uptimeMillis);
            sVar.d().a(sVar.c(), "NetworkFetchProducer", "intermediate_result");
            a(iVar, false, sVar.a());
        }
    }

    private void b(i iVar, s sVar) {
        Map a = a(sVar, iVar.size());
        al d = sVar.d();
        d.a(sVar.c(), "NetworkFetchProducer", a);
        d.a(sVar.c(), "NetworkFetchProducer", true);
        a(iVar, true, sVar.a());
    }

    private void a(i iVar, boolean z, j<e> jVar) {
        e eVar;
        Throwable th;
        com.facebook.common.references.a a = com.facebook.common.references.a.a(iVar.toByteBuffer());
        try {
            eVar = new e(a);
            try {
                eVar.l();
                jVar.b(eVar, z);
                e.d(eVar);
                com.facebook.common.references.a.c(a);
            } catch (Throwable th2) {
                th = th2;
                e.d(eVar);
                com.facebook.common.references.a.c(a);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            eVar = null;
            e.d(eVar);
            com.facebook.common.references.a.c(a);
            throw th;
        }
    }

    private void a(s sVar, Throwable th) {
        sVar.d().a(sVar.c(), "NetworkFetchProducer", th, null);
        sVar.d().a(sVar.c(), "NetworkFetchProducer", false);
        sVar.a().b(th);
    }

    private void a(s sVar) {
        sVar.d().b(sVar.c(), "NetworkFetchProducer", null);
        sVar.a().b();
    }

    private boolean b(s sVar) {
        if (sVar.b().h()) {
            return this.c.a(sVar);
        }
        return false;
    }

    @Nullable
    private Map<String, String> a(s sVar, int i) {
        if (sVar.d().b(sVar.c())) {
            return this.c.a(sVar, i);
        }
        return null;
    }
}
