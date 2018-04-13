package qsbk.app.live.ui.family;

import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import qsbk.app.core.net.Callback;
import qsbk.app.core.net.response.BaseResponse;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.utils.ToastUtil;
import qsbk.app.core.widget.refresh.SwipeRefreshLayoutBoth$SwipeRefreshLayoutDirection;
import qsbk.app.live.R;

class ay extends Callback {
    final /* synthetic */ FamilyMessageActivity a;

    ay(FamilyMessageActivity familyMessageActivity) {
        this.a = familyMessageActivity;
    }

    public void onPreExecute() {
    }

    public void onSuccess(BaseResponse baseResponse) {
        if (this.a.h == 1) {
            this.a.d.clear();
            this.a.b.notifyDataSetChanged();
        }
        Collection listResponse = baseResponse.getListResponse("data", new az(this));
        FamilyMessageActivity familyMessageActivity = this.a;
        boolean z = listResponse != null && listResponse.size() > 0;
        familyMessageActivity.f = z;
        if (this.a.f) {
            this.a.d.addAll(listResponse);
            this.a.b.notifyDataSetChanged();
        } else if (this.a.g.isRefreshing() && this.a.g.getDirection() == SwipeRefreshLayoutBoth$SwipeRefreshLayoutDirection.BOTTOM) {
            ToastUtil.Short(AppUtils.getInstance().getAppContext().getString(R.string.no_more_content));
        }
        if (this.a.d == null || this.a.d.isEmpty()) {
            this.a.j.show();
            this.a.j.setMultilineText(R.string.empty);
        } else {
            this.a.j.hide();
        }
        this.a.g.setEnabled(true);
        this.a.h = this.a.h + 1;
    }

    public Map<String, String> getParams() {
        Map<String, String> hashMap = new HashMap();
        if (this.a.i > 0) {
            hashMap.put("anchor", this.a.i + "");
        }
        hashMap.put(ParamKey.PAGE, this.a.h + "");
        return hashMap;
    }

    public void onFinished() {
        this.a.g.setRefreshing(false);
        this.a.e = false;
    }

    public void onFailed(int i, String str) {
        if (this.a.d.isEmpty()) {
            this.a.j.showError(this.a.getActivity(), i, this.a);
        } else {
            this.a.j.hide();
        }
        this.a.f = false;
    }
}
