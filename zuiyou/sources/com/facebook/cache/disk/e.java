package com.facebook.cache.disk;

import com.facebook.a.a;
import com.facebook.cache.common.CacheErrorLogger;
import com.facebook.cache.common.CacheErrorLogger.CacheErrorCategory;
import com.facebook.cache.disk.c.b;
import com.facebook.common.file.FileUtils;
import com.facebook.common.internal.g;
import com.facebook.common.internal.i;
import java.io.File;
import java.io.IOException;
import java.util.Collection;

public class e implements c {
    private static final Class<?> b = e.class;
    volatile e$a a = new e$a(null, null);
    private final int c;
    private final i<File> d;
    private final String e;
    private final CacheErrorLogger f;

    public e(int i, i<File> iVar, String str, CacheErrorLogger cacheErrorLogger) {
        this.c = i;
        this.f = cacheErrorLogger;
        this.d = iVar;
        this.e = str;
    }

    public boolean a() {
        try {
            return c().a();
        } catch (IOException e) {
            return false;
        }
    }

    public a b(String str, Object obj) throws IOException {
        return c().b(str, obj);
    }

    public void b() {
        try {
            c().b();
        } catch (Throwable e) {
            com.facebook.common.c.a.b(b, "purgeUnexpectedResources", e);
        }
    }

    public b a(String str, Object obj) throws IOException {
        return c().a(str, obj);
    }

    public Collection<c.a> d() throws IOException {
        return c().d();
    }

    public long a(c.a aVar) throws IOException {
        return c().a(aVar);
    }

    synchronized c c() throws IOException {
        if (f()) {
            e();
            g();
        }
        return (c) g.a(this.a.a);
    }

    private boolean f() {
        e$a e_a = this.a;
        return e_a.a == null || e_a.b == null || !e_a.b.exists();
    }

    void e() {
        if (this.a.a != null && this.a.b != null) {
            com.facebook.common.file.a.b(this.a.b);
        }
    }

    private void g() throws IOException {
        File file = new File((File) this.d.b(), this.e);
        a(file);
        this.a = new e$a(file, new DefaultDiskStorage(file, this.c, this.f));
    }

    void a(File file) throws IOException {
        try {
            FileUtils.a(file);
            com.facebook.common.c.a.b(b, "Created cache directory %s", file.getAbsolutePath());
        } catch (Throwable e) {
            this.f.a(CacheErrorCategory.WRITE_CREATE_DIR, b, "createRootDirectoryIfNecessary", e);
            throw e;
        }
    }
}
