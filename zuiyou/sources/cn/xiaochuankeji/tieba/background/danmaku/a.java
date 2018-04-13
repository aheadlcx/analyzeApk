package cn.xiaochuankeji.tieba.background.danmaku;

import cn.htjyb.netlib.d;
import cn.htjyb.netlib.f;
import com.iflytek.cloud.SpeechConstant;
import org.json.JSONException;
import org.json.JSONObject;

public class a {
    private String a;
    private long b;
    private long c;
    private long d;
    private long e;
    private long f;
    private a g;
    private f h;

    public interface a {
        void a(boolean z, String str, DanmakuItem danmakuItem);
    }

    public void a(long j, long j2, long j3, long j4, long j5, String str, a aVar) {
        if (aVar != null) {
            this.b = j;
            this.c = j2;
            this.d = j3;
            this.e = j4;
            this.f = j5;
            this.a = str;
            this.g = aVar;
            a();
            b();
        }
    }

    public void a() {
        if (this.h != null) {
            this.h.c();
            this.h = null;
        }
    }

    private void b() {
        JSONObject jSONObject = new JSONObject();
        cn.xiaochuankeji.tieba.background.utils.d.a.a(jSONObject);
        try {
            jSONObject.put("localid", System.currentTimeMillis());
            jSONObject.put("pid", this.b);
            jSONObject.put(SpeechConstant.ISV_VID, this.c);
            jSONObject.put("rid", this.d);
            if (0 != this.e) {
                jSONObject.put(SpeechConstant.IST_SESSION_ID, this.e);
            }
            jSONObject.put("snaptime", this.f);
            jSONObject.put("text", this.a);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.h = new f(cn.xiaochuankeji.tieba.background.utils.d.a.b("/danmaku/post"), cn.xiaochuankeji.tieba.background.a.d(), jSONObject, new cn.htjyb.netlib.d.a(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onTaskFinish(d dVar) {
                if (dVar.c.a) {
                    try {
                        this.a.g.a(true, null, DanmakuItem.fromJson(dVar.c.c.getJSONObject("danmaku")));
                        return;
                    } catch (JSONException e) {
                        e.printStackTrace();
                        this.a.g.a(false, e.getMessage(), null);
                        return;
                    }
                }
                this.a.g.a(false, dVar.c.c(), null);
            }
        });
        this.h.b();
    }
}
