package cn.xiaochuankeji.tieba.api.hollow;

import cn.xiaochuankeji.tieba.json.EmptyJson;
import cn.xiaochuankeji.tieba.json.hollow.EmotionListJson;
import cn.xiaochuankeji.tieba.json.hollow.HollowDetailJson;
import cn.xiaochuankeji.tieba.json.hollow.HollowJson;
import cn.xiaochuankeji.tieba.json.hollow.HollowListJson;
import cn.xiaochuankeji.tieba.json.hollow.HollowMsgJson;
import cn.xiaochuankeji.tieba.json.hollow.HollowMsgListJson;
import cn.xiaochuankeji.tieba.json.hollow.MyHollowListJson;
import cn.xiaochuankeji.tieba.json.hollow.MyReplyListJson;
import cn.xiaochuankeji.tieba.json.hollow.NickNameListJson;
import cn.xiaochuankeji.tieba.network.c;
import cn.xiaochuankeji.tieba.ui.hollow.data.AudioDataBean;
import com.alibaba.fastjson.JSONObject;
import com.iflytek.cloud.SpeechConstant;
import rx.d;

public class a {
    private HollowService a = ((HollowService) c.a().a(HollowService.class));

    public d<HollowListJson> a(String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("direction", str);
        jSONObject.put("next_cb", str2);
        jSONObject.put("exp", Integer.valueOf(1));
        return this.a.hollowRecommend(jSONObject);
    }

    public d<HollowJson> a(long j, String str, AudioDataBean audioDataBean, long j2, long j3, String str2) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("localid", Long.valueOf(j));
        jSONObject.put(SpeechConstant.SUBJECT, str);
        jSONObject.put("audio", audioDataBean);
        jSONObject.put("emotion_id", Long.valueOf(j2));
        jSONObject.put("name_id", Long.valueOf(j3));
        jSONObject.put("audio_text", str2);
        return this.a.createHollow(jSONObject);
    }

    public d<EmptyJson> a(long j) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("xroom_id", Long.valueOf(j));
        return this.a.deleteHollow(jSONObject);
    }

    public d<HollowDetailJson> a(long j, long j2, String str) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("xroom_id", Long.valueOf(j));
        if (j2 != 0) {
            jSONObject.put("xmsg_id", Long.valueOf(j2));
        }
        jSONObject.put("from", str);
        return this.a.hollowDetail(jSONObject);
    }

    public d<EmotionListJson> a() {
        return this.a.emotionList();
    }

    public d<HollowMsgJson> a(long j, long j2, String str, AudioDataBean audioDataBean) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("xroom_id", Long.valueOf(j));
        jSONObject.put("localid", Long.valueOf(j2));
        jSONObject.put("text", str);
        jSONObject.put("audio", audioDataBean);
        return this.a.createMsg(jSONObject);
    }

    public d<EmptyJson> b(long j) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("xmsg_id", Long.valueOf(j));
        return this.a.deleteMsg(jSONObject);
    }

    public d<HollowMsgListJson> a(long j, String str) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("xroom_id", Long.valueOf(j));
        jSONObject.put("next_cb", str);
        return this.a.msgList(jSONObject);
    }

    public d<MyHollowListJson> a(String str) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("next_cb", str);
        jSONObject.put("exp", Integer.valueOf(1));
        return this.a.myHollowList(jSONObject);
    }

    public d<MyReplyListJson> b(String str) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("next_cb", str);
        jSONObject.put("exp", Integer.valueOf(1));
        return this.a.myReplyList(jSONObject);
    }

    public d<NickNameListJson> b() {
        return this.a.nameList(new JSONObject());
    }

    public static d<EmptyJson> c(long j) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("xroom_id", Long.valueOf(j));
        return ((HollowService) c.a().a(HollowService.class)).hugHollow(jSONObject);
    }

    public static d<EmptyJson> d(long j) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("xroom_id", Long.valueOf(j));
        return ((HollowService) c.a().a(HollowService.class)).cancelHugHollow(jSONObject);
    }

    public static d<EmptyJson> a(long j, long j2) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("xroom_id", Long.valueOf(j));
        jSONObject.put("xmsg_id", Long.valueOf(j2));
        return ((HollowService) c.a().a(HollowService.class)).likeMsg(jSONObject);
    }

    public static d<EmptyJson> b(long j, long j2) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("xroom_id", Long.valueOf(j));
        jSONObject.put("xmsg_id", Long.valueOf(j2));
        return ((HollowService) c.a().a(HollowService.class)).cancelLikeMsg(jSONObject);
    }
}
