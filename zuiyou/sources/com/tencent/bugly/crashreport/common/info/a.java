package com.tencent.bugly.crashreport.common.info;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.os.Process;
import com.iflytek.speech.VoiceWakeuperAidl;
import com.tencent.bugly.b;
import com.tencent.bugly.proguard.an;
import com.tencent.bugly.proguard.ap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

public class a {
    private static a aj = null;
    public String A = null;
    public String B = null;
    public String C = null;
    public boolean D = false;
    public boolean E = false;
    public HashMap<String, String> F = new HashMap();
    public boolean G = true;
    public List<String> H = new ArrayList();
    public boolean I = false;
    public com.tencent.bugly.crashreport.a J = null;
    public SharedPreferences K;
    private final Context L;
    private String M;
    private String N;
    private String O = "unknown";
    private String P = "unknown";
    private String Q = "";
    private String R = null;
    private String S = null;
    private String T = null;
    private String U = null;
    private long V = -1;
    private long W = -1;
    private long X = -1;
    private String Y = null;
    private String Z = null;
    public final long a = System.currentTimeMillis();
    private final Object aA = new Object();
    private final Object aB = new Object();
    private Map<String, PlugInBean> aa = null;
    private boolean ab = true;
    private String ac = null;
    private String ad = null;
    private Boolean ae = null;
    private String af = null;
    private String ag = null;
    private String ah = null;
    private Map<String, PlugInBean> ai = null;
    private int ak = -1;
    private int al = -1;
    private Map<String, String> am = new HashMap();
    private Map<String, String> an = new HashMap();
    private Map<String, String> ao = new HashMap();
    private boolean ap;
    private String aq = null;
    private String ar = null;
    private String as = null;
    private String at = null;
    private String au = null;
    private final Object av = new Object();
    private final Object aw = new Object();
    private final Object ax = new Object();
    private final Object ay = new Object();
    private final Object az = new Object();
    public final String b;
    public final byte c;
    public String d;
    public final String e;
    public String f;
    public boolean g = true;
    public final String h = "com.tencent.bugly";
    public final String i = "2.6.5";
    public final String j = "";
    public final String k;
    public final String l;
    public final String m;
    public long n = 0;
    public String o = null;
    public String p = null;
    public String q = null;
    public String r = null;
    public String s = null;
    public List<String> t = null;
    public String u = "unknown";
    public long v = 0;
    public long w = 0;
    public long x = 0;
    public long y = 0;
    public boolean z = false;

    private a(Context context) {
        this.L = ap.a(context);
        this.c = (byte) 1;
        b(context);
        this.d = AppInfo.a(context);
        this.e = AppInfo.a(context, Process.myPid());
        this.k = b.m();
        this.l = b.a();
        this.p = AppInfo.c(context);
        this.m = "Android " + b.b() + ",level " + b.c();
        this.b = this.l + VoiceWakeuperAidl.PARAMS_SEPARATE + this.m;
        c(context);
        try {
            if (!context.getDatabasePath("bugly_db_").exists()) {
                this.E = true;
                an.c("App is first time to be installed on the device.", new Object[0]);
            }
        } catch (Throwable th) {
            if (b.c) {
                th.printStackTrace();
            }
        }
        this.K = ap.a("BUGLY_COMMON_VALUES", context);
        an.c("com info create end", new Object[0]);
    }

    private void b(Context context) {
        PackageInfo b = AppInfo.b(context);
        if (b != null) {
            try {
                this.o = b.versionName;
                this.A = this.o;
                this.B = Integer.toString(b.versionCode);
            } catch (Throwable th) {
                if (!an.a(th)) {
                    th.printStackTrace();
                }
            }
        }
    }

    private void c(Context context) {
        Map d = AppInfo.d(context);
        if (d != null) {
            try {
                this.t = AppInfo.a(d);
                String str = (String) d.get("BUGLY_APPID");
                if (str != null) {
                    this.ad = str;
                }
                str = (String) d.get("BUGLY_APP_VERSION");
                if (str != null) {
                    this.o = str;
                }
                str = (String) d.get("BUGLY_APP_CHANNEL");
                if (str != null) {
                    this.q = str;
                }
                str = (String) d.get("BUGLY_ENABLE_DEBUG");
                if (str != null) {
                    this.z = str.equalsIgnoreCase("true");
                }
                str = (String) d.get("com.tencent.rdm.uuid");
                if (str != null) {
                    this.C = str;
                }
            } catch (Throwable th) {
                if (!an.a(th)) {
                    th.printStackTrace();
                }
            }
        }
    }

