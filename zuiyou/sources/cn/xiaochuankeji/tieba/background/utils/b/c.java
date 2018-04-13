package cn.xiaochuankeji.tieba.background.utils.b;

import cn.htjyb.netlib.d;
import cn.htjyb.netlib.f;
import org.json.JSONException;
import org.json.JSONObject;

public class c {
    private long a;
    private a b;

    interface a {
        void a(boolean z, long j, int i, String str);
    }

    c(long j, a aVar) {
        this.a = j;
        this.b = aVar;
    }

    void a() {
        String b = cn.xiaochuankeji.tieba.background.utils.d.a.b("/zyapi/upload/blockinit");
        JSONObject jSONObject = new JSONObject();
        cn.xiaochuankeji.tieba.background.utils.d.a.a(jSONObject);
        try {
            jSONObject.put("size", this.a);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        new f(b, cn.xiaochuankeji.tieba.background.a.d(), jSONObject, new cn.htjyb.netlib.d.a(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void onTaskFinish(d dVar) {
                if (dVar.c.a) {
                    JSONObject jSONObject = dVar.c.c;
                    this.a.b.a(true, jSONObject.optLong("uploadid"), jSONObject.optInt("bsize"), null);
                    return;
                }
                this.a.b.a(false, 0, 0, dVar.c.c());
            }
        }).b();
    }
}
