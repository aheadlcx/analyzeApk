package cn.xiaochuankeji.tieba.api.marketing;

import cn.xiaochuankeji.tieba.json.marketing.MarketingDivinationAnswer;
import retrofit2.a.o;
import rx.d;

public interface MarketingService {
    @o(a = "/market/divination")
    d<MarketingDivinationAnswer> fetchDivinationAnswer();
}
