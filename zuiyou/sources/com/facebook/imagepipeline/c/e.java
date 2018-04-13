package com.facebook.imagepipeline.c;

import com.facebook.cache.common.b;
import com.facebook.cache.disk.h;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.memory.g;
import com.facebook.common.memory.j;
import com.facebook.common.references.a;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

public class e {
    private static final Class<?> a = e.class;
    private final h b;
    private final g c;
    private final j d;
    private final Executor e;
    private final Executor f;
    private final ab g = ab.a();
    private final o h;

    public e(h hVar, g gVar, j jVar, Executor executor, Executor executor2, o oVar) {
        this.b = hVar;
        this.c = gVar;
        this.d = jVar;
        this.e = executor;
        this.f = executor2;
        this.h = oVar;
    }

    public boolean a(b bVar) {
        return this.g.b(bVar) || this.b.b(bVar);
    }

    public bolts.g<com.facebook.imagepipeline.g.e> a(b bVar, AtomicBoolean atomicBoolean) {
        com.facebook.imagepipeline.g.e a = this.g.a(bVar);
        if (a != null) {
            return b(bVar, a);
        }
        return b(bVar, atomicBoolean);
    }

    private bolts.g<com.facebook.imagepipeline.g.e> b(final b bVar, final AtomicBoolean atomicBoolean) {
        try {
            return bolts.g.a(new Callable<com.facebook.imagepipeline.g.e>(this) {
                final /* synthetic */ e c;

                public /* synthetic */ Object call() throws Exception {
                    return a();
                }

                public com.facebook.imagepipeline.g.e a() throws Exception {
                    a a;
                    if (atomicBoolean.get()) {
                        throw new CancellationException();
                    }
                    com.facebook.imagepipeline.g.e a2 = this.c.g.a(bVar);
                    if (a2 != null) {
                        com.facebook.common.c.a.a(e.a, "Found image for %s in staging area", bVar.a());
                        this.c.h.c(bVar);
                        a2.a(bVar);
                    } else {
                        com.facebook.common.c.a.a(e.a, "Did not find image for %s in staging area", bVar.a());
                        this.c.h.e();
                        try {
                            a = a.a(this.c.b(bVar));
                            a2 = new com.facebook.imagepipeline.g.e(a);
                            a2.a(bVar);
                            a.c(a);
                        } catch (Exception e) {
                            return null;
                        } catch (Throwable th) {
                            a.c(a);
                        }
                    }
                    if (!Thread.interrupted()) {
                        return a2;
                    }
                    com.facebook.common.c.a.a(e.a, "Host thread was interrupted, decreasing reference count");
                    if (a2 != null) {
                        a2.close();
                    }
                    throw new InterruptedException();
                }
            }, this.e);
        } catch (Throwable e) {
            com.facebook.common.c.a.b(a, e, "Failed to schedule disk-cache read for %s", bVar.a());
            return bolts.g.a(e);
        }
    }

    public void a(final b bVar, com.facebook.imagepipeline.g.e eVar) {
        com.facebook.common.internal.g.a((Object) bVar);
        com.facebook.common.internal.g.a(com.facebook.imagepipeline.g.e.e(eVar));
        this.g.a(bVar, eVar);
        eVar.a(bVar);
        final com.facebook.imagepipeline.g.e a = com.facebook.imagepipeline.g.e.a(eVar);
        try {
            this.f.execute(new Runnable(this) {
                final /* synthetic */ e c;

                public void run() {
                    try {
                        this.c.c(bVar, a);
                    } finally {
                        this.c.g.b(bVar, a);
                        com.facebook.imagepipeline.g.e.d(a);
                    }
                }
            });
        } catch (Throwable e) {
            com.facebook.common.c.a.b(a, e, "Failed to schedule disk-cache write for %s", bVar.a());
            this.g.b(bVar, eVar);
            com.facebook.imagepipeline.g.e.d(a);
        }
    }

    private bolts.g<com.facebook.imagepipeline.g.e> b(b bVar, com.facebook.imagepipeline.g.e eVar) {
        com.facebook.common.c.a.a(a, "Found image for %s in staging area", bVar.a());
        this.h.c(bVar);
        return bolts.g.a(eVar);
    }

    private PooledByteBuffer b(b bVar) throws IOException {
        InputStream a;
        try {
            com.facebook.common.c.a.a(a, "Disk cache read for %s", bVar.a());
            com.facebook.a.a a2 = this.b.a(bVar);
            if (a2 == null) {
                com.facebook.common.c.a.a(a, "Disk cache miss for %s", bVar.a());
                this.h.g();
                return null;
            }
            com.facebook.common.c.a.a(a, "Found entry in disk cache for %s", bVar.a());
            this.h.f();
            a = a2.a();
            PooledByteBuffer newByteBuffer = this.c.newByteBuffer(a, (int) a2.b());
            a.close();
            com.facebook.common.c.a.a(a, "Successful read from disk cache for %s", bVar.a());
            return newByteBuffer;
        } catch (Throwable e) {
            com.facebook.common.c.a.b(a, e, "Exception reading from cache for %s", bVar.a());
            this.h.h();
            throw e;
        } catch (Throwable th) {
            a.close();
        }
    }

    private void c(b bVar, final com.facebook.imagepipeline.g.e eVar) {
        com.facebook.common.c.a.a(a, "About to write to disk-cache for key %s", bVar.a());
        try {
            this.b.a(bVar, new com.facebook.cache.common.h(this) {
                final /* synthetic */ e b;

                public void a(OutputStream outputStream) throws IOException {
                    this.b.d.a(eVar.d(), outputStream);
                }
            });
            com.facebook.common.c.a.a(a, "Successful disk-cache write for key %s", bVar.a());
        } catch (Throwable e) {
            com.facebook.common.c.a.b(a, e, "Failed to write to disk-cache for key %s", bVar.a());
        }
    }
}
