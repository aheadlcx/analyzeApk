package cn.xiaochuankeji.tieba.background.b;

import android.text.TextUtils;
import cn.xiaochuankeji.tieba.background.net.a;
import org.json.JSONException;
import org.json.JSONObject;

public class b extends a {
    public b(long j, String str, int i, String str2, cn.xiaochuankeji.tieba.background.net.a.b<JSONObject> bVar, a.a aVar) {
        this(0, j, str, i, str2, bVar, aVar);
    }

    public b(long j, long j2, String str, int i, String str2, cn.xiaochuankeji.tieba.background.net.a.b<JSONObject> bVar, a.a aVar) {
        super(cn.xiaochuankeji.tieba.background.utils.d.a.b("/report"), a(j, j2, str, i, str2), null, bVar, aVar);
    }

    private static JSONObject a(long j, long j2, String str, int i, String str2) {
        JSONObject b = cn.xiaochuankeji.tieba.background.utils.d.a.b();
        if (0 != j) {
            try {
                b.put("parentid", j);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        b.put("id", j2);
        b.put("type", str);
        b.put("reason", i);
        if (!TextUtils.isEmpty(str2)) {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("content", str2);
            b.put("data", jSONObject);
        }
        return b;
    }
}
