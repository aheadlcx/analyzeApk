package qsbk.app.live.debug;

import java.util.Map;
import qsbk.app.core.net.Callback;
import qsbk.app.core.net.response.BaseResponse;

class h extends Callback {
    final /* synthetic */ LiveDebugListFragment a;

    h(LiveDebugListFragment liveDebugListFragment) {
        this.a = liveDebugListFragment;
    }

    public Map<String, String> getParams() {
        return this.a.f();
    }

    public void onSuccess(BaseResponse baseResponse) {
        this.a.a(baseResponse);
        if (this.a.d == null || this.a.d.isEmpty()) {
            this.a.j.show();
            return;
        }
        this.a.j.hide();
        this.a.g.setVisibility(0);
    }

    public void onFinished() {
        this.a.g.setRefreshing(false);
        this.a.e = false;
    }

    public void onFailed(int i, String str) {
        if (this.a.d.isEmpty()) {
        }
        if (this.a.d.isEmpty()) {
            this.a.g.setVisibility(8);
        } else {
            this.a.j.hide();
            this.a.g.setVisibility(0);
        }
        this.a.f = false;
    }
}
