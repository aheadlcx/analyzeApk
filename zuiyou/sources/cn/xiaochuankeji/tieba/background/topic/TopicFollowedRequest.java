package cn.xiaochuankeji.tieba.background.topic;

import cn.xiaochuankeji.tieba.background.net.a;
import cn.xiaochuankeji.tieba.background.net.a.b;
import org.json.JSONObject;

public class TopicFollowedRequest extends a {
    public TopicFollowedRequest(b<JSONObject> bVar, a.a aVar) {
        super(cn.xiaochuankeji.tieba.background.utils.d.a.b("/topic/attention_list"), bVar, aVar);
    }
}
