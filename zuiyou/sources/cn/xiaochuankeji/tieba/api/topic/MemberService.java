package cn.xiaochuankeji.tieba.api.topic;

import cn.xiaochuankeji.tieba.json.BlockUserJson;
import cn.xiaochuankeji.tieba.json.ModifyMemberCoverJson;
import com.alibaba.fastjson.JSONObject;
import retrofit2.a.a;
import retrofit2.a.o;
import rx.d;

public interface MemberService {
    @o(a = "/my/blocked_users")
    d<BlockUserJson> blockUserList(@a JSONObject jSONObject);

    @o(a = "/account/update_cover")
    d<ModifyMemberCoverJson> modifyMemberCover(@a JSONObject jSONObject);
}
