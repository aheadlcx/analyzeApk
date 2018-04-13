package cn.xiaochuankeji.tieba.api.topic;

import cn.xiaochuankeji.tieba.json.EmptyJson;
import cn.xiaochuankeji.tieba.json.topic.QueryFobiddenJson;
import cn.xiaochuankeji.tieba.json.topic.TopicFollowerListJson;
import cn.xiaochuankeji.tieba.json.topic.TopicHotPostListJson;
import cn.xiaochuankeji.tieba.json.topic.TopicNewPostListJson;
import cn.xiaochuankeji.tieba.json.topic.TopicPostListJson;
import cn.xiaochuankeji.tieba.json.topic.TopicRoleApplyListJson;
import cn.xiaochuankeji.tieba.json.topic.TopicRoledListJson;
import cn.xiaochuankeji.tieba.json.topic.TopicTypeJson;
import com.alibaba.fastjson.JSONObject;
import retrofit2.a.a;
import retrofit2.a.o;
import rx.d;

public interface TopicService {
    @o(a = "/topic/admin_delete_posts")
    d<JSONObject> adminDeletePostList(@a JSONObject jSONObject);

    @o(a = "/topic/admin_apply")
    d<EmptyJson> applyAdmin(@a JSONObject jSONObject);

    @o(a = "/topic/guard_apply")
    d<EmptyJson> applyEscort(@a JSONObject jSONObject);

    @o(a = "/topic/black_user")
    d<EmptyJson> blockUserInTopic(@a JSONObject jSONObject);

    @o(a = "/topic/apply_role_permit")
    d<EmptyJson> checkRoleApply(@a JSONObject jSONObject);

    @o(a = "/topic/del_top_post")
    d<Void> delPostTop(@a JSONObject jSONObject);

    @o(a = "/topic/delete_post_in_topic")
    d<EmptyJson> deletePostInTopic(@a JSONObject jSONObject);

    @o(a = "/topic/guard_apply_list")
    d<TopicRoleApplyListJson> escortApplyList(@a JSONObject jSONObject);

    @o(a = "/topic/guard_operate")
    d<EmptyJson> escortOperate(@a JSONObject jSONObject);

    @o(a = "/topic/admin_apply_list")
    d<TopicRoleApplyListJson> getApplyList(@a JSONObject jSONObject);

    @o(a = "/topic/banner")
    d<JSONObject> getBanners(@a JSONObject jSONObject);

    @o(a = "/topic/atted_users")
    d<TopicFollowerListJson> getCommonAttendMember(@a JSONObject jSONObject);

    @o(a = "/topic/fans")
    d<TopicFollowerListJson> getFollowers(@a JSONObject jSONObject);

    @o(a = "/topic/hotposts_new")
    d<TopicHotPostListJson> getHotPostList(@a JSONObject jSONObject);

    @o(a = "/topic/posts")
    d<TopicNewPostListJson> getNewPostList(@a JSONObject jSONObject);

    @o(a = "/topic/posts_list")
    d<TopicPostListJson> getPostList(@a JSONObject jSONObject);

    @o(a = "/topic/role_list")
    d<TopicRoledListJson> getRoledMembers(@a JSONObject jSONObject);

    @o(a = "/recapi/getpartitionname")
    d<TopicTypeJson> getTopicType(@a JSONObject jSONObject);

    @o(a = "/topic/query_black")
    d<QueryFobiddenJson> isForbidden(@a JSONObject jSONObject);

    @o(a = "/topic/admin_operate")
    d<Void> operateAdmin(@a JSONObject jSONObject);

    @o(a = "/topic/pass_report")
    d<EmptyJson> passPostReportInTopic(@a JSONObject jSONObject);

    @o(a = "/topic/admin_recruit")
    d<Void> recruitAdmin(@a JSONObject jSONObject);

    @o(a = "/topic/set_top_post")
    d<Void> setPostTop(@a JSONObject jSONObject);

    @o(a = "/topic/update_post_topic")
    d<EmptyJson> updatePostInTopic(@a JSONObject jSONObject);
}
