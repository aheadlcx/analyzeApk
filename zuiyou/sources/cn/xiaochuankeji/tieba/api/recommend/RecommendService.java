package cn.xiaochuankeji.tieba.api.recommend;

import cn.xiaochuankeji.tieba.json.recommend.RecommendJson;
import com.alibaba.fastjson.JSONObject;
import retrofit2.a.a;
import retrofit2.a.o;
import rx.d;

public interface RecommendService {
    @o(a = "/index/recommend")
    d<RecommendJson> recommend(@a JSONObject jSONObject);
}
