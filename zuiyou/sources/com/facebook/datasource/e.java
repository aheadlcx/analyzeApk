package com.facebook.datasource;

import com.facebook.common.internal.f;
import com.facebook.common.internal.g;
import com.facebook.common.internal.i;
import java.util.List;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class e<T> implements i<b<T>> {
    private final List<i<b<T>>> a;

    public /* synthetic */ Object b() {
        return a();
    }

    private e(List<i<b<T>>> list) {
        g.a(!list.isEmpty(), (Object) "List of suppliers is empty!");
        this.a = list;
    }

    public static <T> e<T> a(List<i<b<T>>> list) {
        return new e(list);
    }

    public b<T> a() {
        return new e$a(this);
    }

    public int hashCode() {
        return this.a.hashCode();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof e)) {
            return false;
        }
        return f.a(this.a, ((e) obj).a);
    }

    public String toString() {
        return f.a(this).a("list", this.a).toString();
    }
}
