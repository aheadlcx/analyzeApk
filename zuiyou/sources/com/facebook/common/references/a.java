package com.facebook.common.references;

import java.io.Closeable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.Nullable;

public abstract class a<T> implements Closeable, Cloneable {
    private static Class<a> a = a.class;
    private static final c<Closeable> b = new a$1();
    private static volatile boolean c = true;

    public abstract T a();

    public abstract a<T> b();

    public abstract a<T> c();

    public abstract void close();

    public abstract boolean d();

    public abstract int e();

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return b();
    }

    @Nullable
    public static <T extends Closeable> a<T> a(@Nullable T t) {
        if (t == null) {
            return null;
        }
        return b(t, b);
    }

    @Nullable
    public static <T> a<T> a(@Nullable T t, c<T> cVar) {
        if (t == null) {
            return null;
        }
        return b(t, cVar);
    }

    private static <T> a<T> b(@Nullable T t, c<T> cVar) {
        if (c) {
            return new a$a(t, cVar, null);
        }
        return new a$b(t, cVar, null);
    }

    public static boolean a(@Nullable a<?> aVar) {
        return aVar != null && aVar.d();
    }

    @Nullable
    public static <T> a<T> b(@Nullable a<T> aVar) {
        return aVar != null ? aVar.c() : null;
    }

    public static <T> List<a<T>> a(Collection<a<T>> collection) {
        if (collection == null) {
            return null;
        }
        List<a<T>> arrayList = new ArrayList(collection.size());
        for (a b : collection) {
            arrayList.add(b(b));
        }
        return arrayList;
    }

    public static void c(@Nullable a<?> aVar) {
        if (aVar != null) {
            aVar.close();
        }
    }

    public static void a(@Nullable Iterable<? extends a<?>> iterable) {
        if (iterable != null) {
            for (a c : iterable) {
                c(c);
            }
        }
    }
}
