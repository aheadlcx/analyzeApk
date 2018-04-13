package com.facebook.imagepipeline.producers;

import com.facebook.c.b;
import com.facebook.c.c;
import com.facebook.c.d;
import com.facebook.common.memory.g;
import com.facebook.common.memory.i;
import com.facebook.common.util.TriState;
import com.facebook.imagepipeline.g.e;
import java.io.InputStream;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;

public class ax implements ai<e> {
    private final Executor a;
    private final g b;
    private final ai<e> c;

    private class a extends m<e, e> {
        final /* synthetic */ ax a;
        private final aj b;
        private TriState c = TriState.UNSET;

        public a(ax axVar, j<e> jVar, aj ajVar) {
            this.a = axVar;
            super(jVar);
            this.b = ajVar;
        }

        protected void a(@Nullable e eVar, boolean z) {
            if (this.c == TriState.UNSET && eVar != null) {
                this.c = ax.b(eVar);
            }
            if (this.c == TriState.NO) {
                d().b(eVar, z);
            } else if (!z) {
            } else {
                if (this.c != TriState.YES || eVar == null) {
                    d().b(eVar, z);
                } else {
                    this.a.a(eVar, d(), this.b);
                }
            }
        }
    }

    public ax(Executor executor, g gVar, ai<e> aiVar) {
        this.a = (Executor) com.facebook.common.internal.g.a((Object) executor);
        this.b = (g) com.facebook.common.internal.g.a((Object) gVar);
        this.c = (ai) com.facebook.common.internal.g.a((Object) aiVar);
    }

    public void a(j<e> jVar, aj ajVar) {
        this.c.a(new a(this, jVar, ajVar), ajVar);
    }

    private void a(e eVar, j<e> jVar, aj ajVar) {
        com.facebook.common.internal.g.a((Object) eVar);
        final e a = e.a(eVar);
        this.a.execute(new aq<e>(this, jVar, ajVar.c(), "WebpTranscodeProducer", ajVar.b()) {
            final /* synthetic */ ax c;

            protected /* synthetic */ void a(Object obj) {
                b((e) obj);
            }

            protected /* synthetic */ void b(Object obj) {
                a((e) obj);
            }

            protected /* synthetic */ Object c() throws Exception {
                return d();
            }

            protected e d() throws Exception {
                com.facebook.common.references.a a;
                i newOutputStream = this.c.b.newOutputStream();
                try {
                    ax.b(a, newOutputStream);
                    a = com.facebook.common.references.a.a(newOutputStream.toByteBuffer());
                    e eVar = new e(a);
                    eVar.b(a);
                    com.facebook.common.references.a.c(a);
                    newOutputStream.close();
                    return eVar;
                } catch (Throwable th) {
                    newOutputStream.close();
                }
            }

            protected void a(e eVar) {
                e.d(eVar);
            }

            protected void b(e eVar) {
                e.d(a);
                super.a((Object) eVar);
            }

            protected void a(Exception exception) {
                e.d(a);
                super.a(exception);
            }

            protected void b() {
                e.d(a);
                super.b();
            }
        });
    }

    private static TriState b(e eVar) {
        com.facebook.common.internal.g.a((Object) eVar);
        c c = d.c(eVar.d());
        if (b.b(c)) {
            com.facebook.imagepipeline.nativecode.c a = com.facebook.imagepipeline.nativecode.d.a();
            if (a == null) {
                return TriState.NO;
            }
            return TriState.valueOf(!a.a(c));
        } else if (c == c.a) {
            return TriState.UNSET;
        } else {
            return TriState.NO;
        }
    }

    private static void b(e eVar, i iVar) throws Exception {
        InputStream d = eVar.d();
        c c = d.c(d);
        if (c == b.e || c == b.g) {
            com.facebook.imagepipeline.nativecode.d.a().a(d, iVar, 80);
            eVar.a(b.a);
        } else if (c == b.f || c == b.h) {
            com.facebook.imagepipeline.nativecode.d.a().a(d, iVar);
            eVar.a(b.b);
        } else {
            throw new IllegalArgumentException("Wrong image format");
        }
    }
}
