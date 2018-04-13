package okhttp3;

import android.support.v4.internal.view.SupportMenu;
import com.facebook.common.util.UriUtil;
import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import okhttp3.internal.c;

public final class s {
    private static final char[] d = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    final String a;
    final String b;
    final int c;
    private final String e;
    private final String f;
    private final List<String> g;
    private final List<String> h;
    private final String i;
    private final String j;

    public static final class a {
        String a;
        String b = "";
        String c = "";
        String d;
        int e = -1;
        final List<String> f = new ArrayList();
        List<String> g;
        String h;

        enum a {
            a,
            MISSING_SCHEME,
            UNSUPPORTED_SCHEME,
            INVALID_PORT,
            INVALID_HOST
        }

        public a() {
            this.f.add("");
        }

        public a a(String str) {
            if (str == null) {
                throw new NullPointerException("scheme == null");
            }
            if (str.equalsIgnoreCase(UriUtil.HTTP_SCHEME)) {
                this.a = UriUtil.HTTP_SCHEME;
            } else if (str.equalsIgnoreCase("https")) {
                this.a = "https";
            } else {
                throw new IllegalArgumentException("unexpected scheme: " + str);
            }
            return this;
        }

        public a b(String str) {
            if (str == null) {
                throw new NullPointerException("username == null");
            }
            this.b = s.a(str, " \"':;<=>@[]^`{}|/\\?#", false, false, false, true);
            return this;
        }

        public a c(String str) {
            if (str == null) {
                throw new NullPointerException("password == null");
            }
            this.c = s.a(str, " \"':;<=>@[]^`{}|/\\?#", false, false, false, true);
            return this;
        }

        public a d(String str) {
            if (str == null) {
                throw new NullPointerException("host == null");
            }
            String e = e(str, 0, str.length());
            if (e == null) {
                throw new IllegalArgumentException("unexpected host: " + str);
            }
            this.d = e;
            return this;
        }

        public a a(int i) {
            if (i <= 0 || i > SupportMenu.USER_MASK) {
                throw new IllegalArgumentException("unexpected port: " + i);
            }
            this.e = i;
            return this;
        }

        int a() {
            return this.e != -1 ? this.e : s.a(this.a);
        }

        public a e(String str) {
            List b;
            if (str != null) {
                b = s.b(s.a(str, " \"'<>#", true, false, true, true));
            } else {
                b = null;
            }
            this.g = b;
            return this;
        }

        public a a(String str, String str2) {
            if (str == null) {
                throw new NullPointerException("name == null");
            }
            Object a;
            if (this.g == null) {
                this.g = new ArrayList();
            }
            this.g.add(s.a(str, " \"'<>#&=", false, false, true, true));
            List list = this.g;
            if (str2 != null) {
                a = s.a(str2, " \"'<>#&=", false, false, true, true);
            } else {
                a = null;
            }
            list.add(a);
            return this;
        }

        public a b(String str, String str2) {
            if (str == null) {
                throw new NullPointerException("encodedName == null");
            }
            Object a;
            if (this.g == null) {
                this.g = new ArrayList();
            }
            this.g.add(s.a(str, " \"'<>#&=", true, false, true, true));
            List list = this.g;
            if (str2 != null) {
                a = s.a(str2, " \"'<>#&=", true, false, true, true);
            } else {
                a = null;
            }
            list.add(a);
            return this;
        }

        a b() {
            int size = this.f.size();
            for (int i = 0; i < size; i++) {
                this.f.set(i, s.a((String) this.f.get(i), "[]", true, true, false, true));
            }
            if (this.g != null) {
                int size2 = this.g.size();
                for (int i2 = 0; i2 < size2; i2++) {
                    String str = (String) this.g.get(i2);
                    if (str != null) {
                        this.g.set(i2, s.a(str, "\\^`{|}", true, true, true, true));
                    }
                }
            }
            if (this.h != null) {
                this.h = s.a(this.h, " \"#<>\\^`{|}", true, true, false, false);
            }
            return this;
        }

