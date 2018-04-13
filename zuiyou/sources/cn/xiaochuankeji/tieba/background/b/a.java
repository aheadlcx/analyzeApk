package cn.xiaochuankeji.tieba.background.b;

import cn.xiaochuankeji.tieba.background.net.a.b;
import org.json.JSONException;
import org.json.JSONObject;

public class a extends cn.xiaochuankeji.tieba.background.net.a {
    public a(long j, b<JSONObject> bVar, cn.xiaochuankeji.tieba.background.net.a.a aVar) {
        super(cn.xiaochuankeji.tieba.background.utils.d.a.b("/user/block"), a(j), null, bVar, aVar);
    }

    public static JSONObject a(long j) {
        JSONObject b = cn.xiaochuankeji.tieba.background.utils.d.a.b();
        try {
            b.put("block_id", j);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return b;
    }
}
