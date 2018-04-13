package cn.xiaochuankeji.tieba.api.topic;

import android.text.TextUtils;
import cn.xiaochuankeji.tieba.json.EmptyJson;
import cn.xiaochuankeji.tieba.json.topic.QueryFobiddenJson;
import cn.xiaochuankeji.tieba.json.topic.TopicFollowerListJson;
import cn.xiaochuankeji.tieba.json.topic.TopicPostListJson;
import cn.xiaochuankeji.tieba.json.topic.TopicRoleApplyListJson;
import cn.xiaochuankeji.tieba.json.topic.TopicRoledListJson;
import cn.xiaochuankeji.tieba.json.topic.TopicTypeJson;
import cn.xiaochuankeji.tieba.network.c;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import rx.d;

public class b {
    private TopicService a = ((TopicService) c.a().a(TopicService.class));

    public d<EmptyJson> a(long j, String str) {
        if (str.equals("apply") || str.equals("retire")) {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("tid", Long.valueOf(j));
            jSONObject.put("type", str);
            return this.a.applyAdmin(jSONObject);
        }
        throw new IllegalArgumentException("parameter type must be \"apply\" or \"retire\"");
    }

    public d<Void> a(long j, long j2, String str) {
        if (str.equals("agree") || str.equals("refuse") || str.equals("fire")) {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("tid", Long.valueOf(j));
            jSONObject.put("admin_mid", Long.valueOf(j2));
            jSONObject.put("type", str);
            return this.a.operateAdmin(jSONObject);
        }
        throw new IllegalArgumentException("parameter type must be \"agree\" or \"refuse\" or \"fire\"");
    }

    public d<Void> b(long j, String str) {
        if (str.equals("open") || str.equals("close")) {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("tid", Long.valueOf(j));
            jSONObject.put("type", str);
            return this.a.recruitAdmin(jSONObject);
        }
        throw new IllegalArgumentException("parameter type must be \"open\" or \"close\"");
    }

    public d<Void> a(long j, long j2, String str, long j3) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("tid", Long.valueOf(j));
        jSONObject.put("pid", Long.valueOf(j2));
        jSONObject.put("text", str);
        jSONObject.put("img_id", Long.valueOf(j3));
        return this.a.setPostTop(jSONObject);
    }

    public d<Void> a(long j, long j2) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("tid", Long.valueOf(j));
        jSONObject.put("pid", Long.valueOf(j2));
        return this.a.delPostTop(jSONObject);
    }

    public d<TopicRoleApplyListJson> a(long j, int i, int i2) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("tid", Long.valueOf(j));
        jSONObject.put("t", Integer.valueOf(i));
        jSONObject.put("limit", Integer.valueOf(i2));
        return this.a.getApplyList(jSONObject);
    }

    public d<TopicRoledListJson> a(long j) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("tid", Long.valueOf(j));
        return this.a.getRoledMembers(jSONObject);
    }

    public d<TopicFollowerListJson> a(long j, int i) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("tid", Long.valueOf(j));
        jSONObject.put("offset", Integer.valueOf(i));
        return this.a.getCommonAttendMember(jSONObject);
    }

    public d<TopicRoleApplyListJson> b(long j, long j2) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("tid", Long.valueOf(j));
        jSONObject.put("t", Long.valueOf(j2));
        return this.a.escortApplyList(jSONObject);
    }

    public d<EmptyJson> b(long j, long j2, String str) {
        if (str.equalsIgnoreCase("agree") || str.equalsIgnoreCase("refuse") || str.equalsIgnoreCase("fire")) {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("tid", Long.valueOf(j));
            jSONObject.put("guard_mid", Long.valueOf(j2));
            jSONObject.put("type", str);
            return this.a.escortOperate(jSONObject);
        }
        throw new IllegalArgumentException("type must be 'agree' or 'refuse' or 'fire'");
    }

    public d<EmptyJson> a(long j, long j2, int i) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("mid", Long.valueOf(j));
        jSONObject.put("tid", Long.valueOf(j2));
        jSONObject.put("reason", Integer.valueOf(i));
        return this.a.blockUserInTopic(jSONObject);
    }

    public d<EmptyJson> c(long j, long j2) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("pid", Long.valueOf(j));
        jSONObject.put("tid", Long.valueOf(j2));
        return this.a.updatePostInTopic(jSONObject);
    }

    public d<QueryFobiddenJson> b(long j) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("tid", Long.valueOf(j));
        return this.a.isForbidden(jSONObject);
    }

    public d<EmptyJson> a(long j, int i, String str) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("pid", Long.valueOf(j));
        jSONObject.put("reason", Integer.valueOf(i));
        if (!TextUtils.isEmpty(str)) {
            jSONObject.put("from", str);
        }
        return this.a.deletePostInTopic(jSONObject);
    }

    public d<EmptyJson> d(long j, long j2) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("pid", Long.valueOf(j));
        jSONObject.put("tid", Long.valueOf(j2));
        return this.a.passPostReportInTopic(jSONObject);
    }

    public d<TopicTypeJson> c(long j) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("tid", Long.valueOf(j));
        return this.a.getTopicType(jSONObject);
    }

    public d<TopicPostListJson> a(long j, String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("tid", Long.valueOf(j));
        jSONObject.put("next_cb", str);
        jSONObject.put("sort", str2);
        JSONArray jSONArray = new JSONArray();
        jSONArray.add(Integer.valueOf(1));
        jSONArray.add(Integer.valueOf(2));
        jSONArray.add(Integer.valueOf(3));
        jSONObject.put("c_types", jSONArray);
        return this.a.getPostList(jSONObject);
    }
}