        public s c() {
            if (this.a == null) {
                throw new IllegalStateException("scheme == null");
            } else if (this.d != null) {
                return new s(this);
            } else {
                throw new IllegalStateException("host == null");
            }
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.a);
            stringBuilder.append("://");
            if (!(this.b.isEmpty() && this.c.isEmpty())) {
                stringBuilder.append(this.b);
                if (!this.c.isEmpty()) {
                    stringBuilder.append(':');
                    stringBuilder.append(this.c);
                }
                stringBuilder.append('@');
            }
            if (this.d.indexOf(58) != -1) {
                stringBuilder.append('[');
                stringBuilder.append(this.d);
                stringBuilder.append(']');
            } else {
                stringBuilder.append(this.d);
            }
            int a = a();
            if (a != s.a(this.a)) {
                stringBuilder.append(':');
                stringBuilder.append(a);
            }
            s.a(stringBuilder, this.f);
            if (this.g != null) {
                stringBuilder.append('?');
                s.b(stringBuilder, this.g);
            }
            if (this.h != null) {
                stringBuilder.append('#');
                stringBuilder.append(this.h);
            }
            return stringBuilder.toString();
        }

        a a(s sVar, String str) {
            int d;
            int a = c.a(str, 0, str.length());
            int b = c.b(str, a, str.length());
            if (b(str, a, b) != -1) {
                if (str.regionMatches(true, a, "https:", 0, 6)) {
                    this.a = "https";
                    a += "https:".length();
                } else {
                    if (!str.regionMatches(true, a, "http:", 0, 5)) {
                        return a.UNSUPPORTED_SCHEME;
                    }
                    this.a = UriUtil.HTTP_SCHEME;
                    a += "http:".length();
                }
            } else if (sVar == null) {
                return a.MISSING_SCHEME;
            } else {
                this.a = sVar.a;
            }
            int c = c(str, a, b);
            if (c >= 2 || sVar == null || !sVar.a.equals(this.a)) {
                Object obj = null;
                Object obj2 = null;
                int i = a + c;
                while (true) {
                    Object obj3;
                    Object obj4;
                    int a2 = c.a(str, i, b, "@/\\?#");
                    switch (a2 != b ? str.charAt(a2) : '￿') {
                        case '￿':
                        case '#':
                        case '/':
                        case '?':
                        case '\\':
                            d = d(str, i, a2);
                            if (d + 1 < a2) {
                                this.d = e(str, i, d);
                                this.e = g(str, d + 1, a2);
                                if (this.e == -1) {
                                    return a.INVALID_PORT;
                                }
                            }
                            this.d = e(str, i, d);
                            this.e = s.a(this.a);
                            if (this.d != null) {
                                a = a2;
                                break;
                            }
                            return a.INVALID_HOST;
                        case '@':
                            if (obj == null) {
                                a = c.a(str, i, a2, ':');
                                String a3 = s.a(str, i, a, " \"':;<=>@[]^`{}|/\\?#", true, false, false, true);
                                if (obj2 != null) {
                                    a3 = this.b + "%40" + a3;
                                }
                                this.b = a3;
                                if (a != a2) {
                                    obj = 1;
                                    this.c = s.a(str, a + 1, a2, " \"':;<=>@[]^`{}|/\\?#", true, false, false, true);
                                }
                                obj2 = 1;
                            } else {
                                this.c += "%40" + s.a(str, i, a2, " \"':;<=>@[]^`{}|/\\?#", true, false, false, true);
                            }
                            a = a2 + 1;
                            obj3 = obj;
                            obj4 = obj2;
                            continue;
                        default:
                            obj3 = obj;
                            a = i;
                            obj4 = obj2;
                            continue;
                    }
                    obj = obj3;
                    obj2 = obj4;
                    i = a;
                }
            } else {
                this.b = sVar.d();
                this.c = sVar.e();
                this.d = sVar.b;
                this.e = sVar.c;
                this.f.clear();
                this.f.addAll(sVar.i());
                if (a == b || str.charAt(a) == '#') {
                    e(sVar.k());
                }
            }
            d = c.a(str, a, b, "?#");
            a(str, a, d);
            if (d >= b || str.charAt(d) != '?') {
                a = d;
            } else {
                a = c.a(str, d, b, '#');
                this.g = s.b(s.a(str, d + 1, a, " \"'<>#", true, false, true, true));
            }
            if (a < b && str.charAt(a) == '#') {
                this.h = s.a(str, a + 1, b, "", true, false, false, false);
            }
            return a.a;
        }

