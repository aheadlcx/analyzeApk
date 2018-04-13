package cn.xiaochuankeji.tieba.background.utils.b;

import cn.htjyb.netlib.d;
import org.json.JSONException;
import org.json.JSONObject;

class f {
    private String a;
    private String b;
    private a c;

    interface a {
        void a(boolean z, long j, String str);
    }

    f(String str, String str2, a aVar) {
        this.a = str2;
        this.b = str;
        this.c = aVar;
    }

    void a() {
        String b = cn.xiaochuankeji.tieba.background.utils.d.a.b("/video/gen_videothumb");
        JSONObject jSONObject = new JSONObject();
        cn.xiaochuankeji.tieba.background.utils.d.a.a(jSONObject);
        try {
            jSONObject.put("uri", this.b);
            jSONObject.put("md5", this.a);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        new cn.htjyb.netlib.f(b, cn.xiaochuankeji.tieba.background.a.d(), jSONObject, new cn.htjyb.netlib.d.a(this) {
            final /* synthetic */ f a;

            {
                this.a = r1;
            }

            public void onTaskFinish(d dVar) {
                if (dVar.c.a) {
                    this.a.c.a(true, dVar.c.c.optLong("id"), null);
                    return;
                }
                this.a.c.a(false, 0, dVar.c.c());
            }
        }).b();
    }
}
