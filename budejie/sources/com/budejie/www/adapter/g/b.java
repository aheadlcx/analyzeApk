package com.budejie.www.adapter.g;

import android.view.View;
import com.budejie.www.adapter.RowType;

public class b<T> {
    public final int a;
    public final T b;
    public final com.budejie.www.adapter.e.a c;
    public final RowType d;
    public final int e;
    public a f = new a(this);

    public class a {
        public View a;
        public View b;
        final /* synthetic */ b c;

        public a(b bVar) {
            this.c = bVar;
        }
    }

    public b(com.budejie.www.adapter.e.a aVar, T t, int i, RowType rowType, int i2) {
        if (aVar == null) {
            aVar = new com.budejie.www.adapter.g.a.a();
        }
        this.c = aVar;
        this.a = i;
        this.b = t;
        this.d = rowType;
        this.e = i2;
    }
}
