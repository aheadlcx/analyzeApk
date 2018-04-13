package cn.xiaochuankeji.tieba.api.post;

import android.text.TextUtils;
import cn.xiaochuankeji.tieba.json.EmptyJson;
import cn.xiaochuankeji.tieba.json.UgcVideoShareJson;
import cn.xiaochuankeji.tieba.json.topic.TopicReportTediumJson;
import cn.xiaochuankeji.tieba.network.c;
import com.alibaba.fastjson.JSONObject;
import java.util.List;
import rx.d;

public class b {
    private OperateService a = ((OperateService) c.a().a(OperateService.class));

    public d<EmptyJson> a(long j, int i) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("pid", Long.valueOf(j));
        jSONObject.put("reason", Integer.valueOf(i));
        return this.a.deletePost(jSONObject);
    }

    public d<EmptyJson> a(long j, long j2) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("localid", Long.valueOf(j));
        jSONObject.put("pid", Long.valueOf(j2));
        return this.a.cancelCollectPost(jSONObject);
    }

    public d<EmptyJson> a(long j, int i, String str) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("id", Long.valueOf(j));
        jSONObject.put("reason", Integer.valueOf(i));
        if (!TextUtils.isEmpty(str)) {
            jSONObject.put("type", str);
        }
        return this.a.reportPost(jSONObject);
    }

    public d<EmptyJson> b(long j, int i, String str) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("pid", Long.valueOf(j));
        jSONObject.put("reason", Integer.valueOf(i));
        if (!TextUtils.isEmpty(str)) {
            jSONObject.put("from", str);
        }
        return this.a.removePost(jSONObject);
    }

    public d<EmptyJson> a(long j) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("tid", Long.valueOf(j));
        return this.a.blockTopic(jSONObject);
    }

    public d<EmptyJson> a(long j, long j2, int i) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("mid", Long.valueOf(j));
        jSONObject.put("tid", Long.valueOf(j2));
        if (i > 0) {
            jSONObject.put("reason", Integer.valueOf(i));
        }
        return this.a.limitUser(jSONObject);
    }

    public d<TopicReportTediumJson> a(long j, long j2, List<String> list, String str, long j3) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("pid", Long.valueOf(j));
        jSONObject.put("reasons", list);
        jSONObject.put("tid", Long.valueOf(j2));
        if (!TextUtils.isEmpty(str)) {
            jSONObject.put("from", str);
        }
        jSONObject.put("is_new", Long.valueOf(j3));
        return this.a.reportTedium(jSONObject);
    }

    public d<EmptyJson> b(long j) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("pid", Long.valueOf(j));
        return this.a.deleteVideo(jSONObject);
    }

    public d<UgcVideoShareJson> a(long j, String str) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("pid", Long.valueOf(j));
        if (!TextUtils.isEmpty(str)) {
            jSONObject.put("from", str);
        }
        return this.a.shareVideo(jSONObject);
    }

    public d<EmptyJson> a(long j, boolean z, String str) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("pid", Long.valueOf(j));
        jSONObject.put("type", Integer.valueOf(z ? 1 : -1));
        if (!TextUtils.isEmpty(str)) {
            jSONObject.put("from", str);
        }
        return this.a.likeVideo(jSONObject);
    }
}
