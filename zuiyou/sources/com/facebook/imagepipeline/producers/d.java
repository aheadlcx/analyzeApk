package com.facebook.imagepipeline.producers;

import com.facebook.imagepipeline.common.Priority;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequest$RequestLevel;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

public class d implements aj {
    private final ImageRequest a;
    private final String b;
    private final al c;
    private final Object d;
    private final ImageRequest$RequestLevel e;
    @GuardedBy
    private boolean f;
    @GuardedBy
    private Priority g;
    @GuardedBy
    private boolean h;
    @GuardedBy
    private boolean i = false;
    @GuardedBy
    private final List<ak> j = new ArrayList();

    public d(ImageRequest imageRequest, String str, al alVar, Object obj, ImageRequest$RequestLevel imageRequest$RequestLevel, boolean z, boolean z2, Priority priority) {
        this.a = imageRequest;
        this.b = str;
        this.c = alVar;
        this.d = obj;
        this.e = imageRequest$RequestLevel;
        this.f = z;
        this.g = priority;
        this.h = z2;
    }

    public ImageRequest a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public al c() {
        return this.c;
    }

    public Object d() {
        return this.d;
    }

    public ImageRequest$RequestLevel e() {
        return this.e;
    }

    public synchronized boolean f() {
        return this.f;
    }

    public synchronized Priority g() {
        return this.g;
    }

    public synchronized boolean h() {
        return this.h;
    }

    public void a(ak akVar) {
        Object obj = null;
        synchronized (this) {
            this.j.add(akVar);
            if (this.i) {
                obj = 1;
            }
        }
        if (obj != null) {
            akVar.a();
        }
    }

    public void i() {
        a(j());
    }

    @Nullable
    public synchronized List<ak> a(boolean z) {
        List<ak> list;
        if (z == this.f) {
            list = null;
        } else {
            this.f = z;
            list = new ArrayList(this.j);
        }
        return list;
    }

    @Nullable
    public synchronized List<ak> a(Priority priority) {
        List<ak> list;
        if (priority == this.g) {
            list = null;
        } else {
            this.g = priority;
            list = new ArrayList(this.j);
        }
        return list;
    }

    @Nullable
    public synchronized List<ak> b(boolean z) {
        List<ak> list;
        if (z == this.h) {
            list = null;
        } else {
            this.h = z;
            list = new ArrayList(this.j);
        }
        return list;
    }

    @Nullable
    public synchronized List<ak> j() {
        List<ak> list;
        if (this.i) {
            list = null;
        } else {
            this.i = true;
            list = new ArrayList(this.j);
        }
        return list;
    }

    public static void a(@Nullable List<ak> list) {
        if (list != null) {
            for (ak a : list) {
                a.a();
            }
        }
    }

    public static void b(@Nullable List<ak> list) {
        if (list != null) {
            for (ak b : list) {
                b.b();
            }
        }
    }

    public static void c(@Nullable List<ak> list) {
        if (list != null) {
            for (ak c : list) {
                c.c();
            }
        }
    }

    public static void d(@Nullable List<ak> list) {
        if (list != null) {
            for (ak d : list) {
                d.d();
            }
        }
    }
}
