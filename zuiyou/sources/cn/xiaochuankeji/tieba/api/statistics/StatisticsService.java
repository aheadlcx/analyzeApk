package cn.xiaochuankeji.tieba.api.statistics;

import cn.xiaochuankeji.tieba.json.StatisticsJson;
import com.alibaba.fastjson.JSONObject;
import retrofit2.a.a;
import retrofit2.a.o;
import rx.d;

public interface StatisticsService {
    @o(a = "/misc/c")
    d<StatisticsJson> uploadStatistics(@a JSONObject jSONObject);
}
