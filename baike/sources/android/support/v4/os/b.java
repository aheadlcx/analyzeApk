package android.support.v4.os;

import android.os.Build.VERSION;
import android.support.annotation.GuardedBy;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Locale;

@RequiresApi(14)
@RestrictTo({Scope.LIBRARY_GROUP})
final class b {
    private static final Locale[] c = new Locale[0];
    private static final b d = new b(new Locale[0]);
    private static final Locale e = new Locale("en", "XA");
    private static final Locale f = new Locale("ar", "XB");
    private static final Locale g = a.a("en-Latn");
    private static final Object h = new Object();
    @GuardedBy("sLock")
    private static b i = null;
    @GuardedBy("sLock")
    private static b j = null;
    @GuardedBy("sLock")
    private static b k = null;
    @GuardedBy("sLock")
    private static Locale l = null;
    private final Locale[] a;
    @NonNull
    private final String b;

    @RestrictTo({Scope.LIBRARY_GROUP})
    Locale a(int i) {
        return (i < 0 || i >= this.a.length) ? null : this.a[i];
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    boolean a() {
        return this.a.length == 0;
    }

    @IntRange(from = 0)
    @RestrictTo({Scope.LIBRARY_GROUP})
    int b() {
        return this.a.length;
    }

    @IntRange(from = -1)
    @RestrictTo({Scope.LIBRARY_GROUP})
    int a(Locale locale) {
        for (int i = 0; i < this.a.length; i++) {
            if (this.a[i].equals(locale)) {
                return i;
            }
        }
        return -1;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof b)) {
            return false;
        }
        Locale[] localeArr = ((b) obj).a;
        if (this.a.length != localeArr.length) {
            return false;
        }
        for (int i = 0; i < this.a.length; i++) {
            if (!this.a[i].equals(localeArr[i])) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        int i = 1;
        for (Locale hashCode : this.a) {
            i = (i * 31) + hashCode.hashCode();
        }
        return i;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (int i = 0; i < this.a.length; i++) {
            stringBuilder.append(this.a[i]);
            if (i < this.a.length - 1) {
                stringBuilder.append(',');
            }
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    @NonNull
    String c() {
        return this.b;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    b(@NonNull Locale... localeArr) {
        if (localeArr.length == 0) {
            this.a = c;
            this.b = "";
            return;
        }
        Locale[] localeArr2 = new Locale[localeArr.length];
        HashSet hashSet = new HashSet();
        StringBuilder stringBuilder = new StringBuilder();
        int i = 0;
        while (i < localeArr.length) {
            Locale locale = localeArr[i];
            if (locale == null) {
                throw new NullPointerException("list[" + i + "] is null");
            } else if (hashSet.contains(locale)) {
                throw new IllegalArgumentException("list[" + i + "] is a repetition");
            } else {
                locale = (Locale) locale.clone();
                localeArr2[i] = locale;
                stringBuilder.append(a.a(locale));
                if (i < localeArr.length - 1) {
                    stringBuilder.append(',');
                }
                hashSet.add(locale);
                i++;
            }
        }
        this.a = localeArr2;
        this.b = stringBuilder.toString();
    }

    private static String b(Locale locale) {
        if (VERSION.SDK_INT < 21) {
            return "";
        }
        String script = locale.getScript();
        if (script.isEmpty()) {
            return "";
        }
        return script;
    }

    private static boolean c(Locale locale) {
        return e.equals(locale) || f.equals(locale);
    }

    @IntRange(from = 0, to = 1)
    private static int a(Locale locale, Locale locale2) {
        int i = 1;
        if (locale.equals(locale2)) {
            return 1;
        }
        if (!locale.getLanguage().equals(locale2.getLanguage()) || c(locale) || c(locale2)) {
            return 0;
        }
        String b = b(locale);
        if (b.isEmpty()) {
            b = locale.getCountry();
            if (b.isEmpty() || b.equals(locale2.getCountry())) {
                return 1;
            }
            return 0;
        }
        if (!b.equals(b(locale2))) {
            i = 0;
        }
        return i;
    }

    private int d(Locale locale) {
        for (int i = 0; i < this.a.length; i++) {
            if (a(locale, this.a[i]) > 0) {
                return i;
            }
        }
        return Integer.MAX_VALUE;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int a(java.util.Collection<java.lang.String> r6, boolean r7) {
        /*
        r5 = this;
        r1 = 2147483647; // 0x7fffffff float:NaN double:1.060997895E-314;
        r3 = 0;
        r0 = r5.a;
        r0 = r0.length;
        r2 = 1;
        if (r0 != r2) goto L_0x000c;
    L_0x000a:
        r2 = r3;
    L_0x000b:
        return r2;
    L_0x000c:
        r0 = r5.a;
        r0 = r0.length;
        if (r0 != 0) goto L_0x0013;
    L_0x0011:
        r2 = -1;
        goto L_0x000b;
    L_0x0013:
        if (r7 == 0) goto L_0x0048;
    L_0x0015:
        r0 = g;
        r0 = r5.d(r0);
        if (r0 != 0) goto L_0x001f;
    L_0x001d:
        r2 = r3;
        goto L_0x000b;
    L_0x001f:
        if (r0 >= r1) goto L_0x0048;
    L_0x0021:
        r4 = r6.iterator();
        r2 = r0;
    L_0x0026:
        r0 = r4.hasNext();
        if (r0 == 0) goto L_0x0042;
    L_0x002c:
        r0 = r4.next();
        r0 = (java.lang.String) r0;
        r0 = android.support.v4.os.a.a(r0);
        r0 = r5.d(r0);
        if (r0 != 0) goto L_0x003e;
    L_0x003c:
        r2 = r3;
        goto L_0x000b;
    L_0x003e:
        if (r0 >= r2) goto L_0x0046;
    L_0x0040:
        r2 = r0;
        goto L_0x0026;
    L_0x0042:
        if (r2 != r1) goto L_0x000b;
    L_0x0044:
        r2 = r3;
        goto L_0x000b;
    L_0x0046:
        r0 = r2;
        goto L_0x0040;
    L_0x0048:
        r0 = r1;
        goto L_0x0021;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.os.b.a(java.util.Collection, boolean):int");
    }

    private Locale b(Collection<String> collection, boolean z) {
        int a = a((Collection) collection, z);
        return a == -1 ? null : this.a[a];
    }

    @Nullable
    @RestrictTo({Scope.LIBRARY_GROUP})
    Locale a(String[] strArr) {
        return b(Arrays.asList(strArr), false);
    }
}
