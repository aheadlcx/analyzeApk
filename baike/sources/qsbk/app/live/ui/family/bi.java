package qsbk.app.live.ui.family;

import qsbk.app.core.net.Callback;
import qsbk.app.core.net.response.BaseResponse;
import qsbk.app.live.model.FamilyRankData;

class bi extends Callback {
    final /* synthetic */ FamilyRankFragment a;

    bi(FamilyRankFragment familyRankFragment) {
        this.a = familyRankFragment;
    }

    public void onSuccess(BaseResponse baseResponse) {
        this.a.d = (FamilyRankData) baseResponse.getResponse("m", new bj(this));
        if (this.a.d != null) {
            this.a.d.c = baseResponse.parent.optJSONObject("t").optString(this.a.d.t).replace("$", this.a.d.c);
            this.a.a(this.a.d);
        }
    }
}
