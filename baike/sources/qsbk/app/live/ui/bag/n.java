package qsbk.app.live.ui.bag;

import com.tencent.connect.common.Constants;
import java.util.HashMap;
import java.util.Map;
import qsbk.app.core.net.UrlConstants;
import qsbk.app.core.utils.ConfigInfoUtil.UpdateConfigCallback;

class n implements UpdateConfigCallback {
    final /* synthetic */ MarketFragment a;

    n(MarketFragment marketFragment) {
        this.a = marketFragment;
    }

    public void onSuccess() {
        this.a.d.clear();
        Map hashMap = new HashMap();
        hashMap.put("count", Constants.VIA_REPORT_TYPE_SHARE_TO_QQ);
        this.a.c.init(this.a.a, UrlConstants.LIVE_MARKET, hashMap, "jse", new o(this));
        this.a.a.setMarketItemClickListener(new p(this));
        this.a.c.setRefreshListener(new q(this));
    }

    public void onFailed(int i) {
    }

    public void onFinish() {
    }
}
