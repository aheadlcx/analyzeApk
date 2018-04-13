package qsbk.app.core.widget;

import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import java.util.HashMap;
import java.util.Map;
import qsbk.app.core.net.Callback;
import qsbk.app.core.net.response.BaseResponse;

class v extends Callback {
    final /* synthetic */ RefreshRecyclerView a;

    v(RefreshRecyclerView refreshRecyclerView) {
        this.a = refreshRecyclerView;
    }

    public Map<String, String> getParams() {
        if (this.a.o == null) {
            this.a.o = new HashMap();
        }
        this.a.o.put(ParamKey.PAGE, this.a.m + "");
        return this.a.o;
    }

    public void onSuccess(BaseResponse baseResponse) {
        this.a.a(baseResponse);
        if (this.a.i != null && !this.a.i.isEmpty()) {
            this.a.setVisibility(0);
        }
    }

    public void onFinished() {
        this.a.setRefreshing(false);
        this.a.d = false;
    }

    public void onFailed(int i, String str) {
        if (this.a.i.isEmpty()) {
        }
        if (this.a.i.isEmpty()) {
            this.a.setVisibility(8);
        } else {
            this.a.setVisibility(0);
        }
        this.a.e = false;
        if (this.a.q != null) {
            this.a.q.onFailed(i, str);
        }
    }
}
