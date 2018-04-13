package com.facebook.imagepipeline.producers;

import com.facebook.common.internal.g;

public class ar<T> implements ai<T> {
    private final ai<T> a;
    private final as b;

    public ar(ai<T> aiVar, as asVar) {
        this.a = (ai) g.a((Object) aiVar);
        this.b = asVar;
    }

    public void a(j<T> jVar, aj ajVar) {
        al c = ajVar.c();
        String b = ajVar.b();
        final al alVar = c;
        final String str = b;
        final j<T> jVar2 = jVar;
        final aj ajVar2 = ajVar;
        final Runnable anonymousClass1 = new aq<T>(this, jVar, c, "BackgroundThreadHandoffProducer", b) {
            final /* synthetic */ ar f;

            protected void a(T t) {
                alVar.a(str, "BackgroundThreadHandoffProducer", null);
                this.f.a.a(jVar2, ajVar2);
            }

            protected void b(T t) {
            }

            protected T c() throws Exception {
                return null;
            }
        };
        ajVar.a(new e(this) {
            final /* synthetic */ ar b;

            public void a() {
                anonymousClass1.a();
                this.b.b.b(anonymousClass1);
            }
        });
        this.b.a(anonymousClass1);
    }
}
