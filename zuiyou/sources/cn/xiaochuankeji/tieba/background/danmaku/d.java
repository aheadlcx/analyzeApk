package cn.xiaochuankeji.tieba.background.danmaku;

import cn.xiaochuankeji.tieba.background.net.a;
import cn.xiaochuankeji.tieba.background.net.a.b;
import org.json.JSONException;
import org.json.JSONObject;

public class d extends a {
    public d(long j, Object obj, b<JSONObject> bVar, a.a aVar) {
        super(cn.xiaochuankeji.tieba.background.utils.d.a.b("/danmaku/like"), a(j), obj, bVar, aVar);
    }

    public static JSONObject a(long j) {
        JSONObject b = cn.xiaochuankeji.tieba.background.utils.d.a.b();
        try {
            b.put("id", j);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        cn.xiaochuankeji.tieba.background.utils.d.a.a(b);
        return b;
    }
}