    public boolean a() {
        return this.ap;
    }

    public void a(boolean z) {
        this.ap = z;
        if (this.J != null) {
            this.J.setNativeIsAppForeground(z);
        }
    }

    public static synchronized a a(Context context) {
        a aVar;
        synchronized (a.class) {
            if (aj == null) {
                aj = new a(context);
            }
            aVar = aj;
        }
        return aVar;
    }

    public static synchronized a b() {
        a aVar;
        synchronized (a.class) {
            aVar = aj;
        }
        return aVar;
    }

    public String c() {
        return "2.6.5";
    }

    public void d() {
        synchronized (this.av) {
            this.M = UUID.randomUUID().toString();
        }
    }

    public String e() {
        if (this.M == null) {
            synchronized (this.av) {
                if (this.M == null) {
                    this.M = UUID.randomUUID().toString();
                }
            }
        }
        return this.M;
    }

    public String f() {
        if (ap.a(this.f)) {
            return this.ad;
        }
        return this.f;
    }

    public void a(String str) {
        this.ad = str;
    }

    public String g() {
        String str;
        synchronized (this.aA) {
            str = this.O;
        }
        return str;
    }

    public void b(String str) {
        synchronized (this.aA) {
            if (str == null) {
                str = "10000";
            }
            this.O = "" + str;
        }
    }

    public String h() {
        if (this.N != null) {
            return this.N;
        }
        this.N = k() + "|" + m() + "|" + n();
        return this.N;
    }

    public void c(String str) {
        this.N = str;
        synchronized (this.aB) {
            this.an.put("E8", str);
        }
    }

    public synchronized String i() {
        return this.P;
    }

    public synchronized void d(String str) {
        this.P = "" + str;
    }

    public synchronized String j() {
        return this.Q;
    }

    public synchronized void e(String str) {
        this.Q = "" + str;
    }

    public String k() {
        if (!this.ab) {
            return "";
        }
        if (this.R == null) {
            this.R = b.a(this.L);
        }
        return this.R;
    }

    public String l() {
        if (!this.ab) {
            return "";
        }
        if (this.S == null || !this.S.contains(":")) {
            this.S = b.d(this.L);
        }
        return this.S;
    }

    public String m() {
        if (!this.ab) {
            return "";
        }
        if (this.T == null) {
            this.T = b.b(this.L);
        }
        return this.T;
    }

    public String n() {
        if (!this.ab) {
            return "";
        }
        if (this.U == null) {
            this.U = b.c(this.L);
        }
        return this.U;
    }

    public long o() {
        if (this.V <= 0) {
            this.V = b.f();
        }
        return this.V;
    }

    public long p() {
        if (this.W <= 0) {
            this.W = b.h();
        }
        return this.W;
    }

    public long q() {
        if (this.X <= 0) {
            this.X = b.j();
        }
        return this.X;
    }

    public String r() {
        if (this.Y == null) {
            this.Y = b.a(true);
        }
        return this.Y;
    }

    public String s() {
        if (this.Z == null) {
            this.Z = b.h(this.L);
        }
        return this.Z;
    }

    public void a(String str, String str2) {
        if (str != null && str2 != null) {
            synchronized (this.aw) {
                this.F.put(str, str2);
            }
        }
    }

