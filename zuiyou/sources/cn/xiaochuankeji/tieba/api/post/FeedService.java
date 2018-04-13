package cn.xiaochuankeji.tieba.api.post;

import cn.xiaochuankeji.tieba.json.feed.FeedListJson;
import cn.xiaochuankeji.tieba.json.feed.FeedMemberListJson;
import cn.xiaochuankeji.tieba.json.feed.FeedPostListJson;
import com.alibaba.fastjson.JSONObject;
import retrofit2.a.a;
import retrofit2.a.o;
import rx.d;

public interface FeedService {
    @o(a = "/attention/follow_list")
    d<FeedListJson> feedList(@a JSONObject jSONObject);

    @o(a = "/attention/follow_list")
    d<FeedPostListJson> feedPostList(@a JSONObject jSONObject);

    @o(a = "/attention/suggest")
    d<FeedMemberListJson> suggestMembers(@a JSONObject jSONObject);
}
