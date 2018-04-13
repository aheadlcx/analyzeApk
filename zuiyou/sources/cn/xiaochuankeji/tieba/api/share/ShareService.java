package cn.xiaochuankeji.tieba.api.share;

import cn.xiaochuankeji.tieba.json.EmptyJson;
import com.alibaba.fastjson.JSONObject;
import retrofit2.a.a;
import retrofit2.a.o;
import rx.d;

public interface ShareService {
    @o(a = "/share/appreport")
    d<EmptyJson> shareReport(@a JSONObject jSONObject);
}
