package cn.xiaochuankeji.tieba.background.post;

import cn.xiaochuankeji.tieba.background.net.a;
import cn.xiaochuankeji.tieba.background.net.a.b;
import org.json.JSONException;
import org.json.JSONObject;

public class i extends a {
    public i(long j, int i, Object obj, b<JSONObject> bVar, a.a aVar) {
        super(cn.xiaochuankeji.tieba.background.utils.d.a.b("/post/delete"), a(j, i), obj, bVar, aVar);
    }

    public static JSONObject a(long j, int i) {
        JSONObject b = cn.xiaochuankeji.tieba.background.utils.d.a.b();
        try {
            b.put("pid", j);
            b.put("reason", i);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return b;
    }
}
