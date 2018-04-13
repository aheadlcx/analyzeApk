package okhttp3.internal;

import android.support.v4.media.session.PlaybackStateCompat;
import com.baidu.mobstat.Config;
import java.io.Closeable;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.IDN;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import okhttp3.HttpUrl;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;
import okio.Source;

public final class Util {
    public static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
    public static final RequestBody EMPTY_REQUEST = RequestBody.create(null, EMPTY_BYTE_ARRAY);
    public static final ResponseBody EMPTY_RESPONSE = ResponseBody.create(null, EMPTY_BYTE_ARRAY);
    public static final String[] EMPTY_STRING_ARRAY = new String[0];
    public static final Charset ISO_8859_1 = Charset.forName("ISO-8859-1");
    public static final Comparator<String> NATURAL_ORDER = new a();
    public static final TimeZone UTC = TimeZone.getTimeZone("GMT");
    public static final Charset UTF_8 = Charset.forName("UTF-8");
    private static final ByteString a = ByteString.decodeHex("efbbbf");
    private static final ByteString b = ByteString.decodeHex("feff");
    private static final ByteString c = ByteString.decodeHex("fffe");
    private static final ByteString d = ByteString.decodeHex("0000ffff");
    private static final ByteString e = ByteString.decodeHex("ffff0000");
    private static final Charset f = Charset.forName("UTF-16BE");
    private static final Charset g = Charset.forName("UTF-16LE");
    private static final Charset h = Charset.forName("UTF-32BE");
    private static final Charset i = Charset.forName("UTF-32LE");
    private static final Pattern j = Pattern.compile("([0-9a-fA-F]*:[0-9a-fA-F:.]*)|([\\d.]+)");

    private Util() {
    }

