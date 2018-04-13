package cn.xiaochuankeji.tieba.api.tale;

import android.text.TextUtils;
import cn.xiaochuankeji.tieba.json.tale.FollowPostFeedJson;
import cn.xiaochuankeji.tieba.json.tale.TaleArticleIdsJson;
import cn.xiaochuankeji.tieba.json.tale.TaleListJson;
import cn.xiaochuankeji.tieba.json.tale.ThemeListJson;
import cn.xiaochuankeji.tieba.network.c;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import rx.d;

public class a {
    private TaleService a = ((TaleService) c.a().a(TaleService.class));

    public d<String> a(long j) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("id", Long.valueOf(j));
        return this.a.delete(jSONObject);
    }

    public d<FollowPostFeedJson> a(String str, int i, String str2) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("from", str);
        JSONArray jSONArray = new JSONArray();
        jSONArray.add("basic");
        jSONArray.add("member");
        jSONArray.add("stat");
        jSONArray.add("cover_articles");
        jSONObject.put("fields", jSONArray);
        jSONObject.put("cursor", str2);
        return this.a.feedList(jSONObject);
    }

    public d<TaleListJson> a(String str, long j, int i, String str2, JSONArray jSONArray, int i2) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("from", str);
        jSONObject.put("count", Integer.valueOf(i));
        jSONObject.put("id", Long.valueOf(j));
        jSONObject.put("article_ids", jSONArray);
        if (!TextUtils.isEmpty(str2)) {
            jSONObject.put("cursor", str2);
        }
        if (i2 != 0) {
            jSONObject.put("filter", Integer.valueOf(i2));
        }
        return this.a.listArticles(jSONObject);
    }

    public d<ThemeListJson> a(int i, String str) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("sort", "default");
        jSONObject.put("count", Integer.valueOf(i));
        if (!TextUtils.isEmpty(str)) {
            jSONObject.put("cursor", str);
        }
        return this.a.myThemes(jSONObject);
    }

    public d<TaleListJson> b(int i, String str) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("count", Integer.valueOf(i));
        if (!TextUtils.isEmpty(str)) {
            jSONObject.put("cursor", str);
        }
        return this.a.myFollowPostList(jSONObject);
    }

    public d<TaleListJson> c(int i, String str) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("count", Integer.valueOf(i));
        if (!TextUtils.isEmpty(str)) {
            jSONObject.put("cursor", str);
        }
        return this.a.myLikePostList(jSONObject);
    }

    public d<TaleListJson> a(long j, int i, String str) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("uid", Long.valueOf(j));
        jSONObject.put("count", Integer.valueOf(i));
        if (!TextUtils.isEmpty(str)) {
            jSONObject.put("cursor", str);
        }
        return this.a.userFollowPosts(jSONObject);
    }

    public d<TaleArticleIdsJson> a(String str, long j) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("from", str);
        jSONObject.put("article_id", Long.valueOf(j));
        return this.a.getArticleIds(jSONObject);
    }
}
