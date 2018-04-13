package cn.xiaochuankeji.tieba.api.config;

import cn.xiaochuankeji.tieba.json.ABTestingJson;
import cn.xiaochuankeji.tieba.network.c;
import com.alibaba.fastjson.JSONObject;
import rx.d;

public class b {
    private OptionService a = ((OptionService) c.a().a(OptionService.class));

    public d<ABTestingJson> a(String str) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("campaign_id", str);
        return this.a.getAbTestingResult(jSONObject);
    }
}
