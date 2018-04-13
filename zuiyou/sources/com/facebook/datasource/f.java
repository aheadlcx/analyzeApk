package com.facebook.datasource;

import com.facebook.common.internal.g;
import com.facebook.common.internal.i;
import java.util.List;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class f<T> implements i<b<T>> {
    private final List<i<b<T>>> a;

    public /* synthetic */ Object b() {
        return a();
    }

    private f(List<i<b<T>>> list) {
        g.a(!list.isEmpty(), (Object) "List of suppliers is empty!");
        this.a = list;
    }

    public static <T> f<T> a(List<i<b<T>>> list) {
        return new f(list);
    }

    public b<T> a() {
        return new f$a(this);
    }

    public int hashCode() {
        return this.a.hashCode();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof f)) {
            return false;
        }
        return com.facebook.common.internal.f.a(this.a, ((f) obj).a);
    }

    public String toString() {
        return com.facebook.common.internal.f.a(this).a("list", this.a).toString();
    }
}
