package cn.xiaochuankeji.tieba.background.favorite;

import cn.xiaochuankeji.tieba.background.net.a;
import cn.xiaochuankeji.tieba.background.net.a.b;
import org.json.JSONException;
import org.json.JSONObject;

public class c extends a {
    public c(long j, b<JSONObject> bVar, a.a aVar) {
        super(cn.xiaochuankeji.tieba.background.utils.d.a.b("/favor/delete"), a(j), null, bVar, aVar);
    }

    public static JSONObject a(long j) {
        JSONObject b = cn.xiaochuankeji.tieba.background.utils.d.a.b();
        try {
            b.put("id", j);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return b;
    }
}
