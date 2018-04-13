package com.jakewharton.a.b;

import android.view.View;
import android.view.View.OnLongClickListener;
import rx.a.a;
import rx.b.f;
import rx.d$a;
import rx.j;

final class c implements d$a<Void> {
    final View a;
    final f<Boolean> b;

    public /* synthetic */ void call(Object obj) {
        a((j) obj);
    }

    c(View view, f<Boolean> fVar) {
        this.a = view;
        this.b = fVar;
    }

    public void a(final j<? super Void> jVar) {
        a.b();
        OnLongClickListener anonymousClass1 = new OnLongClickListener(this) {
            final /* synthetic */ c b;

            public boolean onLongClick(View view) {
                if (!((Boolean) this.b.b.call()).booleanValue()) {
                    return false;
                }
                if (!jVar.isUnsubscribed()) {
                    jVar.onNext(null);
                }
                return true;
            }
        };
        jVar.add(new a(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            protected void a() {
                this.a.a.setOnLongClickListener(null);
            }
        });
        this.a.setOnLongClickListener(anonymousClass1);
    }
}
