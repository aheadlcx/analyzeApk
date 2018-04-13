package cn.xiaochuankeji.tieba.api.post;

import cn.xiaochuankeji.tieba.json.post.LikedListJson;
import cn.xiaochuankeji.tieba.json.post.PostListJson;
import cn.xiaochuankeji.tieba.network.c;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class d {
    private PostListService a = ((PostListService) c.a().a(PostListService.class));
    private JSONArray b = new JSONArray();

    public d() {
        this.b.add(Integer.valueOf(1));
        this.b.add(Integer.valueOf(2));
    }

    public rx.d<PostListJson> a(long j) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("t", Long.valueOf(j));
        jSONObject.put("c_types", this.b);
        return this.a.loadMyPost(jSONObject);
    }

    public rx.d<PostListJson> a(long j, long j2) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("mid", Long.valueOf(j));
        jSONObject.put("t", Long.valueOf(j2));
        jSONObject.put("c_types", this.b);
        return this.a.loadUserPost(jSONObject);
    }

    public rx.d<PostListJson> b(long j, long j2) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("id", Long.valueOf(j));
        jSONObject.put("t", Long.valueOf(j2));
        jSONObject.put("c_types", this.b);
        return this.a.loadFavorPost(jSONObject);
    }

    public rx.d<LikedListJson> b(long j) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("offset", Long.valueOf(j));
        jSONObject.put("c_types", a(1, 2));
        return this.a.loadLikedPost(jSONObject);
    }

    static JSONArray a(int... iArr) {
        JSONArray jSONArray = new JSONArray();
        for (int valueOf : iArr) {
            jSONArray.add(Integer.valueOf(valueOf));
        }
        return jSONArray;
    }
}
