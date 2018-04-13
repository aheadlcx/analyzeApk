package cn.xiaochuankeji.tieba.background.favorite;

import cn.xiaochuankeji.tieba.background.net.a;
import cn.xiaochuankeji.tieba.background.net.a.b;
import org.json.JSONException;
import org.json.JSONObject;

public class d extends a {
    public d(long j, long j2, String str, b<JSONObject> bVar, a.a aVar) {
        super(cn.xiaochuankeji.tieba.background.utils.d.a.b("/favor/edit"), a(j, j2, str), null, bVar, aVar);
    }

    public static JSONObject a(long j, long j2, String str) {
        JSONObject b = cn.xiaochuankeji.tieba.background.utils.d.a.b();
        try {
            b.put("id", j);
            b.put("localid", j2);
            b.put("name", str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return b;
    }
}