        private void a(String str, int i, int i2) {
            if (i != i2) {
                char charAt = str.charAt(i);
                if (charAt == '/' || charAt == '\\') {
                    this.f.clear();
                    this.f.add("");
                    i++;
                } else {
                    this.f.set(this.f.size() - 1, "");
                }
                int i3 = i;
                while (i3 < i2) {
                    int a = c.a(str, i3, i2, "/\\");
                    boolean z = a < i2;
                    a(str, i3, a, z, true);
                    if (z) {
                        a++;
                    }
                    i3 = a;
                }
            }
        }

        private void a(String str, int i, int i2, boolean z, boolean z2) {
            String a = s.a(str, i, i2, " \"<>^`{}|/\\?#", z2, false, false, true);
            if (!f(a)) {
                if (g(a)) {
                    d();
                    return;
                }
                if (((String) this.f.get(this.f.size() - 1)).isEmpty()) {
                    this.f.set(this.f.size() - 1, a);
                } else {
                    this.f.add(a);
                }
                if (z) {
                    this.f.add("");
                }
            }
        }

        private boolean f(String str) {
            return str.equals(".") || str.equalsIgnoreCase("%2e");
        }

        private boolean g(String str) {
            return str.equals("..") || str.equalsIgnoreCase("%2e.") || str.equalsIgnoreCase(".%2e") || str.equalsIgnoreCase("%2e%2e");
        }

        private void d() {
            if (!((String) this.f.remove(this.f.size() - 1)).isEmpty() || this.f.isEmpty()) {
                this.f.add("");
            } else {
                this.f.set(this.f.size() - 1, "");
            }
        }

        private static int b(String str, int i, int i2) {
            if (i2 - i < 2) {
                return -1;
            }
            char charAt = str.charAt(i);
            if ((charAt < 'a' || charAt > 'z') && (charAt < 'A' || charAt > 'Z')) {
                return -1;
            }
            int i3 = i + 1;
            while (i3 < i2) {
                char charAt2 = str.charAt(i3);
                if ((charAt2 >= 'a' && charAt2 <= 'z') || ((charAt2 >= 'A' && charAt2 <= 'Z') || ((charAt2 >= '0' && charAt2 <= '9') || charAt2 == '+' || charAt2 == '-' || charAt2 == '.'))) {
                    i3++;
                } else if (charAt2 == ':') {
                    return i3;
                } else {
                    return -1;
                }
            }
            return -1;
        }

