package cn.xiaochuankeji.tieba.api.config;

import cn.xiaochuankeji.tieba.json.EmptyJson;
import com.alibaba.fastjson.JSONObject;
import retrofit2.a.a;
import retrofit2.a.f;
import retrofit2.a.o;
import retrofit2.a.x;
import retrofit2.b;
import rx.d;

public interface PushApiService {
    @o(a = "/bind/bind_clientid")
    d<EmptyJson> bindClientId(@a JSONObject jSONObject);

    @o
    d<EmptyJson> clickedCallback(@x String str, @a JSONObject jSONObject);

    @f(a = "/s/route/route")
    b<String> getRoute();

    @o(a = "/msgc/user/register")
    d<EmptyJson> register(@a JSONObject jSONObject);

    @o(a = "/bind/unbind_clientid")
    d<EmptyJson> unbindClientId(@a JSONObject jSONObject);
}
