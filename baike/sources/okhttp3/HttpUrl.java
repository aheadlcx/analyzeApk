package okhttp3;

import android.support.v4.internal.view.SupportMenu;
import cz.msebera.android.httpclient.message.TokenParser;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;
import kotlin.text.Typography;
import okhttp3.internal.Util;
import okhttp3.internal.publicsuffix.PublicSuffixDatabase;
import okio.Buffer;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public final class HttpUrl {
    private static final char[] d = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    final String a;
    final String b;
    final int c;
    private final String e;
    private final String f;
    private final List<String> g;
    @Nullable
    private final List<String> h;
    @Nullable
    private final String i;
    private final String j;

    public static final class Builder {
        @Nullable
        String a;
        String b = "";
        String c = "";
        @Nullable
        String d;
        int e = -1;
        final List<String> f = new ArrayList();
        @Nullable
        List<String> g;
        @Nullable
        String h;

        public Builder() {
            this.f.add("");
        }

        public Builder scheme(String str) {
            if (str == null) {
                throw new NullPointerException("scheme == null");
            }
            if (str.equalsIgnoreCase("http")) {
                this.a = "http";
            } else if (str.equalsIgnoreCase("https")) {
                this.a = "https";
            } else {
                throw new IllegalArgumentException("unexpected scheme: " + str);
            }
            return this;
        }

        public Builder username(String str) {
            if (str == null) {
                throw new NullPointerException("username == null");
            }
            this.b = HttpUrl.a(str, " \"':;<=>@[]^`{}|/\\?#", false, false, false, true);
            return this;
        }

        public Builder encodedUsername(String str) {
            if (str == null) {
                throw new NullPointerException("encodedUsername == null");
            }
            this.b = HttpUrl.a(str, " \"':;<=>@[]^`{}|/\\?#", true, false, false, true);
            return this;
        }

        public Builder password(String str) {
            if (str == null) {
                throw new NullPointerException("password == null");
            }
            this.c = HttpUrl.a(str, " \"':;<=>@[]^`{}|/\\?#", false, false, false, true);
            return this;
        }

        public Builder encodedPassword(String str) {
            if (str == null) {
                throw new NullPointerException("encodedPassword == null");
            }
            this.c = HttpUrl.a(str, " \"':;<=>@[]^`{}|/\\?#", true, false, false, true);
            return this;
        }

        public Builder host(String str) {
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

        public Builder port(int i) {
            if (i <= 0 || i > SupportMenu.USER_MASK) {
                throw new IllegalArgumentException("unexpected port: " + i);
            }
            this.e = i;
            return this;
        }

        int a() {
            return this.e != -1 ? this.e : HttpUrl.defaultPort(this.a);
        }

        public Builder addPathSegment(String str) {
            if (str == null) {
                throw new NullPointerException("pathSegment == null");
            }
            a(str, 0, str.length(), false, false);
            return this;
        }

        public Builder addPathSegments(String str) {
            if (str != null) {
                return a(str, false);
            }
            throw new NullPointerException("pathSegments == null");
        }

        public Builder addEncodedPathSegment(String str) {
            if (str == null) {
                throw new NullPointerException("encodedPathSegment == null");
            }
            a(str, 0, str.length(), false, true);
            return this;
        }

        public Builder addEncodedPathSegments(String str) {
            if (str != null) {
                return a(str, true);
            }
            throw new NullPointerException("encodedPathSegments == null");
        }

        private Builder a(String str, boolean z) {
            int i = 0;
            do {
                boolean z2;
                int delimiterOffset = Util.delimiterOffset(str, i, str.length(), "/\\");
                if (delimiterOffset < str.length()) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                a(str, i, delimiterOffset, z2, z);
                i = delimiterOffset + 1;
            } while (i <= str.length());
            return this;
        }

        public Builder setPathSegment(int i, String str) {
            if (str == null) {
                throw new NullPointerException("pathSegment == null");
            }
            String a = HttpUrl.a(str, 0, str.length(), " \"<>^`{}|/\\?#", false, false, false, true, null);
            if (b(a) || c(a)) {
                throw new IllegalArgumentException("unexpected path segment: " + str);
            }
            this.f.set(i, a);
            return this;
        }

        public Builder setEncodedPathSegment(int i, String str) {
            if (str == null) {
                throw new NullPointerException("encodedPathSegment == null");
            }
            String a = HttpUrl.a(str, 0, str.length(), " \"<>^`{}|/\\?#", true, false, false, true, null);
            this.f.set(i, a);
            if (!b(a) && !c(a)) {
                return this;
            }
            throw new IllegalArgumentException("unexpected path segment: " + str);
        }

        public Builder removePathSegment(int i) {
            this.f.remove(i);
            if (this.f.isEmpty()) {
                this.f.add("");
            }
            return this;
        }

        public Builder encodedPath(String str) {
            if (str == null) {
                throw new NullPointerException("encodedPath == null");
            } else if (str.startsWith(MqttTopic.TOPIC_LEVEL_SEPARATOR)) {
                a(str, 0, str.length());
                return this;
            } else {
                throw new IllegalArgumentException("unexpected encodedPath: " + str);
            }
        }

        public Builder query(@Nullable String str) {
            List a;
            if (str != null) {
                a = HttpUrl.a(HttpUrl.a(str, " \"'<>#", false, false, true, true));
            } else {
                a = null;
            }
            this.g = a;
            return this;
        }

        public Builder encodedQuery(@Nullable String str) {
            List a;
            if (str != null) {
                a = HttpUrl.a(HttpUrl.a(str, " \"'<>#", true, false, true, true));
            } else {
                a = null;
            }
            this.g = a;
            return this;
        }

        public Builder addQueryParameter(String str, @Nullable String str2) {
            if (str == null) {
                throw new NullPointerException("name == null");
            }
            Object a;
            if (this.g == null) {
                this.g = new ArrayList();
            }
            this.g.add(HttpUrl.a(str, " \"'<>#&=", false, false, true, true));
            List list = this.g;
            if (str2 != null) {
                a = HttpUrl.a(str2, " \"'<>#&=", false, false, true, true);
            } else {
                a = null;
            }
            list.add(a);
            return this;
        }

        public Builder addEncodedQueryParameter(String str, @Nullable String str2) {
            if (str == null) {
                throw new NullPointerException("encodedName == null");
            }
            Object a;
            if (this.g == null) {
                this.g = new ArrayList();
            }
            this.g.add(HttpUrl.a(str, " \"'<>#&=", true, false, true, true));
            List list = this.g;
            if (str2 != null) {
                a = HttpUrl.a(str2, " \"'<>#&=", true, false, true, true);
            } else {
                a = null;
            }
            list.add(a);
            return this;
        }

        public Builder setQueryParameter(String str, @Nullable String str2) {
            removeAllQueryParameters(str);
            addQueryParameter(str, str2);
            return this;
        }

        public Builder setEncodedQueryParameter(String str, @Nullable String str2) {
            removeAllEncodedQueryParameters(str);
            addEncodedQueryParameter(str, str2);
            return this;
        }

        public Builder removeAllQueryParameters(String str) {
            if (str == null) {
                throw new NullPointerException("name == null");
            }
            if (this.g != null) {
                a(HttpUrl.a(str, " \"'<>#&=", false, false, true, true));
            }
            return this;
        }

        public Builder removeAllEncodedQueryParameters(String str) {
            if (str == null) {
                throw new NullPointerException("encodedName == null");
            }
            if (this.g != null) {
                a(HttpUrl.a(str, " \"'<>#&=", true, false, true, true));
            }
            return this;
        }

        private void a(String str) {
            for (int size = this.g.size() - 2; size >= 0; size -= 2) {
                if (str.equals(this.g.get(size))) {
                    this.g.remove(size + 1);
                    this.g.remove(size);
                    if (this.g.isEmpty()) {
                        this.g = null;
                        return;
                    }
                }
            }
        }

        public Builder fragment(@Nullable String str) {
            String a;
            if (str != null) {
                a = HttpUrl.a(str, "", false, false, false, false);
            } else {
                a = null;
            }
            this.h = a;
            return this;
        }

        public Builder encodedFragment(@Nullable String str) {
            String a;
            if (str != null) {
                a = HttpUrl.a(str, "", true, false, false, false);
            } else {
                a = null;
            }
            this.h = a;
            return this;
        }

        Builder b() {
            int size = this.f.size();
            for (int i = 0; i < size; i++) {
                this.f.set(i, HttpUrl.a((String) this.f.get(i), "[]", true, true, false, true));
            }
            if (this.g != null) {
                int size2 = this.g.size();
                for (int i2 = 0; i2 < size2; i2++) {
                    String str = (String) this.g.get(i2);
                    if (str != null) {
                        this.g.set(i2, HttpUrl.a(str, "\\^`{|}", true, true, true, true));
                    }
                }
            }
            if (this.h != null) {
                this.h = HttpUrl.a(this.h, " \"#<>\\^`{|}", true, true, false, false);
            }
            return this;
        }

        public HttpUrl build() {
            if (this.a == null) {
                throw new IllegalStateException("scheme == null");
            } else if (this.d != null) {
                return new HttpUrl(this);
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
            if (a != HttpUrl.defaultPort(this.a)) {
                stringBuilder.append(':');
                stringBuilder.append(a);
            }
            HttpUrl.a(stringBuilder, this.f);
            if (this.g != null) {
                stringBuilder.append('?');
                HttpUrl.b(stringBuilder, this.g);
            }
            if (this.h != null) {
                stringBuilder.append('#');
                stringBuilder.append(this.h);
            }
            return stringBuilder.toString();
        }

        HttpUrl$Builder$a a(@Nullable HttpUrl httpUrl, String str) {
            int d;
            int skipLeadingAsciiWhitespace = Util.skipLeadingAsciiWhitespace(str, 0, str.length());
            int skipTrailingAsciiWhitespace = Util.skipTrailingAsciiWhitespace(str, skipLeadingAsciiWhitespace, str.length());
            if (b(str, skipLeadingAsciiWhitespace, skipTrailingAsciiWhitespace) != -1) {
                if (str.regionMatches(true, skipLeadingAsciiWhitespace, "https:", 0, 6)) {
                    this.a = "https";
                    skipLeadingAsciiWhitespace += "https:".length();
                } else {
                    if (!str.regionMatches(true, skipLeadingAsciiWhitespace, "http:", 0, 5)) {
                        return HttpUrl$Builder$a.UNSUPPORTED_SCHEME;
                    }
                    this.a = "http";
                    skipLeadingAsciiWhitespace += "http:".length();
                }
            } else if (httpUrl == null) {
                return HttpUrl$Builder$a.MISSING_SCHEME;
            } else {
                this.a = httpUrl.a;
            }
            int c = c(str, skipLeadingAsciiWhitespace, skipTrailingAsciiWhitespace);
            if (c >= 2 || httpUrl == null || !httpUrl.a.equals(this.a)) {
                Object obj = null;
                Object obj2 = null;
                int i = skipLeadingAsciiWhitespace + c;
                while (true) {
                    char charAt;
                    Object obj3;
                    Object obj4;
                    int delimiterOffset = Util.delimiterOffset(str, i, skipTrailingAsciiWhitespace, "@/\\?#");
                    if (delimiterOffset != skipTrailingAsciiWhitespace) {
                        charAt = str.charAt(delimiterOffset);
                    } else {
                        charAt = '￿';
                    }
                    switch (charAt) {
                        case '￿':
                        case '#':
                        case '/':
                        case '?':
                        case '\\':
                            d = d(str, i, delimiterOffset);
                            if (d + 1 < delimiterOffset) {
                                this.d = e(str, i, d);
                                this.e = f(str, d + 1, delimiterOffset);
                                if (this.e == -1) {
                                    return HttpUrl$Builder$a.INVALID_PORT;
                                }
                            }
                            this.d = e(str, i, d);
                            this.e = HttpUrl.defaultPort(this.a);
                            if (this.d != null) {
                                skipLeadingAsciiWhitespace = delimiterOffset;
                                break;
                            }
                            return HttpUrl$Builder$a.INVALID_HOST;
                        case '@':
                            if (obj == null) {
                                skipLeadingAsciiWhitespace = Util.delimiterOffset(str, i, delimiterOffset, ':');
                                String a = HttpUrl.a(str, i, skipLeadingAsciiWhitespace, " \"':;<=>@[]^`{}|/\\?#", true, false, false, true, null);
                                if (obj2 != null) {
                                    a = this.b + "%40" + a;
                                }
                                this.b = a;
                                if (skipLeadingAsciiWhitespace != delimiterOffset) {
                                    obj = 1;
                                    this.c = HttpUrl.a(str, skipLeadingAsciiWhitespace + 1, delimiterOffset, " \"':;<=>@[]^`{}|/\\?#", true, false, false, true, null);
                                }
                                obj2 = 1;
                            } else {
                                this.c += "%40" + HttpUrl.a(str, i, delimiterOffset, " \"':;<=>@[]^`{}|/\\?#", true, false, false, true, null);
                            }
                            skipLeadingAsciiWhitespace = delimiterOffset + 1;
                            obj3 = obj;
                            obj4 = obj2;
                            continue;
                        default:
                            obj3 = obj;
                            skipLeadingAsciiWhitespace = i;
                            obj4 = obj2;
                            continue;
                    }
                    obj = obj3;
                    obj2 = obj4;
                    i = skipLeadingAsciiWhitespace;
                }
            } else {
                this.b = httpUrl.encodedUsername();
                this.c = httpUrl.encodedPassword();
                this.d = httpUrl.b;
                this.e = httpUrl.c;
                this.f.clear();
                this.f.addAll(httpUrl.encodedPathSegments());
                if (skipLeadingAsciiWhitespace == skipTrailingAsciiWhitespace || str.charAt(skipLeadingAsciiWhitespace) == '#') {
                    encodedQuery(httpUrl.encodedQuery());
                }
            }
            d = Util.delimiterOffset(str, skipLeadingAsciiWhitespace, skipTrailingAsciiWhitespace, "?#");
            a(str, skipLeadingAsciiWhitespace, d);
            if (d >= skipTrailingAsciiWhitespace || str.charAt(d) != '?') {
                skipLeadingAsciiWhitespace = d;
            } else {
                skipLeadingAsciiWhitespace = Util.delimiterOffset(str, d, skipTrailingAsciiWhitespace, '#');
                this.g = HttpUrl.a(HttpUrl.a(str, d + 1, skipLeadingAsciiWhitespace, " \"'<>#", true, false, true, true, null));
            }
            if (skipLeadingAsciiWhitespace < skipTrailingAsciiWhitespace && str.charAt(skipLeadingAsciiWhitespace) == '#') {
                this.h = HttpUrl.a(str, skipLeadingAsciiWhitespace + 1, skipTrailingAsciiWhitespace, "", true, false, false, false, null);
            }
            return HttpUrl$Builder$a.SUCCESS;
        }

        private void a(String str, int i, int i2) {
            if (i != i2) {
                char charAt = str.charAt(i);
                if (charAt == '/' || charAt == TokenParser.ESCAPE) {
                    this.f.clear();
                    this.f.add("");
                    i++;
                } else {
                    this.f.set(this.f.size() - 1, "");
                }
                int i3 = i;
                while (i3 < i2) {
                    int delimiterOffset = Util.delimiterOffset(str, i3, i2, "/\\");
                    boolean z = delimiterOffset < i2;
                    a(str, i3, delimiterOffset, z, true);
                    if (z) {
                        delimiterOffset++;
                    }
                    i3 = delimiterOffset;
                }
            }
        }

        private void a(String str, int i, int i2, boolean z, boolean z2) {
            String a = HttpUrl.a(str, i, i2, " \"<>^`{}|/\\?#", z2, false, false, true, null);
            if (!b(a)) {
                if (c(a)) {
                    c();
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

        private boolean b(String str) {
            return str.equals(".") || str.equalsIgnoreCase("%2e");
        }

        private boolean c(String str) {
            return str.equals("..") || str.equalsIgnoreCase("%2e.") || str.equalsIgnoreCase(".%2e") || str.equalsIgnoreCase("%2e%2e");
        }

        private void c() {
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
                if (charAt != TokenParser.ESCAPE && charAt != '/') {
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
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.HttpUrl.Builder.d(java.lang.String, int, int):int");
        }

        private static String e(String str, int i, int i2) {
            return Util.canonicalizeHost(HttpUrl.a(str, i, i2, false));
        }

        private static int f(String str, int i, int i2) {
            try {
                int parseInt = Integer.parseInt(HttpUrl.a(str, i, i2, "", false, false, false, true, null));
                return (parseInt <= 0 || parseInt > SupportMenu.USER_MASK) ? -1 : parseInt;
            } catch (NumberFormatException e) {
                return -1;
            }
        }
    }

    HttpUrl(Builder builder) {
        List a;
        String str = null;
        this.a = builder.a;
        this.e = a(builder.b, false);
        this.f = a(builder.c, false);
        this.b = builder.d;
        this.c = builder.a();
        this.g = a(builder.f, false);
        if (builder.g != null) {
            a = a(builder.g, true);
        } else {
            a = null;
        }
        this.h = a;
        if (builder.h != null) {
            str = a(builder.h, false);
        }
        this.i = str;
        this.j = builder.toString();
    }

    public URL url() {
        try {
            return new URL(this.j);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public URI uri() {
        String builder = newBuilder().b().toString();
        try {
            return new URI(builder);
        } catch (Throwable e) {
            try {
                return URI.create(builder.replaceAll("[\\u0000-\\u001F\\u007F-\\u009F\\p{javaWhitespace}]", ""));
            } catch (Exception e2) {
                throw new RuntimeException(e);
            }
        }
    }

    public String scheme() {
        return this.a;
    }

    public boolean isHttps() {
        return this.a.equals("https");
    }

    public String encodedUsername() {
        if (this.e.isEmpty()) {
            return "";
        }
        int length = this.a.length() + 3;
        return this.j.substring(length, Util.delimiterOffset(this.j, length, this.j.length(), ":@"));
    }

    public String username() {
        return this.e;
    }

    public String encodedPassword() {
        if (this.f.isEmpty()) {
            return "";
        }
        return this.j.substring(this.j.indexOf(58, this.a.length() + 3) + 1, this.j.indexOf(64));
    }

    public String password() {
        return this.f;
    }

    public String host() {
        return this.b;
    }

    public int port() {
        return this.c;
    }

    public static int defaultPort(String str) {
        if (str.equals("http")) {
            return 80;
        }
        if (str.equals("https")) {
            return 443;
        }
        return -1;
    }

    public int pathSize() {
        return this.g.size();
    }

    public String encodedPath() {
        int indexOf = this.j.indexOf(47, this.a.length() + 3);
        return this.j.substring(indexOf, Util.delimiterOffset(this.j, indexOf, this.j.length(), "?#"));
    }

    static void a(StringBuilder stringBuilder, List<String> list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            stringBuilder.append('/');
            stringBuilder.append((String) list.get(i));
        }
    }

    public List<String> encodedPathSegments() {
        int indexOf = this.j.indexOf(47, this.a.length() + 3);
        int delimiterOffset = Util.delimiterOffset(this.j, indexOf, this.j.length(), "?#");
        List<String> arrayList = new ArrayList();
        while (indexOf < delimiterOffset) {
            int i = indexOf + 1;
            indexOf = Util.delimiterOffset(this.j, i, delimiterOffset, '/');
            arrayList.add(this.j.substring(i, indexOf));
        }
        return arrayList;
    }

    public List<String> pathSegments() {
        return this.g;
    }

    @Nullable
    public String encodedQuery() {
        if (this.h == null) {
            return null;
        }
        int indexOf = this.j.indexOf(63) + 1;
        return this.j.substring(indexOf, Util.delimiterOffset(this.j, indexOf, this.j.length(), '#'));
    }

    static void b(StringBuilder stringBuilder, List<String> list) {
        int size = list.size();
        for (int i = 0; i < size; i += 2) {
            String str = (String) list.get(i);
            String str2 = (String) list.get(i + 1);
            if (i > 0) {
                stringBuilder.append(Typography.amp);
            }
            stringBuilder.append(str);
            if (str2 != null) {
                stringBuilder.append('=');
                stringBuilder.append(str2);
            }
        }
    }

    static List<String> a(String str) {
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

    @Nullable
    public String query() {
        if (this.h == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        b(stringBuilder, this.h);
        return stringBuilder.toString();
    }

    public int querySize() {
        return this.h != null ? this.h.size() / 2 : 0;
    }

    @Nullable
    public String queryParameter(String str) {
        if (this.h == null) {
            return null;
        }
        int size = this.h.size();
        for (int i = 0; i < size; i += 2) {
            if (str.equals(this.h.get(i))) {
                return (String) this.h.get(i + 1);
            }
        }
        return null;
    }

    public Set<String> queryParameterNames() {
        if (this.h == null) {
            return Collections.emptySet();
        }
        Set linkedHashSet = new LinkedHashSet();
        int size = this.h.size();
        for (int i = 0; i < size; i += 2) {
            linkedHashSet.add(this.h.get(i));
        }
        return Collections.unmodifiableSet(linkedHashSet);
    }

    public List<String> queryParameterValues(String str) {
        if (this.h == null) {
            return Collections.emptyList();
        }
        List arrayList = new ArrayList();
        int size = this.h.size();
        for (int i = 0; i < size; i += 2) {
            if (str.equals(this.h.get(i))) {
                arrayList.add(this.h.get(i + 1));
            }
        }
        return Collections.unmodifiableList(arrayList);
    }

    public String queryParameterName(int i) {
        if (this.h != null) {
            return (String) this.h.get(i * 2);
        }
        throw new IndexOutOfBoundsException();
    }

    public String queryParameterValue(int i) {
        if (this.h != null) {
            return (String) this.h.get((i * 2) + 1);
        }
        throw new IndexOutOfBoundsException();
    }

    @Nullable
    public String encodedFragment() {
        if (this.i == null) {
            return null;
        }
        return this.j.substring(this.j.indexOf(35) + 1);
    }

    @Nullable
    public String fragment() {
        return this.i;
    }

    public String redact() {
        return newBuilder("/...").username("").password("").build().toString();
    }

    @Nullable
    public HttpUrl resolve(String str) {
        Builder newBuilder = newBuilder(str);
        return newBuilder != null ? newBuilder.build() : null;
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.a = this.a;
        builder.b = encodedUsername();
        builder.c = encodedPassword();
        builder.d = this.b;
        builder.e = this.c != defaultPort(this.a) ? this.c : -1;
        builder.f.clear();
        builder.f.addAll(encodedPathSegments());
        builder.encodedQuery(encodedQuery());
        builder.h = encodedFragment();
        return builder;
    }

    @Nullable
    public Builder newBuilder(String str) {
        Builder builder = new Builder();
        return builder.a(this, str) == HttpUrl$Builder$a.SUCCESS ? builder : null;
    }

    @Nullable
    public static HttpUrl parse(String str) {
        Builder builder = new Builder();
        if (builder.a(null, str) == HttpUrl$Builder$a.SUCCESS) {
            return builder.build();
        }
        return null;
    }

    @Nullable
    public static HttpUrl get(URL url) {
        return parse(url.toString());
    }

    static HttpUrl b(String str) throws MalformedURLException, UnknownHostException {
        Builder builder = new Builder();
        HttpUrl$Builder$a a = builder.a(null, str);
        switch (l.a[a.ordinal()]) {
            case 1:
                return builder.build();
            case 2:
                throw new UnknownHostException("Invalid host: " + str);
            default:
                throw new MalformedURLException("Invalid URL: " + a + " for " + str);
        }
    }

    @Nullable
    public static HttpUrl get(URI uri) {
        return parse(uri.toString());
    }

    public boolean equals(@Nullable Object obj) {
        return (obj instanceof HttpUrl) && ((HttpUrl) obj).j.equals(this.j);
    }

    public int hashCode() {
        return this.j.hashCode();
    }

    public String toString() {
        return this.j;
    }

    @Nullable
    public String topPrivateDomain() {
        if (Util.verifyAsIpAddress(this.b)) {
            return null;
        }
        return PublicSuffixDatabase.get().getEffectiveTldPlusOne(this.b);
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
                Buffer buffer = new Buffer();
                buffer.writeUtf8(str, i, i3);
                a(buffer, str, i3, i2, z);
                return buffer.readUtf8();
            }
        }
        return str.substring(i, i2);
    }

    static void a(Buffer buffer, String str, int i, int i2, boolean z) {
        int i3 = i;
        while (i3 < i2) {
            int codePointAt = str.codePointAt(i3);
            if (codePointAt != 37 || i3 + 2 >= i2) {
                if (codePointAt == 43 && z) {
                    buffer.writeByte(32);
                }
                buffer.writeUtf8CodePoint(codePointAt);
            } else {
                int decodeHexDigit = Util.decodeHexDigit(str.charAt(i3 + 1));
                int decodeHexDigit2 = Util.decodeHexDigit(str.charAt(i3 + 2));
                if (!(decodeHexDigit == -1 || decodeHexDigit2 == -1)) {
                    buffer.writeByte((decodeHexDigit << 4) + decodeHexDigit2);
                    i3 += 2;
                }
                buffer.writeUtf8CodePoint(codePointAt);
            }
            i3 += Character.charCount(codePointAt);
        }
    }

    static boolean a(String str, int i, int i2) {
        return i + 2 < i2 && str.charAt(i) == '%' && Util.decodeHexDigit(str.charAt(i + 1)) != -1 && Util.decodeHexDigit(str.charAt(i + 2)) != -1;
    }

    static String a(String str, int i, int i2, String str2, boolean z, boolean z2, boolean z3, boolean z4, Charset charset) {
        int i3 = i;
        while (i3 < i2) {
            int codePointAt = str.codePointAt(i3);
            if (codePointAt < 32 || codePointAt == 127 || ((codePointAt >= 128 && z4) || str2.indexOf(codePointAt) != -1 || ((codePointAt == 37 && (!z || (z2 && !a(str, i3, i2)))) || (codePointAt == 43 && z3)))) {
                Buffer buffer = new Buffer();
                buffer.writeUtf8(str, i, i3);
                a(buffer, str, i3, i2, str2, z, z2, z3, z4, charset);
                return buffer.readUtf8();
            }
            i3 += Character.charCount(codePointAt);
        }
        return str.substring(i, i2);
    }

    static void a(Buffer buffer, String str, int i, int i2, String str2, boolean z, boolean z2, boolean z3, boolean z4, Charset charset) {
        Buffer buffer2 = null;
        while (i < i2) {
            int codePointAt = str.codePointAt(i);
            if (!(z && (codePointAt == 9 || codePointAt == 10 || codePointAt == 12 || codePointAt == 13))) {
                if (codePointAt == 43 && z3) {
                    buffer.writeUtf8(z ? MqttTopic.SINGLE_LEVEL_WILDCARD : "%2B");
                } else if (codePointAt < 32 || codePointAt == 127 || ((codePointAt >= 128 && z4) || str2.indexOf(codePointAt) != -1 || (codePointAt == 37 && (!z || (z2 && !a(str, i, i2)))))) {
                    if (buffer2 == null) {
                        buffer2 = new Buffer();
                    }
                    if (charset == null || charset.equals(Util.UTF_8)) {
                        buffer2.writeUtf8CodePoint(codePointAt);
                    } else {
                        buffer2.writeString(str, i, Character.charCount(codePointAt) + i, charset);
                    }
                    while (!buffer2.exhausted()) {
                        int readByte = buffer2.readByte() & 255;
                        buffer.writeByte(37);
                        buffer.writeByte(d[(readByte >> 4) & 15]);
                        buffer.writeByte(d[readByte & 15]);
                    }
                } else {
                    buffer.writeUtf8CodePoint(codePointAt);
                }
            }
            i += Character.charCount(codePointAt);
        }
    }

    static String a(String str, String str2, boolean z, boolean z2, boolean z3, boolean z4, Charset charset) {
        return a(str, 0, str.length(), str2, z, z2, z3, z4, charset);
    }

    static String a(String str, String str2, boolean z, boolean z2, boolean z3, boolean z4) {
        return a(str, 0, str.length(), str2, z, z2, z3, z4, null);
    }
}