    public String t() {
        try {
            Map all = this.L.getSharedPreferences("BuglySdkInfos", 0).getAll();
            if (!all.isEmpty()) {
                synchronized (this.aw) {
                    for (Entry entry : all.entrySet()) {
                        try {
                            this.F.put(entry.getKey(), entry.getValue().toString());
                        } catch (Throwable th) {
                            an.a(th);
                        }
                    }
                }
            }
        } catch (Throwable th2) {
            an.a(th2);
        }
        if (this.F.isEmpty()) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Entry entry2 : this.F.entrySet()) {
            stringBuilder.append("[");
            stringBuilder.append((String) entry2.getKey());
            stringBuilder.append(",");
            stringBuilder.append((String) entry2.getValue());
            stringBuilder.append("] ");
        }
        c("SDK_INFO", stringBuilder.toString());
        return stringBuilder.toString();
    }

    public String u() {
        if (this.au == null) {
            this.au = AppInfo.e(this.L);
        }
        return this.au;
    }

    public synchronized Map<String, PlugInBean> v() {
        Map<String, PlugInBean> map;
        if (this.aa == null || this.aa.size() <= 0) {
            map = null;
        } else {
            map = new HashMap(this.aa.size());
            map.putAll(this.aa);
        }
        return map;
    }

    public String w() {
        if (this.ac == null) {
            this.ac = b.l();
        }
        return this.ac;
    }

    public Boolean x() {
        if (this.ae == null) {
            this.ae = Boolean.valueOf(b.i(this.L));
        }
        return this.ae;
    }

    public String y() {
        if (this.af == null) {
            this.af = "" + b.g(this.L);
            an.a("ROM ID: %s", this.af);
        }
        return this.af;
    }

    public String z() {
        if (this.ag == null) {
            this.ag = "" + b.e(this.L);
            an.a("SIM serial number: %s", this.ag);
        }
        return this.ag;
    }

    public String A() {
        if (this.ah == null) {
            this.ah = "" + b.d();
            an.a("Hardware serial number: %s", this.ah);
        }
        return this.ah;
    }

    public Map<String, String> B() {
        Map<String, String> map;
        synchronized (this.ax) {
            if (this.am.size() <= 0) {
                map = null;
            } else {
                map = new HashMap(this.am);
            }
        }
        return map;
    }

    public String f(String str) {
        if (ap.a(str)) {
            an.d("key should not be empty %s", "" + str);
            return null;
        }
        String str2;
        synchronized (this.ax) {
            str2 = (String) this.am.remove(str);
        }
        return str2;
    }

    public void C() {
        synchronized (this.ax) {
            this.am.clear();
        }
    }

    public String g(String str) {
        if (ap.a(str)) {
            an.d("key should not be empty %s", "" + str);
            return null;
        }
        String str2;
        synchronized (this.ax) {
            str2 = (String) this.am.get(str);
        }
        return str2;
    }

    public void b(String str, String str2) {
        if (ap.a(str) || ap.a(str2)) {
            an.d("key&value should not be empty %s %s", "" + str, "" + str2);
            return;
        }
        synchronized (this.ax) {
            this.am.put(str, str2);
        }
    }

    public int D() {
        int size;
        synchronized (this.ax) {
            size = this.am.size();
        }
        return size;
    }

    public Set<String> E() {
        Set<String> keySet;
        synchronized (this.ax) {
            keySet = this.am.keySet();
        }
        return keySet;
    }

    public Map<String, String> F() {
        Map<String, String> map;
        synchronized (this.aB) {
            if (this.an.size() <= 0) {
                map = null;
            } else {
                map = new HashMap(this.an);
            }
        }
        return map;
    }

    public void c(String str, String str2) {
        if (ap.a(str) || ap.a(str2)) {
            an.d("server key&value should not be empty %s %s", "" + str, "" + str2);
            return;
        }
        synchronized (this.ay) {
            this.ao.put(str, str2);
        }
    }

    public Map<String, String> G() {
        Map<String, String> map;
        synchronized (this.ay) {
            if (this.ao.size() <= 0) {
                map = null;
            } else {
                map = new HashMap(this.ao);
            }
        }
        return map;
    }

    public void a(int i) {
        synchronized (this.az) {
            if (this.ak != i) {
                this.ak = i;
                an.a("user scene tag %d changed to tag %d", Integer.valueOf(r0), Integer.valueOf(this.ak));
            }
        }
    }

    public int H() {
        int i;
        synchronized (this.az) {
            i = this.ak;
        }
        return i;
    }

    public void b(int i) {
        if (this.al != i) {
            this.al = i;
            an.a("server scene tag %d changed to tag %d", Integer.valueOf(r0), Integer.valueOf(this.al));
        }
    }

    public int I() {
        return this.al;
    }

    public boolean J() {
        return AppInfo.f(this.L);
    }

    public synchronized Map<String, PlugInBean> K() {
        return this.ai;
    }

    public int L() {
        return b.c();
    }

    public String M() {
        if (this.aq == null) {
            this.aq = b.n();
        }
        return this.aq;
    }

    public String N() {
        if (this.ar == null) {
            this.ar = b.j(this.L);
        }
        return this.ar;
    }

    public String O() {
        if (this.as == null) {
            this.as = b.k(this.L);
        }
        return this.as;
    }

    public String P() {
        return b.l(this.L);
    }

    public String Q() {
        if (this.at == null) {
            this.at = b.m(this.L);
        }
        return this.at;
    }

    public long R() {
        return b.n(this.L);
    }
}
