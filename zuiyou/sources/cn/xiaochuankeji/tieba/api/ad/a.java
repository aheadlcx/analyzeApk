package cn.xiaochuankeji.tieba.api.ad;

import cn.xiaochuankeji.tieba.background.ad.AdAuditBean;
import cn.xiaochuankeji.tieba.background.ad.CheckAdBean;
import cn.xiaochuankeji.tieba.network.c;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import rx.d;

public class a {
    private AdService a = ((AdService) c.a().a(AdService.class));

    public d<CheckAdBean> a(AdAuditBean adAuditBean) {
        return this.a.adAudit(JSON.parseObject(JSON.toJSONString(adAuditBean)));
    }

    public d<Void> a(JSONArray jSONArray) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("list", jSONArray);
        return this.a.statAd(jSONObject);
    }

    public d<Void> a(JSONObject jSONObject) {
        return this.a.disgustAd(jSONObject);
    }
}
