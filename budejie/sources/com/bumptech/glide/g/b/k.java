package com.bumptech.glide.g.b;

import android.view.View;
import com.bumptech.glide.g.c;

public abstract class k<T extends View, Z> extends a<Z> {
    private static boolean a = false;
    private static Integer c = null;
    protected final T b;
    private final k$a d;

    public k(T t) {
        if (t == null) {
            throw new NullPointerException("View must not be null!");
        }
        this.b = t;
        this.d = new k$a(t);
    }

    public T a() {
        return this.b;
    }

    public void getSize(h hVar) {
        this.d.a(hVar);
    }

    public void setRequest(c cVar) {
        a(cVar);
    }

    public c getRequest() {
        Object c = c();
        if (c == null) {
            return null;
        }
        if (c instanceof c) {
            return (c) c;
        }
        throw new IllegalArgumentException("You must not call setTag() on a view Glide is targeting");
    }

    private void a(Object obj) {
        if (c == null) {
            a = true;
            this.b.setTag(obj);
            return;
        }
        this.b.setTag(c.intValue(), obj);
    }

    private Object c() {
        if (c == null) {
            return this.b.getTag();
        }
        return this.b.getTag(c.intValue());
    }

    public String toString() {
        return "Target for: " + this.b;
    }
}
