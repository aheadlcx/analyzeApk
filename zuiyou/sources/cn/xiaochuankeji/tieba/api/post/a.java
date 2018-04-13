package cn.xiaochuankeji.tieba.api.post;

import cn.xiaochuankeji.tieba.json.feed.FeedMemberListJson;
import cn.xiaochuankeji.tieba.json.feed.FeedPostListJson;
import cn.xiaochuankeji.tieba.network.c;
import com.alibaba.fastjson.JSONObject;
import rx.d;

public class a {
    private FeedService a = ((FeedService) c.a().a(FeedService.class));

    public d<FeedPostListJson> a(int i, String str, long j, long j2) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("auto", Integer.valueOf(i));
        jSONObject.put("direction", str);
        jSONObject.put("down_offset", Long.valueOf(j));
        jSONObject.put("up_offset", Long.valueOf(j2));
        jSONObject.put("c_types", d.a(1, 2));
        return this.a.feedPostList(jSONObject);
    }

    public d<FeedMemberListJson> a(long j) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("offset", Long.valueOf(j));
        return this.a.suggestMembers(jSONObject);
    }
}
