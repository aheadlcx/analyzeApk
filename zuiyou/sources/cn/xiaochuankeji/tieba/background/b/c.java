package cn.xiaochuankeji.tieba.background.b;

import cn.xiaochuankeji.tieba.background.net.a;
import cn.xiaochuankeji.tieba.background.net.a.b;
import org.json.JSONException;
import org.json.JSONObject;

public class c extends a {
    public c(long j, int i, b<JSONObject> bVar, a.a aVar) {
        super(cn.xiaochuankeji.tieba.background.utils.d.a.b("/user/unblock"), a(j, i), null, bVar, aVar);
    }

    public static JSONObject a(long j, int i) {
        JSONObject b = cn.xiaochuankeji.tieba.background.utils.d.a.b();
        try {
            b.put("block_id", j);
            b.put("type", i);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return b;
    }
}
