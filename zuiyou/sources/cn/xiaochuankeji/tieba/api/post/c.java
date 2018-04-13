package cn.xiaochuankeji.tieba.api.post;

import cn.xiaochuankeji.tieba.background.assessor.ReportAssessResultJson;
import cn.xiaochuankeji.tieba.json.CheckUrlJson;
import cn.xiaochuankeji.tieba.json.SubmitUrlJson;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import rx.d;

public class c {
    private PostService a = ((PostService) cn.xiaochuankeji.tieba.network.c.a().a(PostService.class));

    public d<SubmitUrlJson> a(String str) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("url", str);
        return this.a.submitUrlCrawl(jSONObject);
    }

    public d<CheckUrlJson> a(String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("url", str);
        jSONObject.put("res_id", str2);
        return this.a.checkUrlCrawl(jSONObject);
    }

    public d<Void> a(JSONArray jSONArray) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("data", jSONArray);
        return this.a.setCommentHide(jSONObject);
    }

    public d<ReportAssessResultJson> a(long j, String str, int i) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("pid", Long.valueOf(j));
        jSONObject.put("verify", str);
        if (i >= 0) {
            jSONObject.put("reason", Integer.valueOf(i));
        }
        return this.a.checkPost(jSONObject);
    }

    public d<JSONObject> a(long j, String str, long j2) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("pid", Long.valueOf(j));
        jSONObject.put("from", str);
        if (j2 > 0) {
            jSONObject.put("rid", Long.valueOf(j2));
        }
        return this.a.relatedVideos(jSONObject);
    }
}
