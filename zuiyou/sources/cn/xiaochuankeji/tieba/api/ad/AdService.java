package cn.xiaochuankeji.tieba.api.ad;

import cn.xiaochuankeji.tieba.background.ad.CheckAdBean;
import com.alibaba.fastjson.JSONObject;
import retrofit2.a.a;
import retrofit2.a.o;
import rx.d;

public interface AdService {
    @o(a = "/ad/audit")
    d<CheckAdBean> adAudit(@a JSONObject jSONObject);

    @o(a = "/ad/disgust")
    d<Void> disgustAd(@a JSONObject jSONObject);

    @o(a = "/stat/ad")
    d<Void> statAd(@a JSONObject jSONObject);
}
