package bolts;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

public class g<TResult> {
    public static final ExecutorService a = b.a();
    public static final Executor b = a.b();
    private static final Executor c = b.b();
    private static volatile a d;
    private static g<?> m = new g(null);
    private static g<Boolean> n = new g(Boolean.valueOf(true));
    private static g<Boolean> o = new g(Boolean.valueOf(false));
    private static g<?> p = new g(true);
    private final Object e = new Object();
    private boolean f;
    private boolean g;
    private TResult h;
    private Exception i;
    private boolean j;
    private i k;
    private List<f<TResult, Void>> l = new ArrayList();

    public interface a {
        void a(g<?> gVar, UnobservedTaskException unobservedTaskException);
    }

    public static a a() {
        return d;
    }

    g() {
    }

    private g(TResult tResult) {
        b((Object) tResult);
    }

    private g(boolean z) {
        if (z) {
            g();
        } else {
            b(null);
        }
    }

    public boolean b() {
        boolean z;
        synchronized (this.e) {
            z = this.f;
        }
        return z;
    }

    public boolean c() {
        boolean z;
        synchronized (this.e) {
            z = this.g;
        }
        return z;
    }

    public boolean d() {
        boolean z;
        synchronized (this.e) {
            z = f() != null;
        }
        return z;
    }

    public TResult e() {
        TResult tResult;
        synchronized (this.e) {
            tResult = this.h;
        }
        return tResult;
    }

    public Exception f() {
        Exception exception;
        synchronized (this.e) {
            if (this.i != null) {
                this.j = true;
                if (this.k != null) {
                    this.k.a();
                    this.k = null;
                }
            }
            exception = this.i;
        }
        return exception;
    }

    public static <TResult> g<TResult> a(TResult tResult) {
        if (tResult == null) {
            return m;
        }
        if (tResult instanceof Boolean) {
            return ((Boolean) tResult).booleanValue() ? n : o;
        } else {
            h hVar = new h();
            hVar.b((Object) tResult);
            return hVar.a();
        }
    }

    public static <TResult> g<TResult> a(Exception exception) {
        h hVar = new h();
        hVar.b(exception);
        return hVar.a();
    }

    public static <TResult> g<TResult> a(Callable<TResult> callable, Executor executor) {
        return a((Callable) callable, executor, null);
    }

    public static <TResult> g<TResult> a(final Callable<TResult> callable, Executor executor, final c cVar) {
        final h hVar = new h();
        try {
            executor.execute(new Runnable() {
                public void run() {
                    if (cVar == null || !cVar.a()) {
                        try {
                            hVar.b(callable.call());
                            return;
                        } catch (CancellationException e) {
                            hVar.c();
                            return;
                        } catch (Exception e2) {
                            hVar.b(e2);
                            return;
                        }
                    }
                    hVar.c();
                }
            });
        } catch (Exception e) {
            hVar.b(new ExecutorException(e));
        }
        return hVar.a();
    }

    public <TContinuationResult> g<TContinuationResult> a(f<TResult, TContinuationResult> fVar, Executor executor, c cVar) {
        final h hVar = new h();
        synchronized (this.e) {
            boolean b = b();
            if (!b) {
                final f<TResult, TContinuationResult> fVar2 = fVar;
                final Executor executor2 = executor;
                final c cVar2 = cVar;
                this.l.add(new f<TResult, Void>(this) {
                    final /* synthetic */ g e;

                    public /* synthetic */ Object a(g gVar) throws Exception {
                        return b(gVar);
                    }

                    public Void b(g<TResult> gVar) {
                        g.c(hVar, fVar2, gVar, executor2, cVar2);
                        return null;
                    }
                });
            }
        }
        if (b) {
            c(hVar, fVar, this, executor, cVar);
        }
        return hVar.a();
    }

    public <TContinuationResult> g<TContinuationResult> a(f<TResult, TContinuationResult> fVar) {
        return a((f) fVar, c, null);
    }

    public <TContinuationResult> g<TContinuationResult> b(f<TResult, g<TContinuationResult>> fVar, Executor executor, c cVar) {
        final h hVar = new h();
        synchronized (this.e) {
            boolean b = b();
            if (!b) {
                final f<TResult, g<TContinuationResult>> fVar2 = fVar;
                final Executor executor2 = executor;
                final c cVar2 = cVar;
                this.l.add(new f<TResult, Void>(this) {
                    final /* synthetic */ g e;

                    public /* synthetic */ Object a(g gVar) throws Exception {
                        return b(gVar);
                    }

                    public Void b(g<TResult> gVar) {
                        g.d(hVar, fVar2, gVar, executor2, cVar2);
                        return null;
                    }
                });
            }
        }
        if (b) {
            d(hVar, fVar, this, executor, cVar);
        }
        return hVar.a();
    }

