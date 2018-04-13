package cn.xiaochuankeji.tieba.api.voice;

import cn.xiaochuankeji.tieba.json.voice.VoiceListJson;
import cn.xiaochuankeji.tieba.json.voice.VoicePublishJson;
import cn.xiaochuankeji.tieba.json.voice.VoiceTopicJson;
import com.alibaba.fastjson.JSONObject;
import retrofit2.a.a;
import retrofit2.a.o;
import rx.d;

public interface VoiceService {
    @o(a = "/voice/topics")
    d<VoiceTopicJson> getTopicList(@a JSONObject jSONObject);

    @o(a = "/post/create")
    d<VoicePublishJson> publishVoice(@a JSONObject jSONObject);

    @o(a = "/voice/list")
    d<VoiceListJson> voiceList(@a JSONObject jSONObject);
}
