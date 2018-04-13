package cn.xiaochuankeji.tieba.api.review;

import cn.xiaochuankeji.tieba.json.ForbidReplyJson;
import com.alibaba.fastjson.JSONObject;
import retrofit2.a.a;
import retrofit2.a.o;
import rx.d;

public interface ReviewService {
    @o(a = "/review/set_disable_reply")
    d<ForbidReplyJson> forbidReply(@a JSONObject jSONObject);
}
