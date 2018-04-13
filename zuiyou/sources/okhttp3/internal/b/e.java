package okhttp3.internal.b;

import java.util.List;
import java.util.regex.Pattern;
import okhttp3.HttpUrl;
import okhttp3.aa;
import okhttp3.l;
import okhttp3.m;
import okhttp3.s;

public final class e {
    private static final Pattern a = Pattern.compile(" +([^ \"=]*)=(:?\"([^\"]*)\"|([^ \"=]*)) *(:?,|$)");

    public static long a(aa aaVar) {
        return a(aaVar.f());
    }

    public static long a(s sVar) {
        return a(sVar.a("Content-Length"));
    }

    private static long a(String str) {
        long j = -1;
        if (str != null) {
            try {
                j = Long.parseLong(str);
            } catch (NumberFormatException e) {
            }
        }
        return j;
    }

    public static void a(m mVar, HttpUrl httpUrl, s sVar) {
        if (mVar != m.a) {
            List a = l.a(httpUrl, sVar);
            if (!a.isEmpty()) {
                mVar.a(httpUrl, a);
            }
        }
    }

    public static boolean b(aa aaVar) {
        if (aaVar.a().b().equals("HEAD")) {
            return false;
        }
        int b = aaVar.b();
        if ((b < 100 || b >= 200) && b != 204 && b != 304) {
            return true;
        }
        if (a(aaVar) != -1 || "chunked".equalsIgnoreCase(aaVar.a("Transfer-Encoding"))) {
            return true;
        }
        return false;
    }

    public static int a(String str, int i, String str2) {
        while (i < str.length() && str2.indexOf(str.charAt(i)) == -1) {
            i++;
        }
        return i;
    }

    public static int a(String str, int i) {
        while (i < str.length()) {
            char charAt = str.charAt(i);
            if (charAt != ' ' && charAt != '\t') {
                break;
            }
            i++;
        }
        return i;
    }

    public static int b(String str, int i) {
        try {
            long parseLong = Long.parseLong(str);
            if (parseLong > 2147483647L) {
                return Integer.MAX_VALUE;
            }
            if (parseLong < 0) {
                return 0;
            }
            return (int) parseLong;
        } catch (NumberFormatException e) {
            return i;
        }
    }
}
