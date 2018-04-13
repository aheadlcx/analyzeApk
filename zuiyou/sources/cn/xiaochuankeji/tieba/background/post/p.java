package cn.xiaochuankeji.tieba.background.post;

import cn.xiaochuankeji.tieba.background.net.a;
import cn.xiaochuankeji.tieba.background.net.a.b;
import com.iflytek.cloud.SpeechConstant;
import org.json.JSONException;
import org.json.JSONObject;

public class p extends a {
    public p(long j, long j2, String str, String str2, Object obj, b<JSONObject> bVar, a.a aVar) {
        super(cn.xiaochuankeji.tieba.background.utils.d.a.b("/post/vote"), a(j, j2, str, str2), obj, bVar, aVar);
    }

    public static JSONObject a(long j, long j2, String str, String str2) {
        JSONObject b = cn.xiaochuankeji.tieba.background.utils.d.a.b();
        try {
            b.put("pid", j);
            b.put(SpeechConstant.ISV_VID, j2);
            b.put("opt", str);
            if (str2 != null && str2.trim().length() > 0) {
                b.put("from", str2);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return b;
    }
}
