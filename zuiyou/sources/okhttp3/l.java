package okhttp3;

import com.iflytek.aiui.AIUIConstant;
import com.iflytek.cloud.SpeechConstant;
import com.meizu.cloud.pushsdk.networking.http.HttpDate;
import com.sina.weibo.sdk.register.mobile.MobileRegisterActivity;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import okhttp3.internal.b.d;
import okhttp3.internal.c;
import okhttp3.internal.publicsuffix.PublicSuffixDatabase;

public final class l {
    private static final Pattern a = Pattern.compile("(\\d{2,4})[^\\d]*");
    private static final Pattern b = Pattern.compile("(?i)(jan|feb|mar|apr|may|jun|jul|aug|sep|oct|nov|dec).*");
    private static final Pattern c = Pattern.compile("(\\d{1,2})[^\\d]*");
    private static final Pattern d = Pattern.compile("(\\d{1,2}):(\\d{1,2}):(\\d{1,2})[^\\d]*");
    private final String e;
    private final String f;
    private final long g;
    private final String h;
    private final String i;
    private final boolean j;
    private final boolean k;
    private final boolean l;
    private final boolean m;

    private l(String str, String str2, long j, String str3, String str4, boolean z, boolean z2, boolean z3, boolean z4) {
        this.e = str;
        this.f = str2;
        this.g = j;
        this.h = str3;
        this.i = str4;
        this.j = z;
        this.k = z2;
        this.m = z3;
        this.l = z4;
    }

    public String a() {
        return this.e;
    }

    public String b() {
        return this.f;
    }

    private static boolean a(String str, String str2) {
        if (str.equals(str2)) {
            return true;
        }
        if (str.endsWith(str2) && str.charAt((str.length() - str2.length()) - 1) == '.' && !c.c(str)) {
            return true;
        }
        return false;
    }

    @Nullable
    public static l a(HttpUrl httpUrl, String str) {
        return a(System.currentTimeMillis(), httpUrl, str);
    }

    @Nullable
    static l a(long j, HttpUrl httpUrl, String str) {
        int length = str.length();
        int a = c.a(str, 0, length, ';');
        int a2 = c.a(str, 0, a, '=');
        if (a2 == a) {
            return null;
        }
        String c = c.c(str, 0, a2);
        if (c.isEmpty() || c.b(c) != -1) {
            return null;
        }
        String c2 = c.c(str, a2 + 1, a);
        if (c.b(c2) != -1) {
            return null;
        }
        String c3;
        long j2 = HttpDate.MAX_DATE;
        long j3 = -1;
        String str2 = null;
        String str3 = null;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = true;
        boolean z4 = false;
        a++;
        while (a < length) {
            long j4;
            int a3 = c.a(str, a, length, ';');
            int a4 = c.a(str, a, a3, '=');
            String c4 = c.c(str, a, a4);
            if (a4 < a3) {
                c3 = c.c(str, a4 + 1, a3);
            } else {
                c3 = "";
            }
            if (c4.equalsIgnoreCase(MobileRegisterActivity.RESPONSE_EXPIRES)) {
                try {
                    j2 = a(c3, 0, c3.length());
                    z4 = true;
                    c3 = str2;
                    j4 = j2;
                } catch (IllegalArgumentException e) {
                    c3 = str2;
                    j4 = j2;
                }
            } else {
                if (c4.equalsIgnoreCase("max-age")) {
                    try {
                        j3 = a(c3);
                        z4 = true;
                        c3 = str2;
                        j4 = j2;
                    } catch (NumberFormatException e2) {
                        c3 = str2;
                        j4 = j2;
                    }
                } else {
                    if (c4.equalsIgnoreCase(SpeechConstant.DOMAIN)) {
                        try {
                            c3 = b(c3);
                            z3 = false;
                            j4 = j2;
                        } catch (IllegalArgumentException e3) {
                            c3 = str2;
                            j4 = j2;
                        }
                    } else {
                        if (c4.equalsIgnoreCase(AIUIConstant.RES_TYPE_PATH)) {
                            str3 = c3;
                            c3 = str2;
                            j4 = j2;
                        } else {
                            if (c4.equalsIgnoreCase("secure")) {
                                z = true;
                                c3 = str2;
                                j4 = j2;
                            } else {
                                if (c4.equalsIgnoreCase("httponly")) {
                                    z2 = true;
                                    c3 = str2;
                                    j4 = j2;
                                } else {
                                    c3 = str2;
                                    j4 = j2;
                                }
                            }
                        }
                    }
                }
            }
            String str4 = c3;
            a = a3 + 1;
            j2 = j4;
            str2 = str4;
        }
        if (j3 == Long.MIN_VALUE) {
            j3 = Long.MIN_VALUE;
        } else if (j3 != -1) {
            if (j3 <= 9223372036854775L) {
                j3 *= 1000;
            } else {
                j3 = Long.MAX_VALUE;
            }
            j3 += j;
            if (j3 < j || j3 > HttpDate.MAX_DATE) {
                j3 = HttpDate.MAX_DATE;
            }
        } else {
            j3 = j2;
        }
        c3 = httpUrl.f();
        if (str2 == null) {
            str2 = c3;
        } else if (!a(c3, str2)) {
            return null;
        }
        if (c3.length() != str2.length() && PublicSuffixDatabase.a().a(str2) == null) {
            return null;
        }
        String substring;
        if (str3 == null || !str3.startsWith("/")) {
            str3 = httpUrl.h();
            a = str3.lastIndexOf(47);
            substring = a != 0 ? str3.substring(0, a) : "/";
        } else {
            substring = str3;
        }
        return new l(c, c2, j3, str2, substring, z, z2, z3, z4);
    }

