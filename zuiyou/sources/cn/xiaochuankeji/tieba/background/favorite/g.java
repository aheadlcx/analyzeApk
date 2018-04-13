package cn.xiaochuankeji.tieba.background.favorite;

import cn.xiaochuankeji.tieba.background.net.a;
import cn.xiaochuankeji.tieba.background.net.a.b;
import org.json.JSONException;
import org.json.JSONObject;

public class g extends a {
    public g(long j, long j2, b<JSONObject> bVar, a.a aVar) {
        super(cn.xiaochuankeji.tieba.background.utils.d.a.b("/favor/delpost"), a(j, j2), null, bVar, aVar);
    }

    public static JSONObject a(long j, long j2) {
        JSONObject b = cn.xiaochuankeji.tieba.background.utils.d.a.b();
        try {
            b.put("localid", j);
            b.put("pid", j2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return b;
    }
}
