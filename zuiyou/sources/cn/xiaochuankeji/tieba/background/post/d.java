package cn.xiaochuankeji.tieba.background.post;

import cn.htjyb.netlib.f;
import org.json.JSONException;
import org.json.JSONObject;

public class d {

    public interface a {
        void a(boolean z, boolean z2, String str);
    }

    public interface b {
        void a(boolean z, String str);
    }

    public void a(long j, String str, final b bVar) {
        if (j != 0) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("token", cn.xiaochuankeji.tieba.background.a.g().a());
                jSONObject.put("pid", j);
                jSONObject.put("from", str);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            cn.xiaochuankeji.tieba.background.utils.d.a.a(jSONObject);
            new f(cn.xiaochuankeji.tieba.background.utils.d.a.b("/post/like"), cn.xiaochuankeji.tieba.background.a.d(), jSONObject, new cn.htjyb.netlib.d.a(this) {
                final /* synthetic */ d b;

                public void onTaskFinish(cn.htjyb.netlib.d dVar) {
                    cn.htjyb.netlib.b.a aVar = dVar.c;
                    if (aVar.a) {
                        if (bVar != null) {
                            bVar.a(true, null);
                        }
                    } else if (bVar != null) {
                        bVar.a(false, aVar.c());
                    }
                }
            }).b();
        }
    }

    public void a(long j, long j2, String str, final a aVar) {
        JSONObject jSONObject = new JSONObject();
        cn.xiaochuankeji.tieba.background.utils.d.a.a(jSONObject);
        if (0 != j2) {
            try {
                jSONObject.put("tid", j2);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        jSONObject.put("pid", j);
        jSONObject.put("from", str);
        new f(cn.xiaochuankeji.tieba.background.utils.d.a.b("/post/dislike"), cn.xiaochuankeji.tieba.background.a.d(), jSONObject, new cn.htjyb.netlib.d.a(this) {
            final /* synthetic */ d b;

            public void onTaskFinish(cn.htjyb.netlib.d dVar) {
                boolean z = false;
                cn.htjyb.netlib.b.a aVar = dVar.c;
                if (aVar.a) {
                    if (aVar.c.optInt("block_topic") == 1) {
                        z = true;
                    }
                    if (aVar != null) {
                        aVar.a(true, z, null);
                    }
                } else if (aVar != null) {
                    aVar.a(false, false, aVar.c());
                }
            }
        }).b();
    }
}
