package cn.xiaochuankeji.tieba.api.subject;

import cn.xiaochuankeji.tieba.network.c;
import com.alibaba.fastjson.JSONObject;
import com.iflytek.cloud.SpeechConstant;
import rx.d;

public class a {
    private SubjectService a = ((SubjectService) c.a().a(SubjectService.class));

    public d<SubjectJson> a(long j) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(SpeechConstant.IST_SESSION_ID, Long.valueOf(j));
        return this.a.get(jSONObject);
    }
}
