package okhttp3;

import cz.msebera.android.httpclient.cookie.ClientCookie;
import cz.msebera.android.httpclient.cookie.SM;
import cz.msebera.android.httpclient.message.TokenParser;
import io.agora.rtc.Constants;
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
import okhttp3.internal.Util;
import okhttp3.internal.http.HttpDate;
import okhttp3.internal.publicsuffix.PublicSuffixDatabase;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public final class Cookie {
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

    private Cookie(String str, String str2, long j, String str3, String str4, boolean z, boolean z2, boolean z3, boolean z4) {
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

    Cookie(Cookie$Builder cookie$Builder) {
        if (cookie$Builder.a == null) {
            throw new NullPointerException("builder.name == null");
        } else if (cookie$Builder.b == null) {
            throw new NullPointerException("builder.value == null");
        } else if (cookie$Builder.d == null) {
            throw new NullPointerException("builder.domain == null");
        } else {
            this.e = cookie$Builder.a;
            this.f = cookie$Builder.b;
            this.g = cookie$Builder.c;
            this.h = cookie$Builder.d;
            this.i = cookie$Builder.e;
            this.j = cookie$Builder.f;
            this.k = cookie$Builder.g;
            this.l = cookie$Builder.h;
            this.m = cookie$Builder.i;
        }
    }

    public String name() {
        return this.e;
    }

    public String value() {
        return this.f;
    }

    public boolean persistent() {
        return this.l;
    }

    public long expiresAt() {
        return this.g;
    }

    public boolean hostOnly() {
        return this.m;
    }

    public String domain() {
        return this.h;
    }

    public String path() {
        return this.i;
    }

    public boolean httpOnly() {
        return this.k;
    }

    public boolean secure() {
        return this.j;
    }

    public boolean matches(HttpUrl httpUrl) {
        boolean equals;
        if (this.m) {
            equals = httpUrl.host().equals(this.h);
        } else {
            equals = a(httpUrl.host(), this.h);
        }
        if (!equals || !a(httpUrl, this.i)) {
            return false;
        }
        if (!this.j || httpUrl.isHttps()) {
            return true;
        }
        return false;
    }

    private static boolean a(String str, String str2) {
        if (str.equals(str2)) {
            return true;
        }
        if (str.endsWith(str2) && str.charAt((str.length() - str2.length()) - 1) == '.' && !Util.verifyAsIpAddress(str)) {
            return true;
        }
        return false;
    }

    private static boolean a(HttpUrl httpUrl, String str) {
        String encodedPath = httpUrl.encodedPath();
        if (encodedPath.equals(str)) {
            return true;
        }
        if (encodedPath.startsWith(str) && (str.endsWith(MqttTopic.TOPIC_LEVEL_SEPARATOR) || encodedPath.charAt(str.length()) == '/')) {
            return true;
        }
        return false;
    }

    @Nullable
    public static Cookie parse(HttpUrl httpUrl, String str) {
        return a(System.currentTimeMillis(), httpUrl, str);
    }

    @Nullable
    static Cookie a(long j, HttpUrl httpUrl, String str) {
        int length = str.length();
        int delimiterOffset = Util.delimiterOffset(str, 0, length, ';');
        int delimiterOffset2 = Util.delimiterOffset(str, 0, delimiterOffset, '=');
        if (delimiterOffset2 == delimiterOffset) {
            return null;
        }
        String trimSubstring = Util.trimSubstring(str, 0, delimiterOffset2);
        if (trimSubstring.isEmpty() || Util.indexOfControlOrNonAscii(trimSubstring) != -1) {
            return null;
        }
        String trimSubstring2 = Util.trimSubstring(str, delimiterOffset2 + 1, delimiterOffset);
        if (Util.indexOfControlOrNonAscii(trimSubstring2) != -1) {
            return null;
        }
        String trimSubstring3;
        long j2 = HttpDate.MAX_DATE;
        long j3 = -1;
        String str2 = null;
        String str3 = null;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = true;
        boolean z4 = false;
        delimiterOffset++;
        while (delimiterOffset < length) {
            long j4;
            int delimiterOffset3 = Util.delimiterOffset(str, delimiterOffset, length, ';');
            int delimiterOffset4 = Util.delimiterOffset(str, delimiterOffset, delimiterOffset3, '=');
            String trimSubstring4 = Util.trimSubstring(str, delimiterOffset, delimiterOffset4);
            if (delimiterOffset4 < delimiterOffset3) {
                trimSubstring3 = Util.trimSubstring(str, delimiterOffset4 + 1, delimiterOffset3);
            } else {
                trimSubstring3 = "";
            }
            if (trimSubstring4.equalsIgnoreCase("expires")) {
                try {
                    j2 = a(trimSubstring3, 0, trimSubstring3.length());
                    z4 = true;
                    trimSubstring3 = str2;
                    j4 = j2;
                } catch (IllegalArgumentException e) {
                    trimSubstring3 = str2;
                    j4 = j2;
                }
            } else {
                if (trimSubstring4.equalsIgnoreCase("max-age")) {
                    try {
                        j3 = a(trimSubstring3);
                        z4 = true;
                        trimSubstring3 = str2;
                        j4 = j2;
                    } catch (NumberFormatException e2) {
                        trimSubstring3 = str2;
                        j4 = j2;
                    }
                } else {
                    if (trimSubstring4.equalsIgnoreCase(ClientCookie.DOMAIN_ATTR)) {
                        try {
                            trimSubstring3 = b(trimSubstring3);
                            z3 = false;
                            j4 = j2;
                        } catch (IllegalArgumentException e3) {
                            trimSubstring3 = str2;
                            j4 = j2;
                        }
                    } else {
                        if (trimSubstring4.equalsIgnoreCase("path")) {
                            str3 = trimSubstring3;
                            trimSubstring3 = str2;
                            j4 = j2;
                        } else {
                            if (trimSubstring4.equalsIgnoreCase(ClientCookie.SECURE_ATTR)) {
                                z = true;
                                trimSubstring3 = str2;
                                j4 = j2;
                            } else {
                                if (trimSubstring4.equalsIgnoreCase("httponly")) {
                                    z2 = true;
                                    trimSubstring3 = str2;
                                    j4 = j2;
                                } else {
                                    trimSubstring3 = str2;
                                    j4 = j2;
                                }
                            }
                        }
                    }
                }
            }
            String str4 = trimSubstring3;
            delimiterOffset = delimiterOffset3 + 1;
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
        trimSubstring3 = httpUrl.host();
        if (str2 == null) {
            str2 = trimSubstring3;
        } else if (!a(trimSubstring3, str2)) {
            return null;
        }
        if (trimSubstring3.length() != str2.length() && PublicSuffixDatabase.get().getEffectiveTldPlusOne(str2) == null) {
            return null;
        }
        String substring;
        if (str3 == null || !str3.startsWith(MqttTopic.TOPIC_LEVEL_SEPARATOR)) {
            str3 = httpUrl.encodedPath();
            delimiterOffset = str3.lastIndexOf(47);
            substring = delimiterOffset != 0 ? str3.substring(0, delimiterOffset) : MqttTopic.TOPIC_LEVEL_SEPARATOR;
        } else {
            substring = str3;
        }
        return new Cookie(trimSubstring, trimSubstring2, j3, str2, substring, z, z2, z3, z4);
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
        if (i8 < Constants.ERR_VCM_ENCODER_INIT_ERROR) {
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
            Calendar gregorianCalendar = new GregorianCalendar(Util.UTC);
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
            Object obj2 = ((charAt >= TokenParser.SP || charAt == '\t') && charAt < '' && ((charAt < '0' || charAt > '9') && ((charAt < 'a' || charAt > 'z') && ((charAt < 'A' || charAt > 'Z') && charAt != ':')))) ? null : 1;
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
            } else if (str.startsWith(com.xiaomi.mipush.sdk.Constants.ACCEPT_TIME_SEPARATOR_SERVER)) {
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
        String canonicalizeHost = Util.canonicalizeHost(str);
        if (canonicalizeHost != null) {
            return canonicalizeHost;
        }
        throw new IllegalArgumentException();
    }

    public static List<Cookie> parseAll(HttpUrl httpUrl, Headers headers) {
        List values = headers.values(SM.SET_COOKIE);
        List list = null;
        int size = values.size();
        for (int i = 0; i < size; i++) {
            Cookie parse = parse(httpUrl, (String) values.get(i));
            if (parse != null) {
                List arrayList;
                if (list == null) {
                    arrayList = new ArrayList();
                } else {
                    arrayList = list;
                }
                arrayList.add(parse);
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
                stringBuilder.append("; expires=").append(HttpDate.format(new Date(this.g)));
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
        if (!(obj instanceof Cookie)) {
            return false;
        }
        Cookie cookie = (Cookie) obj;
        if (cookie.e.equals(this.e) && cookie.f.equals(this.f) && cookie.h.equals(this.h) && cookie.i.equals(this.i) && cookie.g == this.g && cookie.j == this.j && cookie.k == this.k && cookie.l == this.l && cookie.m == this.m) {
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