    public <TContinuationResult> g<TContinuationResult> b(f<TResult, g<TContinuationResult>> fVar) {
        return b(fVar, c, null);
    }

    private static <TContinuationResult, TResult> void c(final h<TContinuationResult> hVar, final f<TResult, TContinuationResult> fVar, final g<TResult> gVar, Executor executor, final c cVar) {
        try {
            executor.execute(new Runnable() {
                public void run() {
                    if (cVar == null || !cVar.a()) {
                        try {
                            hVar.b(fVar.a(gVar));
                            return;
                        } catch (CancellationException e) {
                            hVar.c();
                            return;
                        } catch (Exception e2) {
                            hVar.b(e2);
                            return;
                        }
                    }
                    hVar.c();
                }
            });
        } catch (Exception e) {
            hVar.b(new ExecutorException(e));
        }
    }

    private static <TContinuationResult, TResult> void d(final h<TContinuationResult> hVar, final f<TResult, g<TContinuationResult>> fVar, final g<TResult> gVar, Executor executor, final c cVar) {
        try {
            executor.execute(new Runnable() {
                public void run() {
                    if (cVar == null || !cVar.a()) {
                        try {
                            g gVar = (g) fVar.a(gVar);
                            if (gVar == null) {
                                hVar.b(null);
                                return;
                            } else {
                                gVar.a(new f<TContinuationResult, Void>(this) {
                                    final /* synthetic */ AnonymousClass4 a;

                                    {
                                        this.a = r1;
                                    }

                                    public /* synthetic */ Object a(g gVar) throws Exception {
                                        return b(gVar);
                                    }

                                    public Void b(g<TContinuationResult> gVar) {
                                        if (cVar != null && cVar.a()) {
                                            hVar.c();
                                        } else if (gVar.c()) {
                                            hVar.c();
                                        } else if (gVar.d()) {
                                            hVar.b(gVar.f());
                                        } else {
                                            hVar.b(gVar.e());
                                        }
                                        return null;
                                    }
                                });
                                return;
                            }
                        } catch (CancellationException e) {
                            hVar.c();
                            return;
                        } catch (Exception e2) {
                            hVar.b(e2);
                            return;
                        }
                    }
                    hVar.c();
                }
            });
        } catch (Exception e) {
            hVar.b(new ExecutorException(e));
        }
    }

    private void h() {
        synchronized (this.e) {
            for (f a : this.l) {
                try {
                    a.a(this);
                } catch (RuntimeException e) {
                    throw e;
                } catch (Throwable e2) {
                    throw new RuntimeException(e2);
                }
            }
            this.l = null;
        }
    }

    boolean g() {
        boolean z = true;
        synchronized (this.e) {
            if (this.f) {
                z = false;
            } else {
                this.f = true;
                this.g = true;
                this.e.notifyAll();
                h();
            }
        }
        return z;
    }

    boolean b(TResult tResult) {
        boolean z = true;
        synchronized (this.e) {
            if (this.f) {
                z = false;
            } else {
                this.f = true;
                this.h = tResult;
                this.e.notifyAll();
                h();
            }
        }
        return z;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    boolean b(java.lang.Exception r5) {
        /*
        r4 = this;
        r1 = 1;
        r0 = 0;
        r2 = r4.e;
        monitor-enter(r2);
        r3 = r4.f;	 Catch:{ all -> 0x002f }
        if (r3 == 0) goto L_0x000b;
    L_0x0009:
        monitor-exit(r2);	 Catch:{ all -> 0x002f }
    L_0x000a:
        return r0;
    L_0x000b:
        r0 = 1;
        r4.f = r0;	 Catch:{ all -> 0x002f }
        r4.i = r5;	 Catch:{ all -> 0x002f }
        r0 = 0;
        r4.j = r0;	 Catch:{ all -> 0x002f }
        r0 = r4.e;	 Catch:{ all -> 0x002f }
        r0.notifyAll();	 Catch:{ all -> 0x002f }
        r4.h();	 Catch:{ all -> 0x002f }
        r0 = r4.j;	 Catch:{ all -> 0x002f }
        if (r0 != 0) goto L_0x002c;
    L_0x001f:
        r0 = a();	 Catch:{ all -> 0x002f }
        if (r0 == 0) goto L_0x002c;
    L_0x0025:
        r0 = new bolts.i;	 Catch:{ all -> 0x002f }
        r0.<init>(r4);	 Catch:{ all -> 0x002f }
        r4.k = r0;	 Catch:{ all -> 0x002f }
    L_0x002c:
        monitor-exit(r2);	 Catch:{ all -> 0x002f }
        r0 = r1;
        goto L_0x000a;
    L_0x002f:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x002f }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: bolts.g.b(java.lang.Exception):boolean");
    }
}
