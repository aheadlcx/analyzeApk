package qsbk.app.live.share;

import java.util.HashMap;
import java.util.Map;
import qsbk.app.core.model.Share;
import qsbk.app.core.net.Callback;
import qsbk.app.core.net.response.BaseResponse;
import qsbk.app.core.utils.AppUtils;

class l extends Callback {
    final /* synthetic */ LiveShareActivity a;

    l(LiveShareActivity liveShareActivity) {
        this.a = liveShareActivity;
    }

    public Map<String, String> getParams() {
        Map<String, String> hashMap = new HashMap();
        hashMap.put("source", AppUtils.getInstance().getUserInfoProvider().getUserOrigin() + "");
        hashMap.put("source_id", AppUtils.getInstance().getUserInfoProvider().getUserId() + "");
        hashMap.put("s_type", this.a.h);
        hashMap.put("rv_id", Long.toString(this.a.c.isLiveVideo() ? this.a.c.room_id : this.a.c.id));
        return hashMap;
    }

    public void onSuccess(BaseResponse baseResponse) {
        Share share = (Share) baseResponse.getResponse("share", new m(this));
        if (share != null) {
            this.a.d = share;
        }
    }
}
