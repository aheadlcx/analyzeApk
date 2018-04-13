package cn.xiaochuankeji.tieba.background.utils.monitor.a;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import cn.htjyb.netlib.d;
import cn.htjyb.netlib.f;
import com.tencent.tauth.AuthActivity;
import java.util.LinkedList;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class b implements Callback {
    private static b a;
    private final Handler b = new Handler(Looper.getMainLooper(), this);
    private d c;
    private LinkedList<a> d = new LinkedList();

    private static class a {
        String a;
        String b;
        long c;
        long d;
        String e;
        Map<String, Object> f;

        private a() {
        }
    }

    private b() {
    }

    public static b a() {
        if (a == null) {
            a = new b();
        }
        return a;
    }

    public void a(String str, String str2, Map<String, Object> map) {
        a(str, str2, 0, 0, null, map);
    }

    public void a(String str, String str2, long j, long j2, String str3, Map<String, Object> map) {
        a aVar = new a();
        aVar.a = str;
        aVar.b = str2;
        aVar.c = j;
        aVar.d = j2;
        aVar.e = str3;
        aVar.f = map;
        if (this.d.size() < 100) {
            this.d.add(aVar);
            b();
        }
    }

    private void b() {
        if (this.c == null && !this.d.isEmpty()) {
            int i = 5;
            JSONObject jSONObject = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            while (true) {
                int i2 = i - 1;
                if (i <= 0 || this.d.isEmpty()) {
                    try {
                        break;
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    a aVar = (a) this.d.remove(0);
                    JSONObject jSONObject2 = new JSONObject();
                    try {
                        jSONObject2.put(AuthActivity.ACTION_KEY, aVar.a);
                        jSONObject2.put("otype", aVar.b);
                        jSONObject2.put("id", aVar.c);
                        if (aVar.d > 0) {
                            jSONObject2.put("oid", aVar.d);
                        }
                        jSONObject2.put("src", aVar.e);
                        if (!(aVar.f == null || aVar.f.isEmpty())) {
                            jSONObject2.put("data", new JSONObject(aVar.f));
                        }
                        jSONArray.put(jSONObject2);
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                    }
                    i = i2;
                }
            }
            jSONObject.put("list", jSONArray);
            cn.xiaochuankeji.tieba.background.utils.d.a.a(jSONObject);
            this.c = new f(cn.xiaochuankeji.tieba.background.utils.d.a.b("/stat/action"), cn.xiaochuankeji.tieba.background.a.d(), jSONObject, new cn.htjyb.netlib.d.a(this) {
                final /* synthetic */ b a;

                {
                    this.a = r1;
                }

                public void onTaskFinish(d dVar) {
                    this.a.c = null;
                    this.a.b.sendEmptyMessage(1);
                }
            });
            this.c.b();
        }
    }

    public boolean handleMessage(Message message) {
        if (message.what == 1) {
            b();
        }
        return true;
    }
}
