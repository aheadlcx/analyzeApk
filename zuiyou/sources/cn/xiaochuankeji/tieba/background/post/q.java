package cn.xiaochuankeji.tieba.background.post;

import cn.xiaochuankeji.tieba.background.net.a;
import cn.xiaochuankeji.tieba.background.net.a.b;
import com.iflytek.cloud.SpeechConstant;
import org.json.JSONException;
import org.json.JSONObject;

public class q extends a {
    public q(long j, long j2, Object obj, b<JSONObject> bVar, a.a aVar) {
        super(cn.xiaochuankeji.tieba.background.utils.d.a.b("/post/vote_detail"), a(j, j2), obj, bVar, aVar);
    }

    public static JSONObject a(long j, long j2) {
        JSONObject b = cn.xiaochuankeji.tieba.background.utils.d.a.b();
        try {
            b.put("pid", j);
            b.put(SpeechConstant.ISV_VID, j2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return b;
    }
}
