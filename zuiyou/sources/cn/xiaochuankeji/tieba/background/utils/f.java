package cn.xiaochuankeji.tieba.background.utils;

import cn.htjyb.netlib.b.b;
import cn.htjyb.netlib.d;
import cn.htjyb.netlib.g;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import org.json.JSONException;
import org.json.JSONObject;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

public class f {
    private a a;
    private String b;
    private String c;
    private g d;

    public interface a {
        void a(boolean z, String str, String str2);
    }

    public f(String str, String str2, a aVar) {
        this.b = str;
        this.c = str2;
        this.a = aVar;
    }

    public void a() {
        JSONObject jSONObject = new JSONObject();
        Collection collection = null;
        cn.xiaochuankeji.tieba.background.utils.d.a.a(jSONObject);
        try {
            jSONObject.put(IjkMediaMeta.IJKM_KEY_FORMAT, this.c);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (this.b != null) {
            collection = new ArrayList();
            collection.add(new b(new File(this.b), "file"));
        }
        this.d = new g(cn.xiaochuankeji.tieba.background.utils.d.a.b("/upload/audio"), cn.xiaochuankeji.tieba.background.a.d(), collection, jSONObject, new cn.htjyb.netlib.d.a(this) {
            final /* synthetic */ f a;

            {
                this.a = r1;
            }

            public void onTaskFinish(d dVar) {
                if (dVar.c.a) {
                    this.a.a.a(true, dVar.c.c.optString("uri"), null);
                    return;
                }
                this.a.a.a(false, null, dVar.c.c());
            }
        });
        this.d.b();
    }

    public void b() {
        if (this.d != null) {
            this.d.c();
            this.d = null;
        }
    }
}
