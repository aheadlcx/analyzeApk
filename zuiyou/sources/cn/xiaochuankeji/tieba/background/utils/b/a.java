package cn.xiaochuankeji.tieba.background.utils.b;

import cn.htjyb.netlib.d;
import cn.htjyb.netlib.f;
import org.json.JSONException;
import org.json.JSONObject;

class a {
    private String a;
    private a b;

    interface a {
        void a(boolean z, String str, String str2, String str3);
    }

    a(String str, a aVar) {
        this.a = str;
        this.b = aVar;
    }

    void a() {
        String b = cn.xiaochuankeji.tieba.background.utils.d.a.b("/zyapi/upload/allcheck");
        JSONObject jSONObject = new JSONObject();
        cn.xiaochuankeji.tieba.background.utils.d.a.a(jSONObject);
        try {
            jSONObject.put("md5", this.a);
            jSONObject.put("busstype", "zuiyou_video");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        new f(b, cn.xiaochuankeji.tieba.background.a.d(), jSONObject, new cn.htjyb.netlib.d.a(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onTaskFinish(d dVar) {
                if (dVar.c.a) {
                    JSONObject jSONObject = dVar.c.c;
                    this.a.b.a(true, jSONObject.optString("uri"), jSONObject.optString("md5"), null);
                    return;
                }
                this.a.b.a(false, null, null, dVar.c.c());
            }
        }).b();
    }
}
