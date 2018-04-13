package cn.xiaochuankeji.tieba.background.topic;

import cn.htjyb.netlib.d;
import cn.htjyb.netlib.f;
import cn.xiaochuankeji.tieba.background.utils.d.a;
import cn.xiaochuankeji.tieba.background.utils.g;
import org.json.JSONException;
import org.json.JSONObject;

public class TopicUtilityClass {
    public static void asynchSendFollowRequest(long j, boolean z, String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        a.a(jSONObject);
        try {
            jSONObject.put("tid", j);
            jSONObject.put("from", str);
            jSONObject.put("click_cb", str2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        new f(a.b(z ? "/topic/attention" : "/topic/cancel_attention"), cn.xiaochuankeji.tieba.background.a.d(), jSONObject, new d.a() {
            public void onTaskFinish(d dVar) {
                if (!dVar.c.a) {
                    return;
                }
                if (dVar.a().contains("/topic/attention")) {
                    g.a("您已关注该话题");
                } else {
                    g.a("您已取消关注");
                }
            }
        }).b();
    }
}
