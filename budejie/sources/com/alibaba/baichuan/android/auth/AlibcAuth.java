package com.alibaba.baichuan.android.auth;

import android.content.Intent;
import android.text.TextUtils;
import com.alibaba.baichuan.android.trade.AlibcContext;
import com.alibaba.baichuan.android.trade.adapter.ut.AlibcUserTracker;
import com.alibaba.baichuan.android.trade.constants.UserTrackerConstants;
import com.alibaba.baichuan.android.trade.utils.log.AlibcLogger;
import com.taobao.tao.remotebusiness.auth.RemoteAuth;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class AlibcAuth {
    static Map a = new ConcurrentHashMap();
    private static AlibcAuthRemote b;

    public static class a {
        public String a;
        public String b;
        public Set c;
        public boolean d;
        public e e;

        public a(String str, boolean z, e eVar) {
            this.b = str;
            this.d = z;
            this.e = eVar;
        }
    }

    static List a(String str) {
        return b != null ? b.getHintList(str) : null;
    }

    private static void a(int i) {
        Intent intent = new Intent(AlibcContext.context, AlibcAuthActivity.class);
        intent.addFlags(268435456);
        intent.putExtra("authId", i);
        AlibcContext.context.startActivity(intent);
    }

    static void a(String str, e eVar, boolean z, boolean z2) {
        new c(str, eVar, z2, z).execute(new String[0]);
    }

    static void a(Set set, e eVar, boolean z) {
        String c = d.a().c();
        if (set != null || !TextUtils.isEmpty(c)) {
            new com.alibaba.baichuan.android.auth.a.a().a(set, c, new AlibcAuth$b(eVar, c, z));
        } else if (eVar != null) {
            eVar.a(a.a.b, a.a.c);
        }
    }

    public static void auth(String str, String str2, boolean z, e eVar) {
        List c = c(str2);
        if (c == null || c.size() <= 0) {
            a aVar = new a(str2, z, eVar);
            aVar.a = str;
            int hashCode = aVar.hashCode();
            a.put(Integer.valueOf(hashCode), aVar);
            if (z) {
                a(hashCode);
                return;
            } else {
                a(str, eVar, false, true);
                return;
            }
        }
        auth(c, null, z, eVar);
    }

    public static void auth(List list, String str, boolean z, e eVar) {
        Collection c = c(str);
        Set hashSet = c == null ? null : new HashSet(c);
        if (hashSet != null) {
            hashSet.addAll(list);
        } else {
            hashSet = list == null ? new HashSet() : new HashSet(list);
        }
        if (hashSet == null || hashSet.size() <= 0) {
            eVar.a(a.a.b, a.a.c);
            return;
        }
        a aVar = new a(str, z, eVar);
        aVar.c = hashSet;
        int hashCode = aVar.hashCode();
        a.put(Integer.valueOf(hashCode), aVar);
        if (z) {
            a(hashCode);
        } else {
            a(hashSet, eVar, false);
        }
    }

    public static void authRefresh() {
        a(null, null, true);
    }

    private static List c(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        List arrayList = new ArrayList();
        arrayList.add(str);
        return arrayList;
    }

    public static void cleanAuth() {
        d.a().e();
    }

    private static void d(String str) {
        String str2 = (String) a.d.get(str);
        if (str2 == null) {
            str2 = "00";
        }
        AlibcUserTracker.getInstance().sendUseabilityFailure(UserTrackerConstants.P_BCPCSDK, "Mtop_Auth", "2101" + str2, "授权失败");
    }

    public static void init() {
        AlibcLogger.d("Alibc", "AlibcAuth init");
        RemoteAuth.setAuthImpl(f.a());
    }

    public static void postHintList(String str, String str2) {
        List c = c(str2);
        if (b != null && c != null) {
            b.postHintList(str, c);
        }
    }

    public static void registAuthEvent(AlibcAuthRemote alibcAuthRemote) {
        b = alibcAuthRemote;
    }

    public static void unregistAuthEvent() {
        b = null;
    }
}
