package cn.xiaochuankeji.tieba.background.utils.e;

import cn.htjyb.netlib.f;
import cn.xiaochuankeji.tieba.background.utils.d.a;
import com.izuiyou.a.a.b;
import java.util.HashMap;
import java.util.Map.Entry;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class d {
    private static d a;
    private e b;
    private HashMap<String, b> c = new HashMap();
    private HashMap<String, b> d = new HashMap();

    private d() {
    }

    public static d a() {
        if (a == null) {
            a = new d();
        }
        return a;
    }

    public void b() {
        if (!this.c.isEmpty() || !this.d.isEmpty()) {
            JSONObject jSONObject = new JSONObject();
            a.a(jSONObject);
            JSONArray jSONArray = new JSONArray();
            try {
                a(this.c, jSONArray);
                a(this.d, jSONArray);
                jSONObject.put("list", jSONArray);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            b.e("上报的视频,object:" + jSONObject);
            new f(a.b("/video/stat"), cn.xiaochuankeji.tieba.background.a.d(), jSONObject, new cn.htjyb.netlib.d.a(this) {
                final /* synthetic */ d a;

                {
                    this.a = r1;
                }

                public void onTaskFinish(cn.htjyb.netlib.d dVar) {
                    if (dVar.c.a) {
                        b.c("视频上报成功");
                    } else {
                        b.e("视频信息上报失败");
                    }
                }
            }).b();
            this.b = null;
            this.c.clear();
            this.d.clear();
        }
    }

    private void a(HashMap<String, b> hashMap, JSONArray jSONArray) throws JSONException {
        for (Entry value : hashMap.entrySet()) {
            jSONArray.put(((b) value.getValue()).a());
        }
    }

    public void a(String str, e eVar) {
        if (this.c.isEmpty()) {
            this.b = eVar;
        } else if (this.b != null) {
            eVar.n = this.b.f;
        }
        this.c.put(str, eVar);
    }

    public void a(String str, c cVar) {
        this.d.put(str, cVar);
    }

    public void a(a aVar) {
        if (aVar.e > 0) {
            JSONObject jSONObject = new JSONObject();
            a.a(jSONObject);
            JSONArray jSONArray = new JSONArray();
            try {
                jSONArray.put(aVar.a());
                jSONObject.put("list", jSONArray);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            new f(a.b("/video/stat"), cn.xiaochuankeji.tieba.background.a.d(), jSONObject, null).b();
        }
    }
}
