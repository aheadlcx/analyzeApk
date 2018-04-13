package com.facebook.cache.disk;

import android.content.Context;
import android.support.v4.media.session.PlaybackStateCompat;
import com.facebook.cache.common.CacheErrorLogger;
import com.facebook.cache.common.CacheEventListener;
import com.facebook.common.a.b;
import com.facebook.common.internal.g;
import com.facebook.common.internal.i;
import com.facebook.common.internal.j;
import java.io.File;
import javax.annotation.Nullable;

public class b$a {
    private int a;
    private String b;
    private i<File> c;
    private long d;
    private long e;
    private long f;
    private g g;
    private CacheErrorLogger h;
    private CacheEventListener i;
    private b j;
    private boolean k;
    @Nullable
    private final Context l;

    private b$a(@Nullable Context context) {
        this.a = 1;
        this.b = "image_cache";
        this.d = 41943040;
        this.e = 10485760;
        this.f = PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE;
        this.g = new a();
        this.l = context;
    }

    public b$a a(String str) {
        this.b = str;
        return this;
    }

    public b$a a(File file) {
        this.c = j.a(file);
        return this;
    }

    public b$a a(long j) {
        this.d = j;
        return this;
    }

    public b$a b(long j) {
        this.e = j;
        return this;
    }

    public b$a c(long j) {
        this.f = j;
        return this;
    }

    public b a() {
        boolean z = (this.c == null && this.l == null) ? false : true;
        g.b(z, "Either a non-null context or a base directory path or supplier must be provided.");
        if (this.c == null && this.l != null) {
            this.c = new b$a$1(this);
        }
        return new b(this, null);
    }
}
