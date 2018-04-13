package qsbk.app.live.ui.family;

import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import com.tencent.connect.common.Constants;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import qsbk.app.core.model.User;
import qsbk.app.core.net.Callback;
import qsbk.app.core.net.response.BaseResponse;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.utils.ToastUtil;
import qsbk.app.core.widget.refresh.SwipeRefreshLayoutBoth$SwipeRefreshLayoutDirection;
import qsbk.app.live.R;
import qsbk.app.live.model.FamilyRankData;

class be extends Callback {
    final /* synthetic */ FamilyRankFragment a;

    be(FamilyRankFragment familyRankFragment) {
        this.a = familyRankFragment;
    }

    public void onSuccess(BaseResponse baseResponse) {
        if (this.a.h == 1) {
            this.a.c.clear();
            this.a.g.notifyDataSetChanged();
        }
        Collection<FamilyRankData> listResponse = baseResponse.getListResponse(User.FEMALE, new bf(this));
        for (FamilyRankData familyRankData : listResponse) {
            familyRankData.c = baseResponse.parent.optJSONObject("t").optString(familyRankData.t).replace("$", familyRankData.c);
        }
        FamilyRankFragment familyRankFragment = this.a;
        boolean z = listResponse != null && listResponse.size() > 0;
        familyRankFragment.j = z;
        if (this.a.j) {
            Iterator it = listResponse.iterator();
            while (it.hasNext()) {
                if (this.a.c.contains(it.next())) {
                    it.remove();
                }
            }
            this.a.c.addAll(listResponse);
            this.a.g.notifyDataSetChanged();
        } else if (this.a.e.isRefreshing() && this.a.e.getDirection() == SwipeRefreshLayoutBoth$SwipeRefreshLayoutDirection.BOTTOM) {
            ToastUtil.Short(this.a.getString(R.string.no_more_content));
        }
        this.a.h = this.a.h + 1;
        if (this.a.c == null || this.a.c.isEmpty()) {
            this.a.f.setTextOnly(AppUtils.getInstance().getAppContext().getString(R.string.empty));
        } else {
            this.a.f.hide();
        }
    }

    public Map<String, String> getParams() {
        Map<String, String> hashMap = new HashMap();
        hashMap.put(ParamKey.PAGE, this.a.h + "");
        hashMap.put("count", Constants.VIA_REPORT_TYPE_SHARE_TO_QQ);
        return hashMap;
    }

    public void onFinished() {
        this.a.i = false;
        this.a.e.setRefreshing(false);
    }

    public void onFailed(int i, String str) {
        if (this.a.c.isEmpty()) {
            this.a.f.showError(this.a.getActivity(), i, new bg(this));
            this.a.e.setVisibility(8);
        } else {
            this.a.f.hide();
            this.a.e.setVisibility(0);
        }
        this.a.j = false;
    }
}
