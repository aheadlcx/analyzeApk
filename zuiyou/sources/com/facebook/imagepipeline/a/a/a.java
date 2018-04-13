package com.facebook.imagepipeline.a.a;

import android.os.Looper;
import android.os.SystemClock;
import com.facebook.imagepipeline.g.e;
import com.facebook.imagepipeline.producers.af$a;
import com.facebook.imagepipeline.producers.aj;
import com.facebook.imagepipeline.producers.c;
import com.facebook.imagepipeline.producers.j;
import com.facebook.imagepipeline.producers.s;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import okhttp3.aa;
import okhttp3.ab;
import okhttp3.d$a;
import okhttp3.e$a;
import okhttp3.f;
import okhttp3.y;
import okhttp3.y$a;

public class a extends c<a> {
    private final e$a a;
    private Executor b;

    public static class a extends s {
        public long a;
        public long b;
        public long c;

        public a(j<e> jVar, aj ajVar) {
            super(jVar, ajVar);
        }
    }

    public /* synthetic */ Map a(s sVar, int i) {
        return b((a) sVar, i);
    }

    public /* synthetic */ s b(j jVar, aj ajVar) {
        return a(jVar, ajVar);
    }

    public /* synthetic */ void b(s sVar, int i) {
        a((a) sVar, i);
    }

    public a(e$a e_a, Executor executor) {
        this.a = e_a;
        this.b = executor;
    }

    public a a(j<e> jVar, aj ajVar) {
        return new a(jVar, ajVar);
    }

    public void a(a aVar, af$a af_a) {
        aVar.a = SystemClock.elapsedRealtime();
        try {
            a(aVar, af_a, new y$a().a(new d$a().b().d()).a(aVar.e().toString()).a().d());
        } catch (Throwable e) {
            af_a.a(e);
        }
    }

    public void a(a aVar, int i) {
        aVar.c = SystemClock.elapsedRealtime();
    }

    public Map<String, String> b(a aVar, int i) {
        Map<String, String> hashMap = new HashMap(4);
        hashMap.put("queue_time", Long.toString(aVar.b - aVar.a));
        hashMap.put("fetch_time", Long.toString(aVar.c - aVar.b));
        hashMap.put("total_time", Long.toString(aVar.c - aVar.a));
        hashMap.put("image_size", Integer.toString(i));
        return hashMap;
    }

    protected void a(final a aVar, final af$a af_a, y yVar) {
        final okhttp3.e a = this.a.a(yVar);
        aVar.b().a(new com.facebook.imagepipeline.producers.e(this) {
            final /* synthetic */ a b;

            public void a() {
                if (Looper.myLooper() != Looper.getMainLooper()) {
                    a.b();
                } else {
                    this.b.b.execute(new Runnable(this) {
                        final /* synthetic */ AnonymousClass1 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            a.b();
                        }
                    });
                }
            }
        });
        a.a(new f(this) {
            final /* synthetic */ a c;

            public void a(okhttp3.e eVar, aa aaVar) throws IOException {
                long j = 0;
                aVar.b = SystemClock.elapsedRealtime();
                ab g = aaVar.g();
                try {
                    if (aaVar.c()) {
                        long contentLength = g.contentLength();
                        if (contentLength >= 0) {
                            j = contentLength;
                        }
                        af_a.a(g.byteStream(), (int) j);
                        try {
                            g.close();
                            return;
                        } catch (Throwable e) {
                            com.facebook.common.c.a.a("OkHttpNetworkFetchProducer", "Exception when closing response body", e);
                            return;
                        }
                    }
                    this.c.a(eVar, new IOException("Unexpected HTTP code " + aaVar), af_a);
                    try {
                        g.close();
                    } catch (Throwable e2) {
                        com.facebook.common.c.a.a("OkHttpNetworkFetchProducer", "Exception when closing response body", e2);
                    }
                } catch (Exception e3) {
                    this.c.a(eVar, e3, af_a);
                    try {
                        g.close();
                    } catch (Throwable e22) {
                        com.facebook.common.c.a.a("OkHttpNetworkFetchProducer", "Exception when closing response body", e22);
                    }
                } catch (Throwable e222) {
                    try {
                        g.close();
                    } catch (Throwable e4) {
                        com.facebook.common.c.a.a("OkHttpNetworkFetchProducer", "Exception when closing response body", e4);
                    }
                    throw e222;
                }
            }

            public void a(okhttp3.e eVar, IOException iOException) {
                this.c.a(eVar, (Exception) iOException, af_a);
            }
        });
    }

    private void a(okhttp3.e eVar, Exception exception, af$a af_a) {
        if (eVar.c()) {
            af_a.a();
        } else {
            af_a.a(exception);
        }
    }
}
