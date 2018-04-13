package okhttp3;

import javax.annotation.Nullable;

public final class v$b {
    @Nullable
    final s a;
    final z b;

    public static v$b a(@Nullable s sVar, z zVar) {
        if (zVar == null) {
            throw new NullPointerException("body == null");
        } else if (sVar != null && sVar.a("Content-Type") != null) {
            throw new IllegalArgumentException("Unexpected header: Content-Type");
        } else if (sVar == null || sVar.a("Content-Length") == null) {
            return new v$b(sVar, zVar);
        } else {
            throw new IllegalArgumentException("Unexpected header: Content-Length");
        }
    }

    public static v$b a(String str, String str2) {
        return a(str, null, z.create(null, str2));
    }

    public static v$b a(String str, @Nullable String str2, z zVar) {
        if (str == null) {
            throw new NullPointerException("name == null");
        }
        StringBuilder stringBuilder = new StringBuilder("form-data; name=");
        v.a(stringBuilder, str);
        if (str2 != null) {
            stringBuilder.append("; filename=");
            v.a(stringBuilder, str2);
        }
        return a(s.a("Content-Disposition", stringBuilder.toString()), zVar);
    }

    private v$b(@Nullable s sVar, z zVar) {
        this.a = sVar;
        this.b = zVar;
    }
}
