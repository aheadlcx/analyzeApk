package cn.xiaochuankeji.tieba.api.review;

import cn.xiaochuankeji.tieba.json.ForbidReplyJson;
import cn.xiaochuankeji.tieba.network.c;
import com.alibaba.fastjson.JSONObject;
import rx.d;

public class a {
    private ReviewService a = ((ReviewService) c.a().a(ReviewService.class));

    public d<ForbidReplyJson> a(long j, long j2, boolean z) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("pid", Long.valueOf(j));
        jSONObject.put("rid", Long.valueOf(j2));
        jSONObject.put("disable_reply", Integer.valueOf(z ? 1 : 0));
        return this.a.forbidReply(jSONObject);
    }
}
