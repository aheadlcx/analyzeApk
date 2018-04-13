package cn.xiaochuankeji.tieba.api.recommend;

import cn.xiaochuankeji.tieba.json.recommend.RecommendJson;
import cn.xiaochuankeji.tieba.network.c;
import com.alibaba.fastjson.JSONObject;
import rx.d;

public class a {
    private RecommendService a = ((RecommendService) c.a().a(RecommendService.class));

    public d<RecommendJson> a(String str, int i, String str2, String str3) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("filter", str);
        jSONObject.put("auto", Integer.valueOf(i));
        jSONObject.put("tab", str2);
        jSONObject.put("direction", str3);
        return this.a.recommend(jSONObject);
    }
}
