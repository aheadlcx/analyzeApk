package cn.xiaochuankeji.tieba.background.e;

import android.text.TextUtils;
import cn.xiaochuankeji.tieba.background.net.a;
import org.json.JSONException;
import org.json.JSONObject;

public class b extends a {
    public b(long j, String str, Object obj, cn.xiaochuankeji.tieba.background.net.a.b<JSONObject> bVar, a.a aVar) {
        super(cn.xiaochuankeji.tieba.background.utils.d.a.b("/attention/add"), a(j, str), obj, bVar, aVar);
    }

    public static JSONObject a(long j, String str) {
        JSONObject b = cn.xiaochuankeji.tieba.background.utils.d.a.b();
        try {
            b.put("uid", j);
            if (!TextUtils.isEmpty("from")) {
                b.put("from", str);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return b;
    }
}