    private static long a(String str, int i, int i2) {
        int a = a(str, i, i2, false);
        int i3 = -1;
        int i4 = -1;
        int i5 = -1;
        int i6 = -1;
        int i7 = -1;
        int i8 = -1;
        Matcher matcher = d.matcher(str);
        while (a < i2) {
            int a2 = a(str, a + 1, i2, true);
            matcher.region(a, a2);
            if (i3 == -1 && matcher.usePattern(d).matches()) {
                i3 = Integer.parseInt(matcher.group(1));
                i4 = Integer.parseInt(matcher.group(2));
                i5 = Integer.parseInt(matcher.group(3));
            } else if (i6 == -1 && matcher.usePattern(c).matches()) {
                i6 = Integer.parseInt(matcher.group(1));
            } else if (i7 == -1 && matcher.usePattern(b).matches()) {
                i7 = b.pattern().indexOf(matcher.group(1).toLowerCase(Locale.US)) / 4;
            } else if (i8 == -1 && matcher.usePattern(a).matches()) {
                i8 = Integer.parseInt(matcher.group(1));
            }
            a = a(str, a2 + 1, i2, false);
        }
        if (i8 >= 70 && i8 <= 99) {
            i8 += 1900;
        }
        if (i8 >= 0 && i8 <= 69) {
            i8 += 2000;
        }
        if (i8 < 1601) {
            throw new IllegalArgumentException();
        } else if (i7 == -1) {
            throw new IllegalArgumentException();
        } else if (i6 < 1 || i6 > 31) {
            throw new IllegalArgumentException();
        } else if (i3 < 0 || i3 > 23) {
            throw new IllegalArgumentException();
        } else if (i4 < 0 || i4 > 59) {
            throw new IllegalArgumentException();
        } else if (i5 < 0 || i5 > 59) {
            throw new IllegalArgumentException();
        } else {
            Calendar gregorianCalendar = new GregorianCalendar(c.f);
            gregorianCalendar.setLenient(false);
            gregorianCalendar.set(1, i8);
            gregorianCalendar.set(2, i7 - 1);
            gregorianCalendar.set(5, i6);
            gregorianCalendar.set(11, i3);
            gregorianCalendar.set(12, i4);
            gregorianCalendar.set(13, i5);
            gregorianCalendar.set(14, 0);
            return gregorianCalendar.getTimeInMillis();
        }
    }

