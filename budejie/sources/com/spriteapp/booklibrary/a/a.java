package com.spriteapp.booklibrary.a;

import com.spriteapp.booklibrary.a.a.b;
import java.util.concurrent.TimeUnit;
import okhttp3.w;
import retrofit2.adapter.rxjava2.g;

public class a {
    private static a b;
    public b a;

    private a(w wVar) {
        this.a = (b) new retrofit2.m.a().a("http://s.hxdrive.net/").a(g.a()).a(retrofit2.a.a.a.a()).a(wVar).a().a(b.class);
    }

    public static a a() {
        if (b == null) {
            b = new a(new okhttp3.w.a().a(8, TimeUnit.SECONDS).a(new com.spriteapp.booklibrary.a.a.a()).a(new b()).a());
        }
        return b;
    }
}
