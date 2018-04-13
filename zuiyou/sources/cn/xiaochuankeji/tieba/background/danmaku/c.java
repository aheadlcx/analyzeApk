package cn.xiaochuankeji.tieba.background.danmaku;

import cn.xiaochuankeji.tieba.background.net.a;
import cn.xiaochuankeji.tieba.background.net.a.b;
import org.json.JSONException;
import org.json.JSONObject;

public class c extends a {
    public c(long j, Object obj, b<JSONObject> bVar, a.a aVar) {
        super(cn.xiaochuankeji.tieba.background.utils.d.a.b("/danmaku/dislike"), a(j), obj, bVar, aVar);
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
