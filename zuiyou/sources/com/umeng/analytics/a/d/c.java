package com.umeng.analytics.a.d;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.meizu.cloud.pushsdk.networking.common.ANConstants;
import com.umeng.a.g;
import com.umeng.analytics.a.a.b;
import com.umeng.analytics.a.c.d;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

public class c {
    private static Context a;

    private static final class a {
        private static final c a = new c();
    }

    private c() {
        if (a == null) {
        }
    }

    public static c a(Context context) {
        a = context;
        return a.a;
    }

    public void a(com.umeng.analytics.a.b.a aVar) {
        try {
            SQLiteDatabase a = b.a(a).a();
            String a2 = com.umeng.analytics.a.a.a.a(a);
            String a3 = e.a(System.currentTimeMillis());
            if (a2.equals("0")) {
                aVar.a("faild", false);
                return;
            }
            if (a2.equals(a3)) {
                com.umeng.analytics.a.a.a.b(a, aVar);
            } else {
                com.umeng.analytics.a.a.a.a(a, aVar);
            }
            b.a(a).c();
        } catch (Exception e) {
            aVar.a(Boolean.valueOf(false), false);
            g.d("load agg data error");
        } finally {
            b.a(a).c();
        }
    }

    public void a(com.umeng.analytics.a.b.a aVar, Map<List<String>, com.umeng.analytics.a.c.b> map) {
        try {
            com.umeng.analytics.a.a.a.a(b.a(a).b(), map.values());
            aVar.a(ANConstants.SUCCESS, false);
        } catch (Exception e) {
            g.d("save agg data error");
        } finally {
            b.a(a).c();
        }
    }

    public JSONObject a() {
        JSONObject b;
        try {
            b = com.umeng.analytics.a.a.a.b(b.a(a).a());
        } catch (Exception e) {
            g.d("upload agg date error");
            return null;
        } finally {
            b.a(a).c();
        }
        return b;
    }

    public JSONObject b(com.umeng.analytics.a.b.a aVar) {
        JSONObject a;
        try {
            a = com.umeng.analytics.a.a.a.a(aVar, b.a(a).a());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            b.a(a).c();
        }
        return a;
    }

    public void a(com.umeng.analytics.a.b.a aVar, boolean z) {
        try {
            com.umeng.analytics.a.a.a.a(b.a(a).b(), z, aVar);
        } catch (Exception e) {
            g.d("notifyUploadSuccess error");
        } finally {
            b.a(a).c();
        }
    }

    public void a(com.umeng.analytics.a.b.a aVar, String str, long j, long j2) {
        try {
            com.umeng.analytics.a.a.a.a(b.a(a).b(), str, j, j2);
            aVar.a(ANConstants.SUCCESS, false);
        } catch (Exception e) {
            g.d("package size to big or envelopeOverflowPackageCount exception");
        } finally {
            b.a(a).c();
        }
    }

    public void a(com.umeng.analytics.a.b.a aVar, List<String> list) {
        try {
            com.umeng.analytics.a.a.a.a(aVar, b.a(a).b(), (List) list);
        } catch (Exception e) {
            g.d("saveToLimitCKTable exception");
        } finally {
            b.a(a).c();
        }
    }

    public void b(com.umeng.analytics.a.b.a aVar, Map<String, d> map) {
        try {
            com.umeng.analytics.a.a.a.a(b.a(a).b(), (Map) map, aVar);
        } catch (Exception e) {
            g.d("arrgetated system buffer exception");
        } finally {
            b.a(a).c();
        }
    }

    public List<String> b() {
        List<String> c;
        try {
            c = com.umeng.analytics.a.a.a.c(b.a(a).a());
        } catch (Exception e) {
            g.d("loadCKToMemory exception");
            return null;
        } finally {
            b.a(a).c();
        }
        return c;
    }

    public void c(com.umeng.analytics.a.b.a aVar, Map<List<String>, com.umeng.analytics.a.c.b> map) {
        try {
            com.umeng.analytics.a.a.a.a(aVar, b.a(a).b(), map.values());
        } catch (Exception e) {
            g.d("cacheToData error");
        } finally {
            b.a(a).c();
        }
    }
}
