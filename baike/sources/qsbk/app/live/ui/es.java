package qsbk.app.live.ui;

import java.util.List;
import qsbk.app.core.net.Callback;
import qsbk.app.core.net.response.BaseResponse;

class es extends Callback {
    final /* synthetic */ LivePushActivity a;

    es(LivePushActivity livePushActivity) {
        this.a = livePushActivity;
    }

    public void onSuccess(BaseResponse baseResponse) {
        if (baseResponse != null) {
            List listResponse = baseResponse.getListResponse("msg", new et(this));
            if (listResponse.size() > 0) {
                this.a.bS.setVisibility(0);
                this.a.cF = true;
                if (listResponse.contains(Integer.valueOf(1))) {
                    this.a.bW.setVisibility(0);
                }
                if (listResponse.contains(Integer.valueOf(2))) {
                    this.a.bV.setVisibility(0);
                }
                if (listResponse.contains(Integer.valueOf(3))) {
                    this.a.bX.setVisibility(0);
                }
                if (listResponse.contains(Integer.valueOf(4))) {
                    this.a.ca.setVisibility(0);
                }
                if (listResponse.contains(Integer.valueOf(5))) {
                    this.a.bY.setVisibility(0);
                }
                if (listResponse.contains(Integer.valueOf(6))) {
                    this.a.bZ.setVisibility(0);
                    return;
                }
                return;
            }
            this.a.bS.setVisibility(4);
        }
    }
}
