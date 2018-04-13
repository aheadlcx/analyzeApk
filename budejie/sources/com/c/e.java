package com.c;

import android.view.View;
import com.c.a.a;
import com.c.a.c;

final class e implements a<T> {
    final /* synthetic */ int a;
    final /* synthetic */ b b;

    e(b bVar, int i) {
        this.b = bVar;
        this.a = i;
    }

    public final int a() {
        return this.a;
    }

    public final boolean a(T t, int i) {
        return true;
    }

    public final void a(c cVar, T t, int i) {
        this.b.convert(cVar, t, i);
    }

    public final void a(c cVar, View view) {
        this.b.onViewHolderCreated(cVar, view);
    }
}