    private static int a(String str, int i, int i2, boolean z) {
        for (int i3 = i; i3 < i2; i3++) {
            Object obj;
            char charAt = str.charAt(i3);
            Object obj2 = ((charAt >= ' ' || charAt == '\t') && charAt < '' && ((charAt < '0' || charAt > '9') && ((charAt < 'a' || charAt > 'z') && ((charAt < 'A' || charAt > 'Z') && charAt != ':')))) ? null : 1;
            if (z) {
                obj = null;
            } else {
                obj = 1;
            }
            if (obj2 == obj) {
                return i3;
            }
        }
        return i2;
    }

    private static long a(String str) {
        try {
            long parseLong = Long.parseLong(str);
            if (parseLong <= 0) {
                return Long.MIN_VALUE;
            }
            return parseLong;
        } catch (NumberFormatException e) {
            if (!str.matches("-?\\d+")) {
                throw e;
            } else if (str.startsWith("-")) {
                return Long.MIN_VALUE;
            } else {
                return Long.MAX_VALUE;
            }
        }
    }

    private static String b(String str) {
        if (str.endsWith(".")) {
            throw new IllegalArgumentException();
        }
        if (str.startsWith(".")) {
            str = str.substring(1);
        }
        String a = c.a(str);
        if (a != null) {
            return a;
        }
        throw new IllegalArgumentException();
    }

    public static List<l> a(HttpUrl httpUrl, s sVar) {
        List b = sVar.b("Set-Cookie");
        List list = null;
        int size = b.size();
        for (int i = 0; i < size; i++) {
            l a = a(httpUrl, (String) b.get(i));
            if (a != null) {
                List arrayList;
                if (list == null) {
                    arrayList = new ArrayList();
                } else {
                    arrayList = list;
                }
                arrayList.add(a);
                list = arrayList;
            }
        }
        if (list != null) {
            return Collections.unmodifiableList(list);
        }
        return Collections.emptyList();
    }

    public String toString() {
        return a(false);
    }

    String a(boolean z) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.e);
        stringBuilder.append('=');
        stringBuilder.append(this.f);
        if (this.l) {
            if (this.g == Long.MIN_VALUE) {
                stringBuilder.append("; max-age=0");
            } else {
                stringBuilder.append("; expires=").append(d.a(new Date(this.g)));
            }
        }
        if (!this.m) {
            stringBuilder.append("; domain=");
            if (z) {
                stringBuilder.append(".");
            }
            stringBuilder.append(this.h);
        }
        stringBuilder.append("; path=").append(this.i);
        if (this.j) {
            stringBuilder.append("; secure");
        }
        if (this.k) {
            stringBuilder.append("; httponly");
        }
        return stringBuilder.toString();
    }

    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof l)) {
            return false;
        }
        l lVar = (l) obj;
        if (lVar.e.equals(this.e) && lVar.f.equals(this.f) && lVar.h.equals(this.h) && lVar.i.equals(this.i) && lVar.g == this.g && lVar.j == this.j && lVar.k == this.k && lVar.l == this.l && lVar.m == this.m) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i;
        int i2 = 0;
        int hashCode = (((((((((this.e.hashCode() + 527) * 31) + this.f.hashCode()) * 31) + this.h.hashCode()) * 31) + this.i.hashCode()) * 31) + ((int) (this.g ^ (this.g >>> 32)))) * 31;
        if (this.j) {
            i = 0;
        } else {
            i = 1;
        }
        hashCode = (i + hashCode) * 31;
        if (this.k) {
            i = 0;
        } else {
            i = 1;
        }
        hashCode = (i + hashCode) * 31;
        if (this.l) {
            i = 0;
        } else {
            i = 1;
        }
        i = (i + hashCode) * 31;
        if (!this.m) {
            i2 = 1;
        }
        return i + i2;
    }
}
