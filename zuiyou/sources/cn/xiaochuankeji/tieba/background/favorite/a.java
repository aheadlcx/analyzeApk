package cn.xiaochuankeji.tieba.background.favorite;

import cn.xiaochuankeji.tieba.background.net.a.b;
import org.json.JSONException;
import org.json.JSONObject;

public class a extends cn.xiaochuankeji.tieba.background.net.a {
    public a(long j, long j2, long j3, b<JSONObject> bVar, cn.xiaochuankeji.tieba.background.net.a.a aVar) {
        super(cn.xiaochuankeji.tieba.background.utils.d.a.b("/favor/addpost"), a(j, j2, j3), null, bVar, aVar);
    }

    public static JSONObject a(long j, long j2, long j3) {
        JSONObject b = cn.xiaochuankeji.tieba.background.utils.d.a.b();
        try {
            b.put("localid", j);
            b.put("id", j3);
            b.put("pid", j2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return b;
    }
}
