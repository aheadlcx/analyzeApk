package cn.xiaochuankeji.tieba.api.topic;

import cn.xiaochuankeji.tieba.json.BlockUserJson;
import cn.xiaochuankeji.tieba.json.ModifyMemberCoverJson;
import cn.xiaochuankeji.tieba.network.c;
import com.alibaba.fastjson.JSONObject;
import rx.d;

public class a {
    private MemberService a = ((MemberService) c.a().a(MemberService.class));

    public d<ModifyMemberCoverJson> a(long j) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("cover", Long.valueOf(j));
        return this.a.modifyMemberCover(jSONObject);
    }

    public d<BlockUserJson> a(int i, int i2) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("offset", Integer.valueOf(i));
        jSONObject.put("limit", Integer.valueOf(i2));
        return this.a.blockUserList(jSONObject);
    }
}