        private static int c(String str, int i, int i2) {
            int i3 = 0;
            while (i < i2) {
                char charAt = str.charAt(i);
                if (charAt != '\\' && charAt != '/') {
                    break;
                }
                i3++;
                i++;
            }
            return i3;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private static int d(java.lang.String r3, int r4, int r5) {
            /*
            r0 = r4;
        L_0x0001:
            if (r0 >= r5) goto L_0x001a;
        L_0x0003:
            r1 = r3.charAt(r0);
            switch(r1) {
                case 58: goto L_0x001b;
                case 91: goto L_0x000d;
                default: goto L_0x000a;
            };
        L_0x000a:
            r0 = r0 + 1;
            goto L_0x0001;
        L_0x000d:
            r0 = r0 + 1;
            if (r0 >= r5) goto L_0x000a;
        L_0x0011:
            r1 = r3.charAt(r0);
            r2 = 93;
            if (r1 != r2) goto L_0x000d;
        L_0x0019:
            goto L_0x000a;
        L_0x001a:
            r0 = r5;
        L_0x001b:
            return r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.s.a.d(java.lang.String, int, int):int");
        }

        private static String e(String str, int i, int i2) {
            String a = s.a(str, i, i2, false);
            if (!a.contains(":")) {
                return c.a(a);
            }
            InetAddress f;
            if (a.startsWith("[") && a.endsWith("]")) {
                f = f(a, 1, a.length() - 1);
            } else {
                f = f(a, 0, a.length());
            }
            if (f == null) {
                return null;
            }
            byte[] address = f.getAddress();
            if (address.length == 16) {
                return a(address);
            }
            throw new AssertionError();
        }

        private static InetAddress f(String str, int i, int i2) {
            byte[] bArr = new byte[16];
            int i3 = i;
            int i4 = -1;
            int i5 = -1;
            int i6 = 0;
            while (i3 < i2) {
                if (i6 == bArr.length) {
                    return null;
                }
                int a;
                if (i3 + 2 > i2 || !str.regionMatches(i3, "::", 0, 2)) {
                    if (i6 != 0) {
                        if (str.regionMatches(i3, ":", 0, 1)) {
                            i3++;
                        } else if (!str.regionMatches(i3, ".", 0, 1)) {
                            return null;
                        } else {
                            if (!a(str, i4, i2, bArr, i6 - 2)) {
                                return null;
                            }
                            i6 += 2;
                        }
                    }
                } else if (i5 != -1) {
                    return null;
                } else {
                    i3 += 2;
                    i5 = i6 + 2;
                    if (i3 == i2) {
                        i6 = i5;
                        break;
                    }
                    i6 = i5;
                }
                i4 = 0;
                int i7 = i3;
                while (i7 < i2) {
                    a = s.a(str.charAt(i7));
                    if (a == -1) {
                        break;
                    }
                    i4 = (i4 << 4) + a;
                    i7++;
                }
                a = i7 - i3;
                if (a == 0 || a > 4) {
                    return null;
                }
                a = i6 + 1;
                bArr[i6] = (byte) ((i4 >>> 8) & 255);
                i6 = a + 1;
                bArr[a] = (byte) (i4 & 255);
                i4 = i3;
                i3 = i7;
            }
            if (i6 != bArr.length) {
                if (i5 == -1) {
                    return null;
                }
                System.arraycopy(bArr, i5, bArr, bArr.length - (i6 - i5), i6 - i5);
                Arrays.fill(bArr, i5, (bArr.length - i6) + i5, (byte) 0);
            }
            try {
                return InetAddress.getByAddress(bArr);
            } catch (UnknownHostException e) {
                throw new AssertionError();
            }
        }

        private static boolean a(String str, int i, int i2, byte[] bArr, int i3) {
            int i4 = i;
            int i5 = i3;
            while (i4 < i2) {
                if (i5 == bArr.length) {
                    return false;
                }
                if (i5 != i3) {
                    if (str.charAt(i4) != '.') {
                        return false;
                    }
                    i4++;
                }
                int i6 = 0;
                int i7 = i4;
                while (i7 < i2) {
                    char charAt = str.charAt(i7);
                    if (charAt < '0' || charAt > '9') {
                        break;
                    } else if (i6 == 0 && i4 != i7) {
                        return false;
                    } else {
                        i6 = ((i6 * 10) + charAt) - 48;
                        if (i6 > 255) {
                            return false;
                        }
                        i7++;
                    }
                }
                if (i7 - i4 == 0) {
                    return false;
                }
                i4 = i5 + 1;
                bArr[i5] = (byte) i6;
                i5 = i4;
                i4 = i7;
            }
            if (i5 != i3 + 4) {
                return false;
            }
            return true;
        }

        private static String a(byte[] bArr) {
            int i = 0;
            int i2 = 0;
            int i3 = -1;
            int i4 = 0;
            while (i4 < bArr.length) {
                int i5 = i4;
                while (i5 < 16 && bArr[i5] == (byte) 0 && bArr[i5 + 1] == (byte) 0) {
                    i5 += 2;
                }
                int i6 = i5 - i4;
                if (i6 > i2) {
                    i2 = i6;
                    i3 = i4;
                }
                i4 = i5 + 2;
            }
            okio.c cVar = new okio.c();
            while (i < bArr.length) {
                if (i == i3) {
                    cVar.b(58);
                    i += i2;
                    if (i == 16) {
                        cVar.b(58);
                    }
                } else {
                    if (i > 0) {
                        cVar.b(58);
                    }
                    cVar.i((long) (((bArr[i] & 255) << 8) | (bArr[i + 1] & 255)));
                    i += 2;
                }
            }
            return cVar.q();
        }

        private static int g(String str, int i, int i2) {
            try {
                int parseInt = Integer.parseInt(s.a(str, i, i2, "", false, false, false, true));
                return (parseInt <= 0 || parseInt > SupportMenu.USER_MASK) ? -1 : parseInt;
            } catch (NumberFormatException e) {
                return -1;
            }
        }
    }

    s(a aVar) {
        String str = null;
        this.a = aVar.a;
        this.e = a(aVar.b, false);
        this.f = a(aVar.c, false);
        this.b = aVar.d;
        this.c = aVar.a();
        this.g = a(aVar.f, false);
        this.h = aVar.g != null ? a(aVar.g, true) : null;
        if (aVar.h != null) {
            str = a(aVar.h, false);
        }
        this.i = str;
        this.j = aVar.toString();
    }

    public URI a() {
        String aVar = o().b().toString();
        try {
            return new URI(aVar);
        } catch (Throwable e) {
            try {
                return URI.create(aVar.replaceAll("[\\u0000-\\u001F\\u007F-\\u009F\\p{javaWhitespace}]", ""));
            } catch (Exception e2) {
                throw new RuntimeException(e);
            }
        }
    }

    public String b() {
        return this.a;
    }

    public boolean c() {
        return this.a.equals("https");
    }

    public String d() {
        if (this.e.isEmpty()) {
            return "";
        }
        int length = this.a.length() + 3;
        return this.j.substring(length, c.a(this.j, length, this.j.length(), ":@"));
    }

    public String e() {
        if (this.f.isEmpty()) {
            return "";
        }
        return this.j.substring(this.j.indexOf(58, this.a.length() + 3) + 1, this.j.indexOf(64));
    }

    public String f() {
        return this.b;
    }

    public int g() {
        return this.c;
    }

    public static int a(String str) {
        if (str.equals(UriUtil.HTTP_SCHEME)) {
            return 80;
        }
        if (str.equals("https")) {
            return 443;
        }
        return -1;
    }

    public String h() {
        int indexOf = this.j.indexOf(47, this.a.length() + 3);
        return this.j.substring(indexOf, c.a(this.j, indexOf, this.j.length(), "?#"));
    }

    static void a(StringBuilder stringBuilder, List<String> list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            stringBuilder.append('/');
            stringBuilder.append((String) list.get(i));
        }
    }

    public List<String> i() {
        int indexOf = this.j.indexOf(47, this.a.length() + 3);
        int a = c.a(this.j, indexOf, this.j.length(), "?#");
        List<String> arrayList = new ArrayList();
        while (indexOf < a) {
            int i = indexOf + 1;
            indexOf = c.a(this.j, i, a, '/');
            arrayList.add(this.j.substring(i, indexOf));
        }
        return arrayList;
    }

    public List<String> j() {
        return this.g;
    }

    public String k() {
        if (this.h == null) {
            return null;
        }
        int indexOf = this.j.indexOf(63) + 1;
        return this.j.substring(indexOf, c.a(this.j, indexOf + 1, this.j.length(), '#'));
    }

    static void b(StringBuilder stringBuilder, List<String> list) {
        int size = list.size();
        for (int i = 0; i < size; i += 2) {
            String str = (String) list.get(i);
            String str2 = (String) list.get(i + 1);
            if (i > 0) {
                stringBuilder.append('&');
            }
            stringBuilder.append(str);
            if (str2 != null) {
                stringBuilder.append('=');
                stringBuilder.append(str2);
            }
        }
    }

    static List<String> b(String str) {
        List<String> arrayList = new ArrayList();
        int i = 0;
        while (i <= str.length()) {
            int indexOf = str.indexOf(38, i);
            if (indexOf == -1) {
                indexOf = str.length();
            }
            int indexOf2 = str.indexOf(61, i);
            if (indexOf2 == -1 || indexOf2 > indexOf) {
                arrayList.add(str.substring(i, indexOf));
                arrayList.add(null);
            } else {
                arrayList.add(str.substring(i, indexOf2));
                arrayList.add(str.substring(indexOf2 + 1, indexOf));
            }
            i = indexOf + 1;
        }
        return arrayList;
    }

    public String l() {
        if (this.h == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        b(stringBuilder, this.h);
        return stringBuilder.toString();
    }

    public String m() {
        if (this.i == null) {
            return null;
        }
        return this.j.substring(this.j.indexOf(35) + 1);
    }

    public String n() {
        return d("/...").b("").c("").c().toString();
    }

    public s c(String str) {
        a d = d(str);
        return d != null ? d.c() : null;
    }

    public a o() {
        a aVar = new a();
        aVar.a = this.a;
        aVar.b = d();
        aVar.c = e();
        aVar.d = this.b;
        aVar.e = this.c != a(this.a) ? this.c : -1;
        aVar.f.clear();
        aVar.f.addAll(i());
        aVar.e(k());
        aVar.h = m();
        return aVar;
    }

    public a d(String str) {
        a aVar = new a();
        return aVar.a(this, str) == a.a ? aVar : null;
    }

    public static s e(String str) {
        a aVar = new a();
        if (aVar.a(null, str) == a.a) {
            return aVar.c();
        }
        return null;
    }

    public boolean equals(Object obj) {
        return (obj instanceof s) && ((s) obj).j.equals(this.j);
    }

    public int hashCode() {
        return this.j.hashCode();
    }

    public String toString() {
        return this.j;
    }

    static String a(String str, boolean z) {
        return a(str, 0, str.length(), z);
    }

    private List<String> a(List<String> list, boolean z) {
        int size = list.size();
        List arrayList = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            String str = (String) list.get(i);
            arrayList.add(str != null ? a(str, z) : null);
        }
        return Collections.unmodifiableList(arrayList);
    }

    static String a(String str, int i, int i2, boolean z) {
        for (int i3 = i; i3 < i2; i3++) {
            char charAt = str.charAt(i3);
            if (charAt == '%' || (charAt == '+' && z)) {
                okio.c cVar = new okio.c();
                cVar.a(str, i, i3);
                a(cVar, str, i3, i2, z);
                return cVar.q();
            }
        }
        return str.substring(i, i2);
    }

    static void a(okio.c cVar, String str, int i, int i2, boolean z) {
        int i3 = i;
        while (i3 < i2) {
            int codePointAt = str.codePointAt(i3);
            if (codePointAt != 37 || i3 + 2 >= i2) {
                if (codePointAt == 43 && z) {
                    cVar.b(32);
                }
                cVar.a(codePointAt);
            } else {
                int a = a(str.charAt(i3 + 1));
                int a2 = a(str.charAt(i3 + 2));
                if (!(a == -1 || a2 == -1)) {
                    cVar.b((a << 4) + a2);
                    i3 += 2;
                }
                cVar.a(codePointAt);
            }
            i3 += Character.charCount(codePointAt);
        }
    }

    static boolean a(String str, int i, int i2) {
        return i + 2 < i2 && str.charAt(i) == '%' && a(str.charAt(i + 1)) != -1 && a(str.charAt(i + 2)) != -1;
    }

    static int a(char c) {
        if (c >= '0' && c <= '9') {
            return c - 48;
        }
        if (c >= 'a' && c <= 'f') {
            return (c - 97) + 10;
        }
        if (c < 'A' || c > 'F') {
            return -1;
        }
        return (c - 65) + 10;
    }

    static String a(String str, int i, int i2, String str2, boolean z, boolean z2, boolean z3, boolean z4) {
        int i3 = i;
        while (i3 < i2) {
            int codePointAt = str.codePointAt(i3);
            if (codePointAt < 32 || codePointAt == 127 || ((codePointAt >= 128 && z4) || str2.indexOf(codePointAt) != -1 || ((codePointAt == 37 && (!z || (z2 && !a(str, i3, i2)))) || (codePointAt == 43 && z3)))) {
                okio.c cVar = new okio.c();
                cVar.a(str, i, i3);
                a(cVar, str, i3, i2, str2, z, z2, z3, z4);
                return cVar.q();
            }
            i3 += Character.charCount(codePointAt);
        }
        return str.substring(i, i2);
    }

    static void a(okio.c cVar, String str, int i, int i2, String str2, boolean z, boolean z2, boolean z3, boolean z4) {
        okio.c cVar2 = null;
        while (i < i2) {
            int codePointAt = str.codePointAt(i);
            if (!(z && (codePointAt == 9 || codePointAt == 10 || codePointAt == 12 || codePointAt == 13))) {
                if (codePointAt == 43 && z3) {
                    cVar.a(z ? "+" : "%2B");
                } else if (codePointAt < 32 || codePointAt == 127 || ((codePointAt >= 128 && z4) || str2.indexOf(codePointAt) != -1 || (codePointAt == 37 && (!z || (z2 && !a(str, i, i2)))))) {
                    if (cVar2 == null) {
                        cVar2 = new okio.c();
                    }
                    cVar2.a(codePointAt);
                    while (!cVar2.f()) {
                        int i3 = cVar2.i() & 255;
                        cVar.b(37);
                        cVar.b(d[(i3 >> 4) & 15]);
                        cVar.b(d[i3 & 15]);
                    }
                } else {
                    cVar.a(codePointAt);
                }
            }
            i += Character.charCount(codePointAt);
        }
    }

    static String a(String str, String str2, boolean z, boolean z2, boolean z3, boolean z4) {
        return a(str, 0, str.length(), str2, z, z2, z3, z4);
    }
}
