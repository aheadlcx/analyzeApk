package qsbk.app.live.ui;

import java.util.Map;
import qsbk.app.core.model.CommonVideo;
import qsbk.app.core.net.Callback;
import qsbk.app.core.net.response.BaseResponse;
import qsbk.app.live.R;

class ea extends Callback {
    final /* synthetic */ Map a;
    final /* synthetic */ LivePullActivity b;

    ea(LivePullActivity livePullActivity, Map map) {
        this.b = livePullActivity;
        this.a = map;
    }

    public void onPreExecute() {
        this.b.showSavingDialog(R.string.header_hint_refresh_loading);
    }

    public Map<String, String> getParams() {
        this.a.put("creator_id", this.b.bz);
        return this.a;
    }

    public void onSuccess(BaseResponse baseResponse) {
        this.b.d = (CommonVideo) baseResponse.getResponse(new eb(this));
        if (this.b.d != null) {
            if (!(this.b.d.author == null || this.b.d.author.isFollow())) {
                this.b.v.setVisibility(0);
            }
            this.b.ap();
            this.b.j();
            this.b.J();
        } else {
            a(-1);
        }
        this.b.ak();
    }

    public void onFailed(int i, String str) {
        if (i == -1500) {
            this.b.an();
            this.b.c();
            return;
        }
        this.b.f(str);
        a(i);
    }

    private void a(int i) {
        this.b.bC.showError(this.b, i, this.b);
    }

    public void onFinished() {
        this.b.hideSavingDialog();
    }
}
