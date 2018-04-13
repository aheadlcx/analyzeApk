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
import com.alibaba.fastjson.JSONObject;
import retrofit2.a.a;
import retrofit2.a.o;
import rx.d;

public interface HollowService {
    @o(a = "/flow/xacnt/block")
    d<EmptyJson> blockXid(@a JSONObject jSONObject);

    @o(a = "/flow/xroom/cancel_hug")
    d<EmptyJson> cancelHugHollow(@a JSONObject jSONObject);

    @o(a = "/flow/xmsg/cancel_like")
    d<EmptyJson> cancelLikeMsg(@a JSONObject jSONObject);

    @o(a = "/flow/xacnt/get")
    d<JSONObject> createAccount(@a JSONObject jSONObject);

    @o(a = "/flow/xroom/create")
    d<HollowJson> createHollow(@a JSONObject jSONObject);

    @o(a = "/flow/xmsg/create")
    d<HollowMsgJson> createMsg(@a JSONObject jSONObject);

    @o(a = "/flow/xroom/delete")
    d<EmptyJson> deleteHollow(@a JSONObject jSONObject);

    @o(a = "/flow/xmsg/delete")
    d<EmptyJson> deleteMsg(@a JSONObject jSONObject);

    @o(a = "/flow/emotion/list")
    d<EmotionListJson> emotionList();

    @o(a = "/flow/xacnt/getblock")
    d<JSONObject> getBlock();

    @o(a = "/flow/xroom/detail")
    d<HollowDetailJson> hollowDetail(@a JSONObject jSONObject);

    @o(a = "/flow/xroom/list")
    d<HollowListJson> hollowRecommend(@a JSONObject jSONObject);

    @o(a = "/flow/xroom/hug")
    d<EmptyJson> hugHollow(@a JSONObject jSONObject);

    @o(a = "/flow/xmsg/like")
    d<EmptyJson> likeMsg(@a JSONObject jSONObject);

    @o(a = "/flow/xroom/listmsg")
    d<HollowMsgListJson> msgList(@a JSONObject jSONObject);

    @o(a = "/my/xroom_list")
    d<MyHollowListJson> myHollowList(@a JSONObject jSONObject);

    @o(a = "/my/xmsg_list")
    d<MyReplyListJson> myReplyList(@a JSONObject jSONObject);

    @o(a = "/flow/xacnt/listname")
    d<NickNameListJson> nameList(@a JSONObject jSONObject);

    @o(a = "/flow/xacnt/unblock")
    d<EmptyJson> unblockXid(@a JSONObject jSONObject);
}
