package com.facebook.imagepipeline.producers;

import android.util.Pair;
import com.facebook.common.internal.g;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import javax.annotation.concurrent.GuardedBy;

public class at<T> implements ai<T> {
    private final ai<T> a;
    private final int b;
    @GuardedBy
    private int c = 0;
    @GuardedBy
    private final ConcurrentLinkedQueue<Pair<j<T>, aj>> d = new ConcurrentLinkedQueue();
    private final Executor e;

    private class a extends m<T, T> {
        final /* synthetic */ at a;

        private a(at atVar, j<T> jVar) {
            this.a = atVar;
            super(jVar);
        }

        protected void a(T t, boolean z) {
            d().b(t, z);
            if (z) {
                c();
            }
        }

        protected void a(Throwable th) {
            d().b(th);
            c();
        }

        protected void a() {
            d().b();
            c();
        }

        private void c() {
            synchronized (this.a) {
                final Pair pair = (Pair) this.a.d.poll();
                if (pair == null) {
                    this.a.c = this.a.c - 1;
                }
            }
            if (pair != null) {
                this.a.e.execute(new Runnable(this) {
                    final /* synthetic */ a b;

                    public void run() {
                        this.b.a.b((j) pair.first, (aj) pair.second);
                    }
                });
            }
        }
    }

    public at(int i, Executor executor, ai<T> aiVar) {
        this.b = i;
        this.e = (Executor) g.a((Object) executor);
        this.a = (ai) g.a((Object) aiVar);
    }

    public void a(j<T> jVar, aj ajVar) {
        Object obj;
        ajVar.c().a(ajVar.b(), "ThrottlingProducer");
        synchronized (this) {
            if (this.c >= this.b) {
                this.d.add(Pair.create(jVar, ajVar));
                obj = 1;
            } else {
                this.c++;
                obj = null;
            }
        }
        if (obj == null) {
            b(jVar, ajVar);
        }
    }

    void b(j<T> jVar, aj ajVar) {
        ajVar.c().a(ajVar.b(), "ThrottlingProducer", null);
        this.a.a(new a(jVar), ajVar);
    }
}
