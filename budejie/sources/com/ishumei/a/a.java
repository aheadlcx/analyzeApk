package com.ishumei.a;

import android.content.pm.PackageInfo;
import android.os.Build;
import android.os.Build.VERSION;
import com.alibaba.baichuan.android.trade.constants.AlibcConstants;
import com.ishumei.b.b.b;
import com.ishumei.d.c;
import com.ishumei.d.f;
import com.ishumei.d.g;
import com.ishumei.d.h;
import com.ishumei.d.i;
import com.ishumei.d.j;
import com.ishumei.d.k;
import com.ishumei.d.l;
import com.ishumei.f.d;
import com.ishumei.f.e;
import com.ishumei.smantifraud.SmAntiFraud;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class a implements d {
    private static String A;
    private static String B;
    private static String C;
    private static String D;
    private static String E;
    private static String F;
    private static String G;
    private static String H;
    private static String I;
    private static String J;
    private static String K;
    private static String L;
    private static String M;
    private static String N;
    private static String O;
    private static String P;
    private static String Q;
    private static String R;
    private static String S;
    private static String T;
    private static String U;
    private static String V;
    private static String W;
    private static String X;
    private static String Y;
    private static String Z;
    private static String a;
    private static String aa;
    private static String ab;
    private static String ac;
    private static String ad;
    private static String ae;
    private static String af;
    private static String ag;
    private static String ah;
    private static String ai;
    private static String aj;
    private static String ak;
    private static String al;
    private static String am;
    private static String an;
    private static String ao;
    private static String ap;
    private static a aq = null;
    private static String b;
    private static String c;
    private static String d;
    private static String e;
    private static String f;
    private static String g;
    private static String h;
    private static String i;
    private static String j;
    private static String k;
    private static String l;
    private static String m;
    private static String n;
    private static String o;
    private static String p;
    private static String q;
    private static String r;
    private static String s;
    private static String t;
    private static String u;
    private static String v;
    private static String w;
    private static String x;
    private static String y;
    private static String z;

    static {
        try {
            a = d.g("908c");
            b = d.g("8b");
            c = d.g("908c899a8d");
            d = d.g("8c9b94899a8d");
            e = d.g("9e8f8f899a8d");
            f = d.g("9e8f8f8a8b92");
            g = d.g("9e8f8f919e929a");
            h = d.g("919e929a");
            i = d.g("8f8d909c");
            j = d.g("9d8d9e919b");
            k = d.g("92909b9a93");
            l = d.g("9d9e919b");
            m = d.g("919a8b88908d94");
            n = d.g("908f9a8d9e8b908d");
            o = d.g("929e9c");
            p = d.g("8f8d908786");
            q = d.g("8c8c969b");
            r = d.g("9d8c8c969b");
            s = d.g("88969996968f");
            t = d.g("9d8b929e9c");
            u = d.g("8c92969b");
            v = d.g("8c92969bb99e9693ac8b908d9a");
            w = d.g("8c92969bb99691968c97af979e8c9a");
            x = d.g("96929a96");
            y = d.g("96929a96ce");
            z = d.g("96929a96cd");
            A = d.g("96928c96");
            B = d.g("969c9c969b");
            C = d.g("9e9b969b");
            D = d.g("8a9e");
            E = d.g("9d90908b");
            F = d.g("8c9c8d9a9a91");
            G = d.g("9d8d9698978b919a8c8c");
            H = d.g("9d9e8b8b9a8d86");
            I = d.g("908d969a918b9e8b969091");
            J = d.g("9c8f8ab2909b9a93");
            K = d.g("9c8f8aa99a919b908d");
            L = d.g("9c8f8abc908a918b");
            M = d.g("9c8f8ab98d9a8e");
            N = d.g("988f8c");
            O = d.g("9e8f8c");
            P = d.g("9c9a9393");
            Q = d.g("9e8f8f8c");
            R = d.g("8c868c");
            S = d.g("919a8b");
            T = d.g("8f8d908f8c");
            U = d.g("8c9a918c908d");
            V = d.g("929a92");
            W = d.g("8c9692");
            X = d.g("8b9a93");
            Y = d.g("9a928a");
            Z = d.g("9c8787");
            aa = d.g("9a878b8d9e");
            ab = d.g("9c908c8b");
            ac = d.g("8c929b918c968f");
            ad = d.g("8f8d96899e9c86");
            ae = d.g("8d8b868f9a");
            af = d.g("8b9e8d989a8ba08c9b94");
            ag = d.g("9e9d8b929e9c");
            ah = d.g("9e878f908c9a9b");
            ai = d.g("9e96919990");
            aj = d.g("8c928c9a8e");
            ak = d.g("8897968b9a9e8f8f");
            al = d.g("8d968c949e8f8f");
            am = d.g("8d968c949b968d");
            an = d.g("8a8c8d9c918b");
            ao = d.g("8c868c9c918b");
            ap = d.g("9c9c929bca");
        } catch (Exception e) {
        }
    }

    public static a a() {
        if (aq == null) {
            synchronized (a.class) {
                if (aq == null) {
                    aq = new a();
                }
            }
        }
        return aq;
    }

    private Map<String, Object> a(Map<String, com.ishumei.b.b.a> map) {
        Map<String, Object> hashMap = new HashMap();
        if (map == null || map.size() == 0) {
            return hashMap;
        }
        if (com.ishumei.b.d.a == null) {
            return hashMap;
        }
        Map hashMap2 = new HashMap();
        for (Entry entry : map.entrySet()) {
            hashMap2.put(((com.ishumei.b.b.a) entry.getValue()).b(), (String) entry.getKey());
        }
        ArrayList arrayList = new ArrayList();
        try {
            for (PackageInfo packageInfo : com.ishumei.b.d.a.getPackageManager().getInstalledPackages(0)) {
                if (hashMap2.containsKey(packageInfo.packageName)) {
                    hashMap.put(hashMap2.get(packageInfo.packageName), Integer.valueOf(1));
                }
            }
        } catch (Exception e) {
        }
        return hashMap;
    }

    private static Map<String, Object> b(Map<String, b> map) {
        Map<String, Object> hashMap = new HashMap();
        if (map == null || map.size() == 0) {
            return hashMap;
        }
        for (Entry entry : map.entrySet()) {
            try {
                String str = (String) entry.getKey();
                b bVar = (b) entry.getValue();
                if (bVar.c() == 0) {
                    if (e.a(bVar.b())) {
                        hashMap.put(str, Integer.valueOf(1));
                    }
                } else if (1 == bVar.c() && e.b(bVar.b())) {
                    hashMap.put(str, Integer.valueOf(1));
                }
            } catch (Exception e) {
            }
        }
        return hashMap;
    }

    public Map<String, Object> a(int i) {
        com.ishumei.b.b b = SmAntiFraud.cloudConfiguration.b();
        Map<String, Object> hashMap = new HashMap();
        Set k = b == null ? null : b.k();
        try {
            String str;
            hashMap.put(ae, "all");
            hashMap.put(ad, (i & 1) == 1 ? "md5" : "none");
            hashMap.put(aj, e.a().b());
            hashMap.put(f, SmAntiFraud.option.getChannel());
            hashMap.put(a, AlibcConstants.PF_ANDROID);
            hashMap.put(d, "2.3.6");
            hashMap.put(b, Long.valueOf(System.currentTimeMillis()));
            hashMap.put(c, VERSION.RELEASE);
            hashMap.put(Y, f.a().b());
            hashMap.put(af, Integer.valueOf(com.ishumei.d.b.a().b()));
            hashMap.put(ag, com.ishumei.d.a.a().b());
            if (k != null && k.contains("ainfo")) {
                hashMap.put(ah, com.ishumei.d.a.a().c());
                com.ishumei.d.a.a().a(hashMap, ai, true);
            }
            List<String> i2 = h.a().i();
            if (i2 == null) {
                hashMap.put(S, "null");
            } else if ((i & 1) == 1) {
                List arrayList = new ArrayList(i2.size());
                for (String str2 : i2) {
                    arrayList.add(e.f(str2));
                }
                hashMap.put(S, arrayList);
            } else {
                hashMap.put(S, i2);
            }
            Map b2 = k.a().b();
            if (b2 != null) {
                if ((i & 1) == 1) {
                    str2 = (String) b2.get("ro.serialno");
                    if (str2 != null) {
                        b2.put("ro.serialno", e.f(str2));
                    }
                }
                hashMap.put(T, b2);
            }
            if (k != null && k.contains("bssid")) {
                if ((i & 1) == 1) {
                    hashMap.put(r, e.f(h.a().c()));
                } else {
                    hashMap.put(r, h.a().c());
                }
            }
            if ((i & 1) == 1) {
                hashMap.put(x, e.f(l.a().c()));
            } else {
                hashMap.put(x, l.a().c());
            }
            if ((i & 1) == 1) {
                hashMap.put(y, e.f(l.a().a(1)));
            } else {
                hashMap.put(y, l.a().a(1));
            }
            if ((i & 1) == 1) {
                hashMap.put(z, e.f(l.a().a(2)));
            } else {
                hashMap.put(z, l.a().a(2));
            }
            if ((i & 1) == 1) {
                hashMap.put(C, e.f(j.a().b()));
            } else {
                hashMap.put(C, j.a().b());
            }
            if ((i & 1) == 1) {
                hashMap.put(t, e.f(h.a().g()));
            } else {
                hashMap.put(t, h.a().g());
            }
            if (k != null && k.contains("tel")) {
                if ((i & 1) == 1) {
                    hashMap.put(X, e.f(l.a().b()));
                } else {
                    hashMap.put(X, l.a().b());
                }
            }
            if (k != null && k.contains("imsi")) {
                if ((i & 1) == 1) {
                    hashMap.put(A, e.f(l.a().e()));
                } else {
                    hashMap.put(A, l.a().e());
                }
            }
            if (k != null && k.contains("mac")) {
                if ((i & 1) == 1) {
                    hashMap.put(o, e.f(h.a().d()));
                } else {
                    hashMap.put(o, h.a().d());
                }
            }
            hashMap.put(l, Build.getRadioVersion());
            if (k != null && k.contains("ssid")) {
                hashMap.put(q, h.a().b());
            }
            hashMap.put(s, h.a().e());
            hashMap.put(L, Integer.valueOf(com.ishumei.d.e.a().d()));
            hashMap.put(J, com.ishumei.d.e.a().b());
            hashMap.put(M, Integer.valueOf(com.ishumei.d.e.a().e()));
            hashMap.put(K, com.ishumei.d.e.a().c());
            hashMap.put(k, Build.MODEL);
            hashMap.put(F, j.a().e());
            hashMap.put(G, Integer.valueOf(j.a().f()));
            hashMap.put(E, Long.valueOf(j.a().c()));
            hashMap.put(e, com.ishumei.d.b.a().e());
            hashMap.put(g, com.ishumei.d.b.a().f());
            SmAntiFraud.option.isSynMode();
            hashMap.put(h, com.ishumei.d.b.a().g());
            hashMap.put(i, j.a().d());
            hashMap.put(j, Build.BRAND);
            hashMap.put(m, h.a().h());
            hashMap.put(n, l.a().d());
            hashMap.put(p, k.a().a("http.proxy"));
            hashMap.put(D, k.a().a("http.agent"));
            hashMap.put(R, c.a().b());
            hashMap.put(U, i.a().b());
            hashMap.put(V, Long.valueOf(com.ishumei.d.e.a().f()));
            hashMap.put(W, Integer.valueOf(l.a().g()));
            hashMap.put(I, i.a().b.a());
            if (k != null && k.contains("gps")) {
                hashMap.put(N, g.a().b());
            }
            if (k != null && k.contains("iccid")) {
                hashMap.put(B, l.a().f());
            }
            if (k != null && k.contains("cell")) {
                hashMap.put(P, l.a().i());
            }
            if (k != null && k.contains("aps")) {
                hashMap.put(O, h.a().f());
            }
            if (k != null && k.contains("apps")) {
                Map a = com.ishumei.d.b.a().a(b == null ? null : b.j(), b.b(), b.a());
                hashMap.put(Q, a.get("apps"));
                hashMap.put(ak, a.get("whiteapps"));
            }
            hashMap.put(an, Integer.valueOf(com.ishumei.d.b.a().c()));
            hashMap.put(ao, Integer.valueOf(com.ishumei.d.b.a().d()));
            hashMap.put(al, a(b == null ? null : b.h()));
            hashMap.put(am, b(b == null ? null : b.i()));
            f.a().d();
            hashMap.put(u, f.a().c());
            hashMap.put("smidstat", f.a().b());
            if (b != null) {
                hashMap.put(ap, b.f());
            }
            return hashMap;
        } catch (Exception e) {
            return hashMap;
        }
    }
}
