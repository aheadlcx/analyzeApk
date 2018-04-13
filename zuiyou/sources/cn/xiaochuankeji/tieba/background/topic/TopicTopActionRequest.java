package cn.xiaochuankeji.tieba.background.topic;

import cn.xiaochuankeji.tieba.background.net.a;
import cn.xiaochuankeji.tieba.background.net.a.b;
import org.json.JSONException;
import org.json.JSONObject;

public class TopicTopActionRequest extends a {
    public TopicTopActionRequest(long j, Object obj, b<JSONObject> bVar, a.a aVar) {
        super(cn.xiaochuankeji.tieba.background.utils.d.a.b("/topic/top"), createParams(j), Long.valueOf(j), bVar, aVar);
    }

    public static JSONObject createParams(long j) {
        JSONObject b = cn.xiaochuankeji.tieba.background.utils.d.a.b();
        try {
            b.put("tid", j);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return b;
    }
}
