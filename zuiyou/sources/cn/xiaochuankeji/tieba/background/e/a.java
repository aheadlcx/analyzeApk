package cn.xiaochuankeji.tieba.background.e;

import cn.xiaochuankeji.tieba.background.net.a.b;
import org.json.JSONException;
import org.json.JSONObject;

public class a extends cn.xiaochuankeji.tieba.background.net.a {
    public a(long j, Object obj, b<JSONObject> bVar, cn.xiaochuankeji.tieba.background.net.a.a aVar) {
        super(cn.xiaochuankeji.tieba.background.utils.d.a.b("/attention/cancel"), a(j), obj, bVar, aVar);
    }

    public static JSONObject a(long j) {
        JSONObject b = cn.xiaochuankeji.tieba.background.utils.d.a.b();
        try {
            b.put("uid", j);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return b;
    }
}
