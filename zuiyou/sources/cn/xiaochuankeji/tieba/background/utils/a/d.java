package cn.xiaochuankeji.tieba.background.utils.a;

import cn.xiaochuankeji.tieba.analyse.a;
import cn.xiaochuankeji.tieba.network.e;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.qq.e.comm.constants.Constants.KEYS;
import java.util.Map.Entry;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import rx.j;

public class d {
    private static d a;
    private static ConcurrentHashMap<String, JSONObject> b = new ConcurrentHashMap();
    private static ConcurrentHashMap<String, JSONObject> c = new ConcurrentHashMap();
    private Random d = new Random();

    private d() {
    }

    public static d a() {
        if (a == null) {
            synchronized (d.class) {
                if (a == null) {
                    a = new d();
                }
            }
        }
        return a;
    }

    public void a(String str, String str2) {
        if (b()) {
            try {
                c cVar = new c();
                cVar.f = str;
                cVar.a = str2;
                if (c == null) {
                    c = new ConcurrentHashMap();
                }
                c.put(str2, cVar.a());
                if (c.size() > 50) {
                    a(c, KEYS.BIZ);
                }
            } catch (Throwable e) {
                a.a(e);
            }
        }
    }

    public void a(long j, String str, int i, String str2, String str3, String str4) {
        try {
            c cVar = new c();
            cVar.c = j;
            cVar.f = str2;
            cVar.d = str;
            cVar.e = i;
            cVar.b = str4;
            cVar.a = str3;
            if (b == null) {
                b = new ConcurrentHashMap();
            }
            b.put(str3, cVar.a());
            if (b.size() > 50) {
                a(b, "net");
            }
        } catch (Throwable e) {
            a.a(e);
        }
    }

    public boolean b() {
        if (!e.a().a || this.d.nextInt(10) * 10 >= e.a().b) {
            return false;
        }
        return true;
    }

    public void c() {
        a(c, KEYS.BIZ);
        a(b, "net");
    }

    public void a(ConcurrentHashMap<String, JSONObject> concurrentHashMap, String str) {
        if (!concurrentHashMap.isEmpty()) {
            ConcurrentHashMap concurrentHashMap2 = new ConcurrentHashMap();
            concurrentHashMap2.putAll(concurrentHashMap);
            concurrentHashMap.clear();
            JSONObject jSONObject = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            try {
                a(concurrentHashMap2, jSONArray);
                jSONObject.put("list", jSONArray);
                jSONObject.put("tab", str);
                concurrentHashMap2.clear();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            new cn.xiaochuankeji.tieba.api.log.a().b(jSONObject).b(rx.f.a.c()).b(new j<Void>(this) {
                final /* synthetic */ d a;

                {
                    this.a = r1;
                }

                public /* synthetic */ void onNext(Object obj) {
                    a((Void) obj);
                }

                public void onCompleted() {
                }

                public void onError(Throwable th) {
                }

                public void a(Void voidR) {
                }
            });
        }
    }

    private void a(ConcurrentHashMap<String, JSONObject> concurrentHashMap, JSONArray jSONArray) throws JSONException {
        for (Entry value : concurrentHashMap.entrySet()) {
            jSONArray.add((JSONObject) value.getValue());
        }
    }
}
