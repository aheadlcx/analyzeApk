package cn.xiaochuankeji.tieba.background.topic;

import cn.xiaochuankeji.tieba.background.net.a;
import cn.xiaochuankeji.tieba.background.net.a.b;
import org.json.JSONException;
import org.json.JSONObject;

public class CancelBlockTopicActionRequest extends a {
    public CancelBlockTopicActionRequest(long j, String str, Object obj, b<JSONObject> bVar, a.a aVar) {
        super(cn.xiaochuankeji.tieba.background.utils.d.a.b("/recommend/cancel_topicblock"), createParams(j, str), obj, bVar, aVar);
    }

    public static JSONObject createParams(long j, String str) {
        JSONObject b = cn.xiaochuankeji.tieba.background.utils.d.a.b();
        try {
            b.put("token", str);
            b.put("tid", j);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return b;
    }
}
