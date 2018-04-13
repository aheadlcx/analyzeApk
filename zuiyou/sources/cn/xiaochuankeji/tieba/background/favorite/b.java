package cn.xiaochuankeji.tieba.background.favorite;

import cn.xiaochuankeji.tieba.background.net.a;
import org.json.JSONException;
import org.json.JSONObject;

public class b extends a {
    public b(long j, String str, cn.xiaochuankeji.tieba.background.net.a.b<JSONObject> bVar, a.a aVar) {
        super(cn.xiaochuankeji.tieba.background.utils.d.a.b("/favor/create"), a(j, str), null, bVar, aVar);
    }

    public static JSONObject a(long j, String str) {
        JSONObject b = cn.xiaochuankeji.tieba.background.utils.d.a.b();
        try {
            b.put("localid", j);
            b.put("name", str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return b;
    }
}
