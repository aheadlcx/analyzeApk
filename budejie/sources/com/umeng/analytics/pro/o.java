package com.umeng.analytics.pro;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

public class o {
    private static Context a;

    private static final class a {
        private static final o a = new o();

        private a() {
        }
    }

    private o() {
        if (a == null) {
        }
    }

    public static o a(Context context) {
        a = context;
        return a.a;
    }

    public void a(f fVar) {
        try {
            SQLiteDatabase a = b.a(a).a();
            String a2 = a.a(a);
            String a3 = q.a(System.currentTimeMillis());
            if (a2.equals("0")) {
                fVar.a("faild", false);
                return;
            }
            if (a2.equals(a3)) {
                a.b(a, fVar);
            } else {
                a.a(a, fVar);
            }
            b.a(a).c();
        } catch (Exception e) {
            fVar.a(Boolean.valueOf(false), false);
            by.e("load agg data error");
        } finally {
            b.a(a).c();
        }
    }

    public void a(f fVar, Map<List<String>, i> map) {
        try {
            a.a(b.a(a).b(), map.values());
            fVar.a("success", false);
        } catch (Exception e) {
            by.e("save agg data error");
        } finally {
            b.a(a).c();
        }
    }

    public JSONObject a() {
        JSONObject b;
        try {
            b = a.b(b.a(a).a());
        } catch (Exception e) {
            by.e("upload agg date error");
            return null;
        } finally {
            b.a(a).c();
        }
        return b;
    }

    public JSONObject b(f fVar) {
        JSONObject a;
        try {
            a = a.a(fVar, b.a(a).a());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            b.a(a).c();
        }
        return a;
    }

    public void a(f fVar, boolean z) {
        try {
            a.a(b.a(a).b(), z, fVar);
        } catch (Exception e) {
            by.e("notifyUploadSuccess error");
        } finally {
            b.a(a).c();
        }
    }

    public void a(f fVar, String str, long j, long j2) {
        try {
            a.a(b.a(a).b(), str, j, j2);
            fVar.a("success", false);
        } catch (Exception e) {
            by.e("package size to big or envelopeOverflowPackageCount exception");
        } finally {
            b.a(a).c();
        }
    }

    public void a(f fVar, List<String> list) {
        try {
            a.a(fVar, b.a(a).b(), (List) list);
        } catch (Exception e) {
            by.e("saveToLimitCKTable exception");
        } finally {
            b.a(a).c();
        }
    }

    public void b(f fVar, Map<String, k> map) {
        try {
            a.a(b.a(a).b(), (Map) map, fVar);
        } catch (Exception e) {
            by.e("arrgetated system buffer exception");
        } finally {
            b.a(a).c();
        }
    }

    public List<String> b() {
        List<String> c;
        try {
            c = a.c(b.a(a).a());
        } catch (Exception e) {
            by.e("loadCKToMemory exception");
            return null;
        } finally {
            b.a(a).c();
        }
        return c;
    }

    public void c(f fVar, Map<List<String>, i> map) {
        try {
            a.a(fVar, b.a(a).b(), map.values());
        } catch (Exception e) {
            by.e("cacheToData error");
        } finally {
            b.a(a).c();
        }
    }
}
