package cn.xiaochuankeji.tieba.background.post;

import cn.xiaochuankeji.tieba.background.net.a;
import cn.xiaochuankeji.tieba.background.net.a.b;
import org.json.JSONException;
import org.json.JSONObject;

public class g extends a {
    public g(long j, Object obj, b<JSONObject> bVar, a.a aVar) {
        super(cn.xiaochuankeji.tieba.background.utils.d.a.b("/post/cancel_dislike"), a(j), obj, bVar, aVar);
    }

    public static JSONObject a(long j) {
        JSONObject b = cn.xiaochuankeji.tieba.background.utils.d.a.b();
        try {
            b.put("pid", j);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return b;
    }
}
