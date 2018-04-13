package qsbk.app.live.ui.bag;

import java.util.HashMap;
import java.util.Map;
import qsbk.app.core.net.Callback;
import qsbk.app.core.net.response.BaseResponse;
import qsbk.app.core.utils.AppUtils;

class z extends Callback {
    final /* synthetic */ MarketPayDialog a;

    z(MarketPayDialog marketPayDialog) {
        this.a = marketPayDialog;
    }

    public Map<String, String> getParams() {
        Map<String, String> hashMap = new HashMap();
        hashMap.put("id", this.a.o + "");
        hashMap.put("days", this.a.q[this.a.r] + "");
        return hashMap;
    }

    public void onSuccess(BaseResponse baseResponse) {
        this.a.dismiss();
        new MarketPaySuccessDialog(this.a.getContext()).show();
        AppUtils.getInstance().getUserInfoProvider().setBalance(baseResponse.getSimpleDataLong("coin"));
    }
}
