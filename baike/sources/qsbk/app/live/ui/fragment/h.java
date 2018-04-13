package qsbk.app.live.ui.fragment;

import java.util.HashMap;
import java.util.Map;
import qsbk.app.core.net.Callback;
import qsbk.app.core.net.response.BaseResponse;
import qsbk.app.live.model.GiftRankData;

class h extends Callback {
    final /* synthetic */ GiftRankFragment a;

    h(GiftRankFragment giftRankFragment) {
        this.a = giftRankFragment;
    }

    public Map<String, String> getParams() {
        if (this.a.m == 1) {
            Map<String, String> hashMap = new HashMap();
            if (this.a.k != null) {
                hashMap.put("t_s", this.a.k.getOrigin() + "");
                hashMap.put("t_id", this.a.k.getOriginId() + "");
            }
            if (hashMap.size() > 0) {
                return hashMap;
            }
        }
        return super.getParams();
    }

    public void onSuccess(BaseResponse baseResponse) {
        this.a.o = (GiftRankData) baseResponse.getResponse("m", new i(this));
        if (this.a.o != null) {
            this.a.o.a = baseResponse.parent.optJSONObject("t").optString(this.a.o.t).replace("$", this.a.o.a);
            if (this.a.p == null) {
                this.a.a(this.a.o);
            }
        }
    }
}
