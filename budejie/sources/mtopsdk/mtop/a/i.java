package mtopsdk.mtop.a;

import android.content.Context;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import mtopsdk.common.a.a;
import mtopsdk.common.util.d;
import mtopsdk.common.util.h;
import mtopsdk.common.util.l;
import mtopsdk.common.util.m;

public class i {
    private static final i a = new i();
    private static final h b = h.a();
    private static final d c = d.a();
    private static a d = null;
    private static long e = 10;
    private static Map f = new ConcurrentHashMap();

    private i() {
    }

    public static i a() {
        return a;
    }

    public long a(String str) {
        if (l.b(str)) {
            return 0;
        }
        String str2 = (String) f.get(str);
        if (l.b(str2)) {
            return 0;
        }
        long parseLong;
        try {
            parseLong = Long.parseLong(str2);
        } catch (Exception e) {
            m.d("mtopsdk.SwitchConfig", "[getIndividualApiLockInterval]parse individual apiLock interval error.apiKey=" + str + " ---" + e.toString());
            parseLong = 0;
        }
        return parseLong;
    }

    public void a(Context context) {
        if (d != null) {
            a aVar = d;
        }
    }

    public boolean b() {
        return c.c && b.c;
    }

    public long c() {
        long j = b.d;
        e = j;
        return j;
    }

    public boolean d() {
        return c.b && b.b;
    }
}
