package okhttp3.internal;

import android.support.v4.media.session.PlaybackStateCompat;
import com.facebook.common.time.Clock;
import java.io.Closeable;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.lang.reflect.Array;
import java.net.IDN;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import okhttp3.ab;
import okhttp3.s;
import okhttp3.z;
import okio.ByteString;
import okio.e;
import okio.r;

public final class c {
    public static final byte[] a = new byte[0];
    public static final String[] b = new String[0];
    public static final ab c = ab.a(null, a);
    public static final z d = z.a(null, a);
    public static final Charset e = Charset.forName("UTF-8");
    public static final TimeZone f = TimeZone.getTimeZone("GMT");
    private static final ByteString g = ByteString.decodeHex("efbbbf");
    private static final ByteString h = ByteString.decodeHex("feff");
    private static final ByteString i = ByteString.decodeHex("fffe");
    private static final ByteString j = ByteString.decodeHex("0000ffff");
    private static final ByteString k = ByteString.decodeHex("ffff0000");
    private static final Charset l = Charset.forName("UTF-16BE");
    private static final Charset m = Charset.forName("UTF-16LE");
    private static final Charset n = Charset.forName("UTF-32BE");
    private static final Charset o = Charset.forName("UTF-32LE");
    private static final Pattern p = Pattern.compile("([0-9a-fA-F]*:[0-9a-fA-F:.]*)|([\\d.]+)");

    public static void a(long j, long j2, long j3) {
        if ((j2 | j3) < 0 || j2 > j || j - j2 < j3) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public static boolean a(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception e2) {
            }
        }
    }

    public static void a(Socket socket) {
        if (socket != null) {
            try {
                socket.close();
            } catch (AssertionError e) {
                if (!a(e)) {
                    throw e;
                }
            } catch (RuntimeException e2) {
                throw e2;
            } catch (Exception e3) {
            }
        }
    }

    public static boolean a(r rVar, int i, TimeUnit timeUnit) {
        try {
            return b(rVar, i, timeUnit);
        } catch (IOException e) {
            return false;
        }
    }

    public static boolean b(r rVar, int i, TimeUnit timeUnit) throws IOException {
        long nanoTime = System.nanoTime();
        long d = rVar.a().f_() ? rVar.a().d() - nanoTime : Clock.MAX_TIME;
        rVar.a().a(Math.min(d, timeUnit.toNanos((long) i)) + nanoTime);
        try {
            okio.c cVar = new okio.c();
            while (rVar.a(cVar, PlaybackStateCompat.ACTION_PLAY_FROM_URI) != -1) {
                cVar.t();
            }
            if (d == Clock.MAX_TIME) {
                rVar.a().h_();
            } else {
                rVar.a().a(d + nanoTime);
            }
            return true;
        } catch (InterruptedIOException e) {
            if (d == Clock.MAX_TIME) {
                rVar.a().h_();
            } else {
                rVar.a().a(d + nanoTime);
            }
            return false;
        } catch (Throwable th) {
            if (d == Clock.MAX_TIME) {
                rVar.a().h_();
            } else {
                rVar.a().a(d + nanoTime);
            }
            throw th;
        }
    }

    public static <T> List<T> a(List<T> list) {
        return Collections.unmodifiableList(new ArrayList(list));
    }

    public static <T> List<T> a(T... tArr) {
        return Collections.unmodifiableList(Arrays.asList((Object[]) tArr.clone()));
    }

    public static ThreadFactory a(final String str, final boolean z) {
        return new ThreadFactory() {
            public Thread newThread(Runnable runnable) {
                Thread thread = new Thread(runnable, str);
                thread.setDaemon(z);
                return thread;
            }
        };
    }

    public static <T> T[] a(Class<T> cls, T[] tArr, T[] tArr2) {
        List a = a((Object[]) tArr, (Object[]) tArr2);
        return a.toArray((Object[]) Array.newInstance(cls, a.size()));
    }

    private static <T> List<T> a(T[] tArr, T[] tArr2) {
        List<T> arrayList = new ArrayList();
        for (Object obj : tArr) {
            for (Object obj2 : tArr2) {
                if (obj.equals(obj2)) {
                    arrayList.add(obj2);
                    break;
                }
            }
        }
        return arrayList;
    }

    public static String a(s sVar, boolean z) {
        String str;
        if (sVar.f().contains(":")) {
            str = "[" + sVar.f() + "]";
        } else {
            str = sVar.f();
        }
        if (z || sVar.g() != s.a(sVar.b())) {
            return str + ":" + sVar.g();
        }
        return str;
    }

    public static boolean a(AssertionError assertionError) {
        return (assertionError.getCause() == null || assertionError.getMessage() == null || !assertionError.getMessage().contains("getsockname failed")) ? false : true;
    }

    public static <T> int a(T[] tArr, T t) {
        int length = tArr.length;
        for (int i = 0; i < length; i++) {
            if (a(tArr[i], (Object) t)) {
                return i;
            }
        }
        return -1;
    }

    public static String[] a(String[] strArr, String str) {
        Object obj = new String[(strArr.length + 1)];
        System.arraycopy(strArr, 0, obj, 0, strArr.length);
        obj[obj.length - 1] = str;
        return obj;
    }

    public static int a(String str, int i, int i2) {
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

    public static int b(String str, int i, int i2) {
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

    public static String c(String str, int i, int i2) {
        int a = a(str, i, i2);
        return str.substring(a, b(str, a, i2));
    }

    public static int a(String str, int i, int i2, String str2) {
        for (int i3 = i; i3 < i2; i3++) {
            if (str2.indexOf(str.charAt(i3)) != -1) {
                return i3;
            }
        }
        return i2;
    }

    public static int a(String str, int i, int i2, char c) {
        for (int i3 = i; i3 < i2; i3++) {
            if (str.charAt(i3) == c) {
                return i3;
            }
        }
        return i2;
    }

    public static String a(String str) {
        try {
            String toLowerCase = IDN.toASCII(str).toLowerCase(Locale.US);
            if (toLowerCase.isEmpty() || d(toLowerCase)) {
                return null;
            }
            return toLowerCase;
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    private static boolean d(String str) {
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

    public static int b(String str) {
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

    public static boolean c(String str) {
        return p.matcher(str).matches();
    }

    public static String a(String str, Object... objArr) {
        return String.format(Locale.US, str, objArr);
    }

    public static Charset a(e eVar, Charset charset) throws IOException {
        if (eVar.a(0, g)) {
            eVar.g((long) g.size());
            return e;
        } else if (eVar.a(0, h)) {
            eVar.g((long) h.size());
            return l;
        } else if (eVar.a(0, i)) {
            eVar.g((long) i.size());
            return m;
        } else if (eVar.a(0, j)) {
            eVar.g((long) j.size());
            return n;
        } else if (!eVar.a(0, k)) {
            return charset;
        } else {
            eVar.g((long) k.size());
            return o;
        }
    }
}
