package cn.xiaochuankeji.tieba.api.post;

import cn.xiaochuankeji.tieba.json.EmptyJson;
import cn.xiaochuankeji.tieba.json.UgcVideoShareJson;
import cn.xiaochuankeji.tieba.json.topic.TopicReportTediumJson;
import com.alibaba.fastjson.JSONObject;
import retrofit2.a.a;
import retrofit2.a.o;
import rx.d;

public interface OperateService {
    @o(a = "/recommend/block_topic")
    d<EmptyJson> blockTopic(@a JSONObject jSONObject);

    @o(a = "/favor/delpost")
    d<EmptyJson> cancelCollectPost(@a JSONObject jSONObject);

    @o(a = "/favor/addpost")
    d<EmptyJson> collectPost(@a JSONObject jSONObject);

    @o(a = "/post/delete")
    d<EmptyJson> deletePost(@a JSONObject jSONObject);

    @o(a = "/ugcvideo/post/delete")
    d<EmptyJson> deleteVideo(@a JSONObject jSONObject);

    @o(a = "/ugcvideo/post/like")
    d<EmptyJson> likeVideo(@a JSONObject jSONObject);

    @o(a = "/topic/black_user")
    d<EmptyJson> limitUser(@a JSONObject jSONObject);

    @o(a = "/topic/delete_post_in_topic")
    d<EmptyJson> removePost(@a JSONObject jSONObject);

    @o(a = "/report")
    d<EmptyJson> reportPost(@a JSONObject jSONObject);

    @o(a = "/post/disgust")
    d<TopicReportTediumJson> reportTedium(@a JSONObject jSONObject);

    @o(a = "/ugcvideo/post/share")
    d<UgcVideoShareJson> shareVideo(@a JSONObject jSONObject);
}
