package cn.xiaochuankeji.tieba.api.post;

import cn.xiaochuankeji.tieba.background.assessor.ReportAssessResultJson;
import cn.xiaochuankeji.tieba.json.CheckUrlJson;
import cn.xiaochuankeji.tieba.json.ConvertImageIdJson;
import cn.xiaochuankeji.tieba.json.SubmitUrlJson;
import cn.xiaochuankeji.tieba.json.post.PostDetailJson;
import com.alibaba.fastjson.JSONObject;
import retrofit2.a.a;
import retrofit2.a.o;
import rx.d;

public interface PostService {
    @o(a = "/assessor/verify_post")
    d<ReportAssessResultJson> checkPost(@a JSONObject jSONObject);

    @o(a = "/post/pagecheck")
    d<CheckUrlJson> checkUrlCrawl(@a JSONObject jSONObject);

    @o(a = "/upload/genid")
    d<ConvertImageIdJson> convertMediaUrl(@a JSONObject jSONObject);

    @o(a = "/post/detail")
    d<PostDetailJson> getPostDetail(@a JSONObject jSONObject);

    @o(a = "/post/related_videos")
    d<JSONObject> relatedVideos(@a JSONObject jSONObject);

    @o(a = "review/hide_reviews")
    d<Void> setCommentHide(@a JSONObject jSONObject);

    @o(a = "/post/pagesubmit")
    d<SubmitUrlJson> submitUrlCrawl(@a JSONObject jSONObject);
}
