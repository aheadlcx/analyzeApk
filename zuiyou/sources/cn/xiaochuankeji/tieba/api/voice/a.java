package cn.xiaochuankeji.tieba.api.voice;

import cn.xiaochuankeji.tieba.json.voice.VoiceListJson;
import cn.xiaochuankeji.tieba.json.voice.VoiceTopicJson;
import cn.xiaochuankeji.tieba.network.c;
import com.alibaba.fastjson.JSONObject;
import rx.d;

public class a {
    private VoiceService a = ((VoiceService) c.a().a(VoiceService.class));

    public d<VoiceTopicJson> a(int i) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("offset", Integer.valueOf(i));
        return this.a.getTopicList(jSONObject);
    }

    public d<VoiceListJson> a(String str, boolean z) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("direction", str);
        jSONObject.put("auto", Integer.valueOf(z ? 1 : 0));
        return this.a.voiceList(jSONObject);
    }
}
