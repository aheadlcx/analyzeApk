package cn.xiaochuankeji.tieba.background.review;

import android.support.v4.app.NotificationCompat;
import cn.xiaochuankeji.tieba.background.net.a;
import cn.xiaochuankeji.tieba.background.net.a.b;
import org.json.JSONException;
import org.json.JSONObject;

public class h extends a {
    public h(long j, long j2, String str, int i, b<JSONObject> bVar, a.a aVar) {
        super(cn.xiaochuankeji.tieba.background.utils.d.a.b("/review/cancel_dislike"), a(j, j2, str, i), null, bVar, aVar);
    }

    public static JSONObject a(long j, long j2, String str, int i) {
        JSONObject b = cn.xiaochuankeji.tieba.background.utils.d.a.b();
        try {
            b.put("pid", j);
            b.put("rid", j2);
            b.put("from", str);
            b.put(NotificationCompat.CATEGORY_STATUS, i);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return b;
    }
}
