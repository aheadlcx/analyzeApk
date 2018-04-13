package com.facebook.imagepipeline.request;

import android.net.Uri;
import com.facebook.common.internal.f;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@Immutable
public class b {
    private final String a;
    @Nullable
    private final List<b> b;
    private final boolean c;
    private final String d;

    public static final class b {
        private final Uri a;
        private final int b;
        private final int c;
        @Nullable
        private final ImageRequest$CacheChoice d;

        public b(Uri uri, int i, int i2, @Nullable ImageRequest$CacheChoice imageRequest$CacheChoice) {
            this.a = uri;
            this.b = i;
            this.c = i2;
            this.d = imageRequest$CacheChoice;
        }

        public Uri a() {
            return this.a;
        }

        public int b() {
            return this.b;
        }

        public int c() {
            return this.c;
        }

        @Nullable
        public ImageRequest$CacheChoice d() {
            return this.d;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof b)) {
                return false;
            }
            b bVar = (b) obj;
            if (f.a(this.a, bVar.a) && this.b == bVar.b && this.c == bVar.c && this.d == bVar.d) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return (((this.a.hashCode() * 31) + this.b) * 31) + this.c;
        }

        public String toString() {
            return String.format((Locale) null, "%dx%d %s %s", new Object[]{Integer.valueOf(this.b), Integer.valueOf(this.c), this.a, this.d});
        }
    }

    private b(b$a b_a) {
        this.a = b$a.a(b_a);
        this.b = b$a.b(b_a);
        this.c = b$a.c(b_a);
        this.d = b$a.d(b_a);
    }

    public String a() {
        return this.a;
    }

    public int b() {
        return this.b == null ? 0 : this.b.size();
    }

    public List<b> a(Comparator<b> comparator) {
        int b = b();
        if (b == 0) {
            return Collections.emptyList();
        }
        List<b> arrayList = new ArrayList(b);
        for (int i = 0; i < b; i++) {
            arrayList.add(this.b.get(i));
        }
        Collections.sort(arrayList, comparator);
        return arrayList;
    }

    public boolean c() {
        return this.c;
    }

    public String d() {
        return this.d;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        if (f.a(this.a, bVar.a) && this.c == bVar.c && f.a(this.b, bVar.b)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return f.a(this.a, Boolean.valueOf(this.c), this.b, this.d);
    }

    public String toString() {
        return String.format((Locale) null, "%s-%b-%s-%s", new Object[]{this.a, Boolean.valueOf(this.c), this.b, this.d});
    }

    public static b$a a(String str) {
        return new b$a(str, null);
    }
}
