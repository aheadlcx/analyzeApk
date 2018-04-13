package com.facebook.cache.disk;

import com.facebook.cache.common.h;
import java.io.IOException;
import java.util.Collection;

public interface c {

    public interface a {
        String a();

        long b();

        long d();
    }

    public interface b {
        com.facebook.a.a a(Object obj) throws IOException;

        void a(h hVar, Object obj) throws IOException;

        boolean a();
    }

    long a(a aVar) throws IOException;

    b a(String str, Object obj) throws IOException;

    boolean a();

    com.facebook.a.a b(String str, Object obj) throws IOException;

    void b();

    Collection<a> d() throws IOException;
}
