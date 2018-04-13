package cn.xiaochuankeji.tieba.background.danmaku;

import android.util.Pair;
import cn.htjyb.netlib.d;
import cn.htjyb.netlib.f;
import com.iflytek.cloud.SpeechConstant;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

public class b {
    private final long a;
    private final long b;
    private final boolean c;
    private a d;
    private d e;
    private int f;

    public interface a {
        void a(int i, int i2, String str);

        void a(Pair<Integer, Integer> pair, ArrayList<DanmakuItem> arrayList, ArrayList<DanmakuItem> arrayList2);
    }

    public b(long j, long j2, boolean z) {
        this.a = j;
        this.b = j2;
        this.c = z;
    }

    public void a(a aVar) {
        this.d = aVar;
    }

    public void a(int i, int i2) {
        b();
        b(i, i2);
    }

    public boolean a() {
        return this.e != null;
    }

    public void b() {
        if (this.e != null) {
            this.e.c();
            this.e = null;
        }
    }

    private void b(final int i, final int i2) {
        String b;
        com.izuiyou.a.a.b.b("startPos=" + i + ", endPos=" + i2);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("pid", this.a);
            jSONObject.put(SpeechConstant.ISV_VID, this.b);
            jSONObject.put("t", i);
            if (i2 > i) {
                jSONObject.put("et", i2);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        cn.xiaochuankeji.tieba.background.utils.d.a.a(jSONObject);
        if (this.c) {
            b = cn.xiaochuankeji.tieba.background.utils.d.a.b("/ugcvideo/danmaku/list");
        } else {
            b = cn.xiaochuankeji.tieba.background.utils.d.a.b("/danmaku/list");
        }
        this.e = new f(b, cn.xiaochuankeji.tieba.background.a.d(), jSONObject, new cn.htjyb.netlib.d.a(this) {
            final /* synthetic */ b c;

            public void onTaskFinish(d dVar) {
                this.c.e = null;
                if (dVar.c.a) {
                    this.c.a(i, i2, dVar.c.c);
                } else if (this.c.d != null) {
                    this.c.d.a(i, i2, dVar.c.c());
                }
            }
        });
        this.e.b();
    }

    private void a(int i, int i2, JSONObject jSONObject) {
        ArrayList fromJsonArray = DanmakuItem.fromJsonArray(jSONObject.optJSONArray("list"));
        ArrayList arrayList = new ArrayList();
        Iterator it = fromJsonArray.iterator();
        while (it.hasNext()) {
            DanmakuItem danmakuItem = (DanmakuItem) it.next();
            if (danmakuItem.isTop) {
                arrayList.add(danmakuItem);
            }
        }
        if ((jSONObject.optInt("more") == 1 ? 1 : null) != null) {
            this.f = jSONObject.optInt("t");
        } else if (i2 > i) {
            this.f = i2;
        } else {
            this.f = Integer.MAX_VALUE;
        }
        if (this.d != null) {
            this.d.a(new Pair(Integer.valueOf(i), Integer.valueOf(this.f)), fromJsonArray, arrayList);
        }
    }
}
