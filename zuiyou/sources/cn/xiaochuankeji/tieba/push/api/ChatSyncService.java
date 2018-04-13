package cn.xiaochuankeji.tieba.push.api;

import cn.xiaochuankeji.tieba.json.EmptyJson;
import com.alibaba.fastjson.JSONObject;
import retrofit2.a.a;
import retrofit2.a.f;
import retrofit2.a.o;
import retrofit2.a.s;
import rx.d;

public interface ChatSyncService {
    @o(a = "/chat/hide_messages")
    d<EmptyJson> cleanMessages(@a JSONObject jSONObject);

    @o(a = "/chat/hide_message")
    d<EmptyJson> deleteMessage(@a JSONObject jSONObject);

    @o(a = "/chat/hide_session")
    d<EmptyJson> deleteSession(@a JSONObject jSONObject);

    @o(a = "/chat/messages")
    d<JSONObject> message(@a JSONObject jSONObject);

    @o(a = "/chat/read")
    d<EmptyJson> read(@a JSONObject jSONObject);

    @o(a = "/s/chat/say")
    d<JSONObject> send(@a JSONObject jSONObject);

    @o(a = "/chat/sessions")
    d<JSONObject> session(@a JSONObject jSONObject);

    @f(a = "/s/sync/fetch/{token}/{type}/{begin}/{end}/{count}")
    d<JSONObject> sync(@s(a = "token") String str, @s(a = "type") int i, @s(a = "begin") long j, @s(a = "end") long j2, @s(a = "count") int i2);
}
