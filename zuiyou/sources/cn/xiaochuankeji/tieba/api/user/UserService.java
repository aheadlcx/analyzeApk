package cn.xiaochuankeji.tieba.api.user;

import cn.xiaochuankeji.tieba.json.EmptyJson;
import com.alibaba.fastjson.JSONObject;
import retrofit2.a.a;
import retrofit2.a.o;
import rx.d;

public interface UserService {
    @o(a = "/user/block")
    d<EmptyJson> blockSession(@a JSONObject jSONObject);

    @o(a = "/user/getblock")
    d<JSONObject> getBlockedUsers(@a JSONObject jSONObject);

    @o(a = "/user/unblock")
    d<JSONObject> getChatUnblockedUsers(@a JSONObject jSONObject);

    @o(a = "/setting/get_push")
    d<SettingJson> getSettingPush(@a JSONObject jSONObject);

    @o(a = "/report")
    d<String> reportUser(@a JSONObject jSONObject);

    @o(a = "/setting/push")
    d<JSONObject> sendSettingPush(@a JSONObject jSONObject);

    @o(a = "/user/unblock")
    d<String> unblockSession(@a JSONObject jSONObject);
}
