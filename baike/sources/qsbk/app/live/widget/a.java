package qsbk.app.live.widget;

import android.app.Activity;
import java.util.HashMap;
import java.util.Map;
import qsbk.app.core.net.Callback;
import qsbk.app.core.net.response.BaseResponse;

class a extends Callback {
    final /* synthetic */ AdminListDialog a;

    a(AdminListDialog adminListDialog) {
        this.a = adminListDialog;
    }

    public void onPreExecute() {
        this.a.d.showProgressBar();
    }

    public void onSuccess(BaseResponse baseResponse) {
        this.a.j = baseResponse.getSimpleDataInt("t");
        this.a.refreshData(baseResponse.getListResponse("m", new b(this)));
        this.a.refreshTitle();
        this.a.refreshEmptyView();
    }

    public Map<String, String> getParams() {
        Map<String, String> hashMap = new HashMap();
        hashMap.put("room_id", Long.toString(this.a.h));
        return hashMap;
    }

    public void onFailed(int i, String str) {
        this.a.refreshData(null);
        this.a.refreshTitle();
        this.a.d.showError((Activity) this.a.a, i, new c(this), false);
    }
}
