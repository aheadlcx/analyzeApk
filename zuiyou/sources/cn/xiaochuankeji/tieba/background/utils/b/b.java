package cn.xiaochuankeji.tieba.background.utils.b;

import cn.htjyb.netlib.d;
import cn.htjyb.netlib.f;
import org.json.JSONException;
import org.json.JSONObject;

public class b {
    private long a;
    private String b;
    private a c;

    interface a {
        void a(boolean z, String str, String str2, int i, String str3);
    }

    b(long j, String str, a aVar) {
        this.a = j;
        this.b = str;
        this.c = aVar;
    }

    void a() {
        String b = cn.xiaochuankeji.tieba.background.utils.d.a.b("/zyapi/upload/blockcomplete");
        JSONObject jSONObject = new JSONObject();
        cn.xiaochuankeji.tieba.background.utils.d.a.a(jSONObject);
        try {
            jSONObject.put("uploadid", this.a);
            jSONObject.put("busstype", "zuiyou_video");
            jSONObject.put("conttype", this.b);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        new f(b, cn.xiaochuankeji.tieba.background.a.d(), jSONObject, new cn.htjyb.netlib.d.a(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void onTaskFinish(d dVar) {
                if (dVar.c.a) {
                    JSONObject jSONObject = dVar.c.c;
                    this.a.c.a(true, jSONObject.optString("uri"), jSONObject.optString("md5"), 0, null);
                    return;
                }
                this.a.c.a(false, null, null, dVar.c.b, dVar.c.c());
            }
        }).b();
    }
}
