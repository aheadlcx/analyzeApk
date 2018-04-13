package qsbk.app.live.widget;

import android.support.v4.app.NotificationCompat;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import qsbk.app.core.net.Callback;
import qsbk.app.core.net.response.BaseResponse;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.live.R;

class ce extends Callback {
    final /* synthetic */ GameMVPDialog a;

    ce(GameMVPDialog gameMVPDialog) {
        this.a = gameMVPDialog;
    }

    public Map<String, String> getParams() {
        Map hashMap = new HashMap();
        hashMap.put("gameid", String.valueOf(this.a.i));
        return hashMap;
    }

    public void onSuccess(BaseResponse baseResponse) {
        if (baseResponse.getSimpleDataInt(NotificationCompat.CATEGORY_ERROR) == 0) {
            Collection listResponse = baseResponse.getListResponse("msg", new cf(this));
            if (listResponse != null && listResponse.size() > 0) {
                this.a.h.clear();
                this.a.h.addAll(listResponse);
                this.a.e.notifyDataSetChanged();
            }
            if (this.a.h.isEmpty() && this.a.h.isEmpty()) {
                this.a.f.setTextOnly(AppUtils.getInstance().getAppContext().getString(R.string.live_game_no_mvp));
            } else {
                this.a.f.hide();
            }
        }
    }

    public void onFailed(int i, String str) {
        if (this.a.h.isEmpty() && this.a.h.isEmpty()) {
            this.a.f.showError(this.a.g, i, str, new cg(this));
        } else {
            this.a.f.hide();
        }
    }
}
