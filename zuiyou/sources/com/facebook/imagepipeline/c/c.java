package com.facebook.imagepipeline.c;

import android.net.Uri;
import com.facebook.cache.common.b;
import com.facebook.common.internal.f;
import com.facebook.common.internal.g;
import com.facebook.common.time.RealtimeSinceBootClock;
import com.facebook.imagepipeline.common.a;
import com.facebook.imagepipeline.common.d;
import java.util.Locale;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@Immutable
public class c implements b {
    private final String a;
    @Nullable
    private final com.facebook.imagepipeline.common.c b;
    private final d c;
    private final a d;
    @Nullable
    private final b e;
    @Nullable
    private final String f;
    private final int g;
    private final Object h;
    private final long i;

    public c(String str, @Nullable com.facebook.imagepipeline.common.c cVar, d dVar, a aVar, @Nullable b bVar, @Nullable String str2, Object obj) {
        this.a = (String) g.a(str);
        this.b = cVar;
        this.c = dVar;
        this.d = aVar;
        this.e = bVar;
        this.f = str2;
        this.g = com.facebook.common.util.a.a(Integer.valueOf(str.hashCode()), Integer.valueOf(cVar != null ? cVar.hashCode() : 0), Integer.valueOf(dVar.hashCode()), this.d, this.e, str2);
        this.h = obj;
        this.i = RealtimeSinceBootClock.get().now();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof c)) {
            return false;
        }
        c cVar = (c) obj;
        if (this.g == cVar.g && this.a.equals(cVar.a) && f.a(this.b, cVar.b) && f.a(this.c, cVar.c) && f.a(this.d, cVar.d) && f.a(this.e, cVar.e) && f.a(this.f, cVar.f)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.g;
    }

    public boolean a(Uri uri) {
        return a().contains(uri.toString());
    }

    public String a() {
        return this.a;
    }

    public String toString() {
        return String.format((Locale) null, "%s_%s_%s_%s_%s_%s_%d", new Object[]{this.a, this.b, this.c, this.d, this.e, this.f, Integer.valueOf(this.g)});
    }
}
