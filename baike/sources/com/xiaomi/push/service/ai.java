package com.xiaomi.push.service;

import android.util.Pair;
import com.xiaomi.channel.commonutils.misc.b;
import com.xiaomi.xmpush.thrift.ac;
import com.xiaomi.xmpush.thrift.ad;
import com.xiaomi.xmpush.thrift.f;
import com.xiaomi.xmpush.thrift.g;
import com.xiaomi.xmpush.thrift.n;
import com.xiaomi.xmpush.thrift.p;
import java.util.ArrayList;
import java.util.List;

public class ai {
    public static int a(ah ahVar, f fVar) {
        int i = 0;
        String a = a(fVar);
        switch (u.a[fVar.ordinal()]) {
            case 1:
                i = 1;
                break;
        }
        return ahVar.a.getInt(a, i);
    }

    private static String a(f fVar) {
        return "oc_version_" + fVar.a();
    }

    private static List<Pair<Integer, Object>> a(List<p> list, boolean z) {
        if (b.a(list)) {
            return null;
        }
        List<Pair<Integer, Object>> arrayList = new ArrayList();
        for (p pVar : list) {
            int a = pVar.a();
            g a2 = g.a(pVar.c());
            if (a2 != null) {
                if (z && pVar.c) {
                    arrayList.add(new Pair(Integer.valueOf(a), null));
                } else {
                    Object obj;
                    Pair pair;
                    switch (u.b[a2.ordinal()]) {
                        case 1:
                            pair = new Pair(Integer.valueOf(a), Integer.valueOf(pVar.f()));
                            break;
                        case 2:
                            pair = new Pair(Integer.valueOf(a), Long.valueOf(pVar.h()));
                            break;
                        case 3:
                            pair = new Pair(Integer.valueOf(a), pVar.j());
                            break;
                        case 4:
                            pair = new Pair(Integer.valueOf(a), Boolean.valueOf(pVar.l()));
                            break;
                        default:
                            obj = null;
                            break;
                    }
                    arrayList.add(obj);
                }
            }
        }
        return arrayList;
    }

    public static void a(ah ahVar, ac acVar) {
        ahVar.b(a(acVar.a(), true));
    }

    public static void a(ah ahVar, ad adVar) {
        for (n nVar : adVar.a()) {
            if (nVar.a() > a(ahVar, nVar.d())) {
                a(ahVar, nVar.d(), nVar.a());
                ahVar.a(a(nVar.b, false));
            }
        }
    }

    public static void a(ah ahVar, f fVar, int i) {
        ahVar.a.edit().putInt(a(fVar), i).commit();
    }
}
