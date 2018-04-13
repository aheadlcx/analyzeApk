package android.arch.b.a;

import java.util.regex.Pattern;

public final class c {
    private static final Pattern a = Pattern.compile("\\s*\\d+\\s*(,\\s*\\d+\\s*)?");
    private boolean b = false;
    private final String c;
    private String[] d = null;
    private String e;
    private Object[] f;
    private String g = null;
    private String h = null;
    private String i = null;
    private String j = null;

    public static c a(String str) {
        return new c(str);
    }

    private c(String str) {
        this.c = str;
    }

    public c a(String[] strArr) {
        this.d = strArr;
        return this;
    }

    public c a(String str, Object[] objArr) {
        this.e = str;
        this.f = objArr;
        return this;
    }

    public c b(String str) {
        this.i = str;
        return this;
    }

    public c c(String str) {
        if (d(str) || a.matcher(str).matches()) {
            this.j = str;
            return this;
        }
        throw new IllegalArgumentException("invalid LIMIT clauses:" + str);
    }

    public b a() {
        if (!d(this.g) || d(this.h)) {
            StringBuilder stringBuilder = new StringBuilder(120);
            stringBuilder.append("SELECT ");
            if (this.b) {
                stringBuilder.append("DISTINCT ");
            }
            if (this.d == null || this.d.length == 0) {
                stringBuilder.append(" * ");
            } else {
                a(stringBuilder, this.d);
            }
            stringBuilder.append(" FROM ");
            stringBuilder.append(this.c);
            a(stringBuilder, " WHERE ", this.e);
            a(stringBuilder, " GROUP BY ", this.g);
            a(stringBuilder, " HAVING ", this.h);
            a(stringBuilder, " ORDER BY ", this.i);
            a(stringBuilder, " LIMIT ", this.j);
            return new a(stringBuilder.toString(), this.f);
        }
        throw new IllegalArgumentException("HAVING clauses are only permitted when using a groupBy clause");
    }

    private static void a(StringBuilder stringBuilder, String str, String str2) {
        if (!d(str2)) {
            stringBuilder.append(str);
            stringBuilder.append(str2);
        }
    }

    private static void a(StringBuilder stringBuilder, String[] strArr) {
        int length = strArr.length;
        for (int i = 0; i < length; i++) {
            String str = strArr[i];
            if (i > 0) {
                stringBuilder.append(", ");
            }
            stringBuilder.append(str);
        }
        stringBuilder.append(' ');
    }

    private static boolean d(String str) {
        return str == null || str.length() == 0;
    }
}
