package com.jakewharton.a.b;

import android.view.View;
import android.view.View.OnClickListener;
import rx.a.a;
import rx.d$a;
import rx.j;

final class b implements d$a<Void> {
    final View a;

    public /* synthetic */ void call(Object obj) {
        a((j) obj);
    }

    b(View view) {
        this.a = view;
    }

    public void a(final j<? super Void> jVar) {
        a.b();
        OnClickListener anonymousClass1 = new OnClickListener(this) {
            final /* synthetic */ b b;

            public void onClick(View view) {
                if (!jVar.isUnsubscribed()) {
                    jVar.onNext(null);
                }
            }
        };
        jVar.add(new a(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            protected void a() {
                this.a.a.setOnClickListener(null);
            }
        });
        this.a.setOnClickListener(anonymousClass1);
    }
}
