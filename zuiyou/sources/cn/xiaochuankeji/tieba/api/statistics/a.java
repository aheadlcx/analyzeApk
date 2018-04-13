package cn.xiaochuankeji.tieba.api.statistics;

import cn.xiaochuankeji.tieba.json.StatisticsJson;
import cn.xiaochuankeji.tieba.network.c;
import com.alibaba.fastjson.JSONObject;
import rx.d;

public class a {
    private StatisticsService a = ((StatisticsService) c.a().a(StatisticsService.class));

    public d<StatisticsJson> a(String str) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("data", str);
        return this.a.uploadStatistics(jSONObject);
    }
}
