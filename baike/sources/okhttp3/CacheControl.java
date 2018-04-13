package okhttp3;

import cz.msebera.android.httpclient.client.cache.HeaderConstants;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import okhttp3.internal.http.HttpHeaders;

public final class CacheControl {
    public static final CacheControl FORCE_CACHE = new CacheControl$Builder().onlyIfCached().maxStale(Integer.MAX_VALUE, TimeUnit.SECONDS).build();
    public static final CacheControl FORCE_NETWORK = new CacheControl$Builder().noCache().build();
    @Nullable
    String a;
    private final boolean b;
    private final boolean c;
    private final int d;
    private final int e;
    private final boolean f;
    private final boolean g;
    private final boolean h;
    private final int i;
    private final int j;
    private final boolean k;
    private final boolean l;
    private final boolean m;

    private CacheControl(boolean z, boolean z2, int i, int i2, boolean z3, boolean z4, boolean z5, int i3, int i4, boolean z6, boolean z7, boolean z8, @Nullable String str) {
        this.b = z;
        this.c = z2;
        this.d = i;
        this.e = i2;
        this.f = z3;
        this.g = z4;
        this.h = z5;
        this.i = i3;
        this.j = i4;
        this.k = z6;
        this.l = z7;
        this.m = z8;
        this.a = str;
    }

    CacheControl(CacheControl$Builder cacheControl$Builder) {
        this.b = cacheControl$Builder.a;
        this.c = cacheControl$Builder.b;
        this.d = cacheControl$Builder.c;
        this.e = -1;
        this.f = false;
        this.g = false;
        this.h = false;
        this.i = cacheControl$Builder.d;
        this.j = cacheControl$Builder.e;
        this.k = cacheControl$Builder.f;
        this.l = cacheControl$Builder.g;
        this.m = cacheControl$Builder.h;
    }

    public boolean noCache() {
        return this.b;
    }

    public boolean noStore() {
        return this.c;
    }

    public int maxAgeSeconds() {
        return this.d;
    }

    public int sMaxAgeSeconds() {
        return this.e;
    }

    public boolean isPrivate() {
        return this.f;
    }

    public boolean isPublic() {
        return this.g;
    }

    public boolean mustRevalidate() {
        return this.h;
    }

    public int maxStaleSeconds() {
        return this.i;
    }

    public int minFreshSeconds() {
        return this.j;
    }

    public boolean onlyIfCached() {
        return this.k;
    }

    public boolean noTransform() {
        return this.l;
    }

    public boolean immutable() {
        return this.m;
    }

    public static CacheControl parse(Headers headers) {
        String value;
        boolean z = false;
        int i = -1;
        int i2 = -1;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        int i3 = -1;
        int i4 = -1;
        boolean z5 = false;
        boolean z6 = false;
        boolean z7 = false;
        Object obj = 1;
        int size = headers.size();
        int i5 = 0;
        String str = null;
        boolean z8 = false;
        while (i5 < size) {
            boolean z9;
            String name = headers.name(i5);
            value = headers.value(i5);
            if (name.equalsIgnoreCase("Cache-Control")) {
                if (str != null) {
                    obj = null;
                } else {
                    str = value;
                }
            } else if (name.equalsIgnoreCase("Pragma")) {
                obj = null;
            } else {
                z9 = z8;
                i5++;
                z8 = z9;
            }
            z9 = z8;
            int i6 = 0;
            while (i6 < value.length()) {
                String str2;
                int skipUntil = HttpHeaders.skipUntil(value, i6, "=,;");
                String trim = value.substring(i6, skipUntil).trim();
                if (skipUntil == value.length() || value.charAt(skipUntil) == ',' || value.charAt(skipUntil) == ';') {
                    i6 = skipUntil + 1;
                    str2 = null;
                } else {
                    i6 = HttpHeaders.skipWhitespace(value, skipUntil + 1);
                    String trim2;
                    if (i6 >= value.length() || value.charAt(i6) != '\"') {
                        skipUntil = HttpHeaders.skipUntil(value, i6, ",;");
                        trim2 = value.substring(i6, skipUntil).trim();
                        i6 = skipUntil;
                        str2 = trim2;
                    } else {
                        i6++;
                        skipUntil = HttpHeaders.skipUntil(value, i6, "\"");
                        trim2 = value.substring(i6, skipUntil);
                        i6 = skipUntil + 1;
                        str2 = trim2;
                    }
                }
                if (HeaderConstants.CACHE_CONTROL_NO_CACHE.equalsIgnoreCase(trim)) {
                    z9 = true;
                } else if (HeaderConstants.CACHE_CONTROL_NO_STORE.equalsIgnoreCase(trim)) {
                    z = true;
                } else if ("max-age".equalsIgnoreCase(trim)) {
                    i = HttpHeaders.parseSeconds(str2, -1);
                } else if ("s-maxage".equalsIgnoreCase(trim)) {
                    i2 = HttpHeaders.parseSeconds(str2, -1);
                } else if ("private".equalsIgnoreCase(trim)) {
                    z2 = true;
                } else if (HeaderConstants.PUBLIC.equalsIgnoreCase(trim)) {
                    z3 = true;
                } else if (HeaderConstants.CACHE_CONTROL_MUST_REVALIDATE.equalsIgnoreCase(trim)) {
                    z4 = true;
                } else if (HeaderConstants.CACHE_CONTROL_MAX_STALE.equalsIgnoreCase(trim)) {
                    i3 = HttpHeaders.parseSeconds(str2, Integer.MAX_VALUE);
                } else if (HeaderConstants.CACHE_CONTROL_MIN_FRESH.equalsIgnoreCase(trim)) {
                    i4 = HttpHeaders.parseSeconds(str2, -1);
                } else if ("only-if-cached".equalsIgnoreCase(trim)) {
                    z5 = true;
                } else if ("no-transform".equalsIgnoreCase(trim)) {
                    z6 = true;
                } else if ("immutable".equalsIgnoreCase(trim)) {
                    z7 = true;
                }
            }
            i5++;
            z8 = z9;
        }
        if (obj == null) {
            value = null;
        } else {
            value = str;
        }
        return new CacheControl(z8, z, i, i2, z2, z3, z4, i3, i4, z5, z6, z7, value);
    }

    public String toString() {
        String str = this.a;
        if (str != null) {
            return str;
        }
        str = a();
        this.a = str;
        return str;
    }

    private String a() {
        StringBuilder stringBuilder = new StringBuilder();
        if (this.b) {
            stringBuilder.append("no-cache, ");
        }
        if (this.c) {
            stringBuilder.append("no-store, ");
        }
        if (this.d != -1) {
            stringBuilder.append("max-age=").append(this.d).append(", ");
        }
        if (this.e != -1) {
            stringBuilder.append("s-maxage=").append(this.e).append(", ");
        }
        if (this.f) {
            stringBuilder.append("private, ");
        }
        if (this.g) {
            stringBuilder.append("public, ");
        }
        if (this.h) {
            stringBuilder.append("must-revalidate, ");
        }
        if (this.i != -1) {
            stringBuilder.append("max-stale=").append(this.i).append(", ");
        }
        if (this.j != -1) {
            stringBuilder.append("min-fresh=").append(this.j).append(", ");
        }
        if (this.k) {
            stringBuilder.append("only-if-cached, ");
        }
        if (this.l) {
            stringBuilder.append("no-transform, ");
        }
        if (this.m) {
            stringBuilder.append("immutable, ");
        }
        if (stringBuilder.length() == 0) {
            return "";
        }
        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        return stringBuilder.toString();
    }
}
