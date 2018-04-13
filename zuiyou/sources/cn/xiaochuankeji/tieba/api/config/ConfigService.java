package cn.xiaochuankeji.tieba.api.config;

import cn.xiaochuankeji.tieba.background.beans.GrayConfigBean;
import cn.xiaochuankeji.tieba.json.EmptyJson;
import cn.xiaochuankeji.tieba.json.GuideJson;
import cn.xiaochuankeji.tieba.json.OSSTokenJson;
import cn.xiaochuankeji.tieba.json.SplashConfigJson;
import cn.xiaochuankeji.tieba.json.topic.TopImageConfigJson;
import com.alibaba.fastjson.JSONObject;
import retrofit2.a.a;
import retrofit2.a.o;
import rx.d;

public interface ConfigService {
    @o(a = "/stat/save_device_info")
    d<EmptyJson> deviceInfo(@a JSONObject jSONObject);

    @o(a = "/config/get_user_beta_conf")
    d<JSONObject> getBeta(@a JSONObject jSONObject);

    @o(a = "/config/gray")
    d<GrayConfigBean> getGrayConfig();

    @o(a = "/config/topic_attention_guide")
    d<GuideJson> getGuideConfig();

    @o(a = "/upload/oss_config")
    d<OSSTokenJson> getOssToken(@a JSONObject jSONObject);

    @o(a = "/upload/custom_auth")
    d<OSSTokenJson> getOssTokenAuth(@a JSONObject jSONObject);

    @o(a = "/config/splash_v2")
    d<SplashConfigJson> getSplashConfig(@a JSONObject jSONObject);

    @o(a = "/config/top_imgs")
    d<TopImageConfigJson> getTopImageConfig(@a JSONObject jSONObject);

    @o(a = "/config/update_user_beta_conf")
    d<EmptyJson> updateBeta(@a JSONObject jSONObject);

    @o(a = "/version/check")
    d<JSONObject> versionConfig(@a JSONObject jSONObject);
}
