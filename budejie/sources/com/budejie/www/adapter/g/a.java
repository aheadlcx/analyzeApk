package com.budejie.www.adapter.g;

import android.content.Context;
import android.view.View.OnClickListener;

public abstract class a<T> implements OnClickListener, e<T> {
    protected Context a;
    protected b<T> b;
    protected T c;
    protected int d;

    public a(Context context, b<T> bVar) {
        this.a = context;
        this.c = bVar.b;
        this.b = bVar;
    }

    public void a(b<T> bVar) {
        this.c = bVar.b;
        this.d = bVar.a;
        c();
    }
}