    public static void checkOffsetAndCount(long j, long j2, long j3) {
        if ((j2 | j3) < 0 || j2 > j || j - j2 < j3) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public static boolean equal(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception e2) {
            }
        }
    }

    public static void closeQuietly(Socket socket) {
        if (socket != null) {
            try {
                socket.close();
            } catch (AssertionError e) {
                if (!isAndroidGetsocknameError(e)) {
                    throw e;
                }
            } catch (RuntimeException e2) {
                throw e2;
            } catch (Exception e3) {
            }
        }
    }

    public static void closeQuietly(ServerSocket serverSocket) {
        if (serverSocket != null) {
            try {
                serverSocket.close();
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception e2) {
            }
        }
    }

    public static boolean discard(Source source, int i, TimeUnit timeUnit) {
        try {
            return skipAll(source, i, timeUnit);
        } catch (IOException e) {
            return false;
        }
    }

    public static boolean skipAll(Source source, int i, TimeUnit timeUnit) throws IOException {
        long deadlineNanoTime;
        long nanoTime = System.nanoTime();
        if (source.timeout().hasDeadline()) {
            deadlineNanoTime = source.timeout().deadlineNanoTime() - nanoTime;
        } else {
            deadlineNanoTime = Long.MAX_VALUE;
        }
        source.timeout().deadlineNanoTime(Math.min(deadlineNanoTime, timeUnit.toNanos((long) i)) + nanoTime);
        try {
            Buffer buffer = new Buffer();
            while (source.read(buffer, PlaybackStateCompat.ACTION_PLAY_FROM_URI) != -1) {
                buffer.clear();
            }
            if (deadlineNanoTime == Long.MAX_VALUE) {
                source.timeout().clearDeadline();
            } else {
                source.timeout().deadlineNanoTime(deadlineNanoTime + nanoTime);
            }
            return true;
        } catch (InterruptedIOException e) {
            if (deadlineNanoTime == Long.MAX_VALUE) {
                source.timeout().clearDeadline();
            } else {
                source.timeout().deadlineNanoTime(deadlineNanoTime + nanoTime);
            }
            return false;
        } catch (Throwable th) {
            if (deadlineNanoTime == Long.MAX_VALUE) {
                source.timeout().clearDeadline();
            } else {
                source.timeout().deadlineNanoTime(deadlineNanoTime + nanoTime);
            }
            throw th;
        }
    }

    public static <T> List<T> immutableList(List<T> list) {
        return Collections.unmodifiableList(new ArrayList(list));
    }

    public static <T> List<T> immutableList(T... tArr) {
        return Collections.unmodifiableList(Arrays.asList((Object[]) tArr.clone()));
    }

    public static ThreadFactory threadFactory(String str, boolean z) {
        return new b(str, z);
    }

    public static String[] intersect(Comparator<? super String> comparator, String[] strArr, String[] strArr2) {
        List arrayList = new ArrayList();
        for (Object obj : strArr) {
            for (Object compare : strArr2) {
                if (comparator.compare(obj, compare) == 0) {
                    arrayList.add(obj);
                    break;
                }
            }
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    public static boolean nonEmptyIntersection(Comparator<String> comparator, String[] strArr, String[] strArr2) {
        if (strArr == null || strArr2 == null || strArr.length == 0 || strArr2.length == 0) {
            return false;
        }
        for (Object obj : strArr) {
            for (Object compare : strArr2) {
                if (comparator.compare(obj, compare) == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public static String hostHeader(HttpUrl httpUrl, boolean z) {
        String str;
        if (httpUrl.host().contains(Config.TRACE_TODAY_VISIT_SPLIT)) {
            str = "[" + httpUrl.host() + "]";
        } else {
            str = httpUrl.host();
        }
        if (z || httpUrl.port() != HttpUrl.defaultPort(httpUrl.scheme())) {
            return str + Config.TRACE_TODAY_VISIT_SPLIT + httpUrl.port();
        }
        return str;
    }

    public static String toHumanReadableAscii(String str) {
        int length = str.length();
        int i = 0;
        while (i < length) {
            int codePointAt = str.codePointAt(i);
            if (codePointAt <= 31 || codePointAt >= 127) {
                Buffer buffer = new Buffer();
                buffer.writeUtf8(str, 0, i);
                codePointAt = i;
                while (codePointAt < length) {
                    int codePointAt2 = str.codePointAt(codePointAt);
                    i = (codePointAt2 <= 31 || codePointAt2 >= 127) ? 63 : codePointAt2;
                    buffer.writeUtf8CodePoint(i);
                    codePointAt = Character.charCount(codePointAt2) + codePointAt;
                }
                return buffer.readUtf8();
            }
            i += Character.charCount(codePointAt);
        }
        return str;
    }

    public static boolean isAndroidGetsocknameError(AssertionError assertionError) {
        return (assertionError.getCause() == null || assertionError.getMessage() == null || !assertionError.getMessage().contains("getsockname failed")) ? false : true;
    }

    public static int indexOf(Comparator<String> comparator, String[] strArr, String str) {
        int length = strArr.length;
        for (int i = 0; i < length; i++) {
            if (comparator.compare(strArr[i], str) == 0) {
                return i;
            }
        }
        return -1;
    }

    public static String[] concat(String[] strArr, String str) {
        Object obj = new String[(strArr.length + 1)];
        System.arraycopy(strArr, 0, obj, 0, strArr.length);
        obj[obj.length - 1] = str;
        return obj;
    }

    public static int skipLeadingAsciiWhitespace(String str, int i, int i2) {
        int i3 = i;
        while (i3 < i2) {
            switch (str.charAt(i3)) {
                case '\t':
                case '\n':
                case '\f':
                case '\r':
                case ' ':
                    i3++;
                default:
                    return i3;
            }
        }
        return i2;
    }

    public static int skipTrailingAsciiWhitespace(String str, int i, int i2) {
        int i3 = i2 - 1;
        while (i3 >= i) {
            switch (str.charAt(i3)) {
                case '\t':
                case '\n':
                case '\f':
                case '\r':
                case ' ':
                    i3--;
                default:
                    return i3 + 1;
            }
        }
        return i;
    }

    public static String trimSubstring(String str, int i, int i2) {
        int skipLeadingAsciiWhitespace = skipLeadingAsciiWhitespace(str, i, i2);
        return str.substring(skipLeadingAsciiWhitespace, skipTrailingAsciiWhitespace(str, skipLeadingAsciiWhitespace, i2));
    }

    public static int delimiterOffset(String str, int i, int i2, String str2) {
        for (int i3 = i; i3 < i2; i3++) {
            if (str2.indexOf(str.charAt(i3)) != -1) {
                return i3;
            }
        }
        return i2;
    }

    public static int delimiterOffset(String str, int i, int i2, char c) {
        for (int i3 = i; i3 < i2; i3++) {
            if (str.charAt(i3) == c) {
                return i3;
            }
        }
        return i2;
    }

    public static String canonicalizeHost(String str) {
        if (str.contains(Config.TRACE_TODAY_VISIT_SPLIT)) {
            InetAddress a;
            if (str.startsWith("[") && str.endsWith("]")) {
                a = a(str, 1, str.length() - 1);
            } else {
                a = a(str, 0, str.length());
            }
            if (a == null) {
                return null;
            }
            byte[] address = a.getAddress();
            if (address.length == 16) {
                return a(address);
            }
            throw new AssertionError("Invalid IPv6 address: '" + str + "'");
        }
        try {
            String toLowerCase = IDN.toASCII(str).toLowerCase(Locale.US);
            if (toLowerCase.isEmpty() || a(toLowerCase)) {
                return null;
            }
            return toLowerCase;
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    private static boolean a(String str) {
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt <= '\u001f' || charAt >= '') {
                return true;
            }
            if (" #%/:?@[\\]".indexOf(charAt) != -1) {
                return true;
            }
        }
        return false;
    }

    public static int indexOfControlOrNonAscii(String str) {
        int i = 0;
        int length = str.length();
        while (i < length) {
            char charAt = str.charAt(i);
            if (charAt <= '\u001f' || charAt >= '') {
                return i;
            }
            i++;
        }
        return -1;
    }

    public static boolean verifyAsIpAddress(String str) {
        return j.matcher(str).matches();
    }

    public static String format(String str, Object... objArr) {
        return String.format(Locale.US, str, objArr);
    }

    public static Charset bomAwareCharset(BufferedSource bufferedSource, Charset charset) throws IOException {
        if (bufferedSource.rangeEquals(0, a)) {
            bufferedSource.skip((long) a.size());
            return UTF_8;
        } else if (bufferedSource.rangeEquals(0, b)) {
            bufferedSource.skip((long) b.size());
            return f;
        } else if (bufferedSource.rangeEquals(0, c)) {
            bufferedSource.skip((long) c.size());
            return g;
        } else if (bufferedSource.rangeEquals(0, d)) {
            bufferedSource.skip((long) d.size());
            return h;
        } else if (!bufferedSource.rangeEquals(0, e)) {
            return charset;
        } else {
            bufferedSource.skip((long) e.size());
            return i;
        }
    }

    public static int checkDuration(String str, long j, TimeUnit timeUnit) {
        if (j < 0) {
            throw new IllegalArgumentException(str + " < 0");
        } else if (timeUnit == null) {
            throw new NullPointerException("unit == null");
        } else {
            long toMillis = timeUnit.toMillis(j);
            if (toMillis > 2147483647L) {
                throw new IllegalArgumentException(str + " too large.");
            } else if (toMillis != 0 || j <= 0) {
                return (int) toMillis;
            } else {
                throw new IllegalArgumentException(str + " too small.");
            }
        }
    }

    public static AssertionError assertionError(String str, Exception exception) {
        return (AssertionError) new AssertionError(str).initCause(exception);
    }

    public static int decodeHexDigit(char c) {
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

    @Nullable
    private static InetAddress a(String str, int i, int i2) {
        Object obj = new byte[16];
        int i3 = i;
        int i4 = -1;
        int i5 = -1;
        int i6 = 0;
        while (i3 < i2) {
            if (i6 == obj.length) {
                return null;
            }
            int decodeHexDigit;
            if (i3 + 2 > i2 || !str.regionMatches(i3, "::", 0, 2)) {
                if (i6 != 0) {
                    if (str.regionMatches(i3, Config.TRACE_TODAY_VISIT_SPLIT, 0, 1)) {
                        i3++;
                    } else if (!str.regionMatches(i3, ".", 0, 1)) {
                        return null;
                    } else {
                        if (!a(str, i4, i2, obj, i6 - 2)) {
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
                decodeHexDigit = decodeHexDigit(str.charAt(i7));
                if (decodeHexDigit == -1) {
                    break;
                }
                i4 = (i4 << 4) + decodeHexDigit;
                i7++;
            }
            decodeHexDigit = i7 - i3;
            if (decodeHexDigit == 0 || decodeHexDigit > 4) {
                return null;
            }
            decodeHexDigit = i6 + 1;
            obj[i6] = (byte) ((i4 >>> 8) & 255);
            i6 = decodeHexDigit + 1;
            obj[decodeHexDigit] = (byte) (i4 & 255);
            i4 = i3;
            i3 = i7;
        }
        if (i6 != obj.length) {
            if (i5 == -1) {
                return null;
            }
            System.arraycopy(obj, i5, obj, obj.length - (i6 - i5), i6 - i5);
            Arrays.fill(obj, i5, (obj.length - i6) + i5, (byte) 0);
        }
        try {
            return InetAddress.getByAddress(obj);
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
            if (i6 > i2 && i6 >= 4) {
                i2 = i6;
                i3 = i4;
            }
            i4 = i5 + 2;
        }
        Buffer buffer = new Buffer();
        while (i < bArr.length) {
            if (i == i3) {
                buffer.writeByte(58);
                i += i2;
                if (i == 16) {
                    buffer.writeByte(58);
                }
            } else {
                if (i > 0) {
                    buffer.writeByte(58);
                }
                buffer.writeHexadecimalUnsignedLong((long) (((bArr[i] & 255) << 8) | (bArr[i + 1] & 255)));
                i += 2;
            }
        }
        return buffer.readUtf8();
    }
}
