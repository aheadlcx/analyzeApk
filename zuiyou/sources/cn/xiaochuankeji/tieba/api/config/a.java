package cn.xiaochuankeji.tieba.api.config;

import cn.xiaochuankeji.tieba.background.beans.GrayConfigBean;
import cn.xiaochuankeji.tieba.json.GuideJson;
import cn.xiaochuankeji.tieba.json.SplashConfigJson;
import cn.xiaochuankeji.tieba.json.topic.TopImageConfigJson;
import cn.xiaochuankeji.tieba.network.c;
import com.alibaba.fastjson.JSONObject;
import rx.d;

public class a {
    private ConfigService a = ((ConfigService) c.a().a(ConfigService.class));

    public d<SplashConfigJson> a(String str, int i) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("size", str);
        jSONObject.put("version", Integer.valueOf(i));
        return this.a.getSplashConfig(jSONObject);
    }

    public d<TopImageConfigJson> a(int i) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("version", Integer.valueOf(i));
        return this.a.getTopImageConfig(jSONObject);
    }

    public d<GrayConfigBean> a() {
        return this.a.getGrayConfig();
    }

    public d<GuideJson> b() {
        return this.a.getGuideConfig();
    }
}
