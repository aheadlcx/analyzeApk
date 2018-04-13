package okhttp3.internal.b;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Pattern;
import okhttp3.aa;
import okhttp3.internal.c;
import okhttp3.l;
import okhttp3.m;
import okhttp3.r;
import okhttp3.r.a;
import okhttp3.s;
import okhttp3.y;

public final class e {
    private static final Pattern a = Pattern.compile(" +([^ \"=]*)=(:?\"([^\"]*)\"|([^ \"=]*)) *(:?,|$)");

    public static long a(aa aaVar) {
        return a(aaVar.g());
    }

    public static long a(r rVar) {
        return a(rVar.a("Content-Length"));
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

    public static boolean a(aa aaVar, r rVar, y yVar) {
        for (String str : e(aaVar)) {
            if (!c.a(rVar.b(str), yVar.b(str))) {
                return false;
            }
        }
        return true;
    }

    public static boolean b(aa aaVar) {
        return b(aaVar.g());
    }

    public static boolean b(r rVar) {
        return c(rVar).contains("*");
    }

    private static Set<String> e(aa aaVar) {
        return c(aaVar.g());
    }

    public static Set<String> c(r rVar) {
        Set<String> emptySet = Collections.emptySet();
        int a = rVar.a();
        for (int i = 0; i < a; i++) {
            if ("Vary".equalsIgnoreCase(rVar.a(i))) {
                String b = rVar.b(i);
                if (emptySet.isEmpty()) {
                    emptySet = new TreeSet(String.CASE_INSENSITIVE_ORDER);
                }
                for (String trim : b.split(",")) {
                    emptySet.add(trim.trim());
                }
            }
        }
        return emptySet;
    }

    public static r c(aa aaVar) {
        return a(aaVar.j().a().c(), aaVar.g());
    }

    public static r a(r rVar, r rVar2) {
        Set c = c(rVar2);
        if (c.isEmpty()) {
            return new a().a();
        }
        a aVar = new a();
        int a = rVar.a();
        for (int i = 0; i < a; i++) {
            String a2 = rVar.a(i);
            if (c.contains(a2)) {
                aVar.a(a2, rVar.b(i));
            }
        }
        return aVar.a();
    }

    public static void a(m mVar, s sVar, r rVar) {
        if (mVar != m.a) {
            List a = l.a(sVar, rVar);
            if (!a.isEmpty()) {
                mVar.a(sVar, a);
            }
        }
    }

    public static boolean d(aa aaVar) {
        if (aaVar.a().b().equals("HEAD")) {
            return false;
        }
        int c = aaVar.c();
        if ((c < 100 || c >= 200) && c != 204 && c != 304) {
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
