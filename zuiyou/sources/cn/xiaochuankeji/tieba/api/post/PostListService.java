package cn.xiaochuankeji.tieba.api.post;

import cn.xiaochuankeji.tieba.json.post.LikedListJson;
import cn.xiaochuankeji.tieba.json.post.PostListJson;
import com.alibaba.fastjson.JSONObject;
import retrofit2.a.a;
import retrofit2.a.o;
import rx.d;

public interface PostListService {
    @o(a = "/favor/posts")
    d<PostListJson> loadFavorPost(@a JSONObject jSONObject);

    @o(a = "/my/likedposts")
    d<LikedListJson> loadLikedPost(@a JSONObject jSONObject);

    @o(a = "/my/posts")
    d<PostListJson> loadMyPost(@a JSONObject jSONObject);

    @o(a = "/user/posts")
    d<PostListJson> loadUserPost(@a JSONObject jSONObject);
}
