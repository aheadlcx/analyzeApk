package cn.xiaochuankeji.tieba.api.tale;

import cn.xiaochuankeji.tieba.json.EmptyJson;
import cn.xiaochuankeji.tieba.json.tale.FollowPostCreateJson;
import cn.xiaochuankeji.tieba.json.tale.FollowPostDetailJson;
import cn.xiaochuankeji.tieba.json.tale.FollowPostFeedJson;
import cn.xiaochuankeji.tieba.json.tale.TaleArticleIdsJson;
import cn.xiaochuankeji.tieba.json.tale.TaleCommentJson;
import cn.xiaochuankeji.tieba.json.tale.TaleCommentListJson;
import cn.xiaochuankeji.tieba.json.tale.TaleListJson;
import cn.xiaochuankeji.tieba.json.tale.TaleRepliesJson;
import cn.xiaochuankeji.tieba.json.tale.TaleThumbUserJson;
import cn.xiaochuankeji.tieba.json.tale.ThemeListJson;
import com.alibaba.fastjson.JSONObject;
import retrofit2.a.a;
import retrofit2.a.o;
import rx.d;

public interface TaleService {
    @o(a = "/tale/article/create")
    d<FollowPostCreateJson> create(@a JSONObject jSONObject);

    @o(a = "/tale/comment/create")
    d<TaleCommentJson> createComment(@a JSONObject jSONObject);

    @o(a = "/tale/article/delete")
    d<String> delete(@a JSONObject jSONObject);

    @o(a = "/tale/comment/delete")
    d<EmptyJson> deleteComments(@a JSONObject jSONObject);

    @o(a = "/tale/article/detail")
    d<FollowPostDetailJson> detail(@a JSONObject jSONObject);

    @o(a = "/tale/feeds/list")
    d<FollowPostFeedJson> feedList(@a JSONObject jSONObject);

    @o(a = "/tale/theme/get_article_ids")
    d<TaleArticleIdsJson> getArticleIds(@a JSONObject jSONObject);

    @o(a = "/tale/article/list_comments")
    d<TaleCommentListJson> getComments(@a JSONObject jSONObject);

    @o(a = "/tale/comment/get_replies")
    d<TaleRepliesJson> getReplies(@a JSONObject jSONObject);

    @o(a = "/tale/theme/list_articles")
    d<TaleListJson> listArticles(@a JSONObject jSONObject);

    @o(a = "/tale/my/articles")
    d<TaleListJson> myFollowPostList(@a JSONObject jSONObject);

    @o(a = "/tale/my/liked_articles")
    d<TaleListJson> myLikePostList(@a JSONObject jSONObject);

    @o(a = "/tale/my/themes")
    d<ThemeListJson> myThemes(@a JSONObject jSONObject);

    @o(a = "/tale/comment/like_op")
    d<EmptyJson> taleCommentThumb(@a JSONObject jSONObject);

    @o(a = "/tale/comment/liked_users")
    d<TaleThumbUserJson> taleCommentThumbUsers(@a JSONObject jSONObject);

    @o(a = "/tale/article/like_op")
    d<EmptyJson> taleThumb(@a JSONObject jSONObject);

    @o(a = "/tale/article/liked_users")
    d<TaleThumbUserJson> taleThumbUsers(@a JSONObject jSONObject);

    @o(a = "/tale/user/articles")
    d<TaleListJson> userFollowPosts(@a JSONObject jSONObject);
}
