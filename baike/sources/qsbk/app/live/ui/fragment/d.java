package qsbk.app.live.ui.fragment;

import android.support.v4.app.FragmentActivity;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import com.tencent.connect.common.Constants;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import qsbk.app.core.model.User;
import qsbk.app.core.net.Callback;
import qsbk.app.core.net.response.BaseResponse;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.utils.ToastUtil;
import qsbk.app.core.widget.refresh.SwipeRefreshLayoutBoth$SwipeRefreshLayoutDirection;
import qsbk.app.live.R;
import qsbk.app.live.model.GiftRankData;
import qsbk.app.live.ui.GiftRankActivity;

class d extends Callback {
    final /* synthetic */ GiftRankFragment a;

    d(GiftRankFragment giftRankFragment) {
        this.a = giftRankFragment;
    }

    public void onSuccess(BaseResponse baseResponse) {
        if (this.a.f == 1) {
            this.a.i.clear();
            this.a.e.notifyDataSetChanged();
        }
        Collection<GiftRankData> listResponse = baseResponse.getListResponse(User.FEMALE, new e(this));
        for (GiftRankData giftRankData : listResponse) {
            giftRankData.a = baseResponse.parent.optJSONObject("t").optString(giftRankData.t).replace("$", giftRankData.a);
        }
        FragmentActivity activity = this.a.getActivity();
        if (activity != null && (activity instanceof GiftRankActivity)) {
            ((GiftRankActivity) activity).setTotalGift(baseResponse.getSimpleDataLong("s"));
        }
        GiftRankFragment giftRankFragment = this.a;
        boolean z = listResponse != null && listResponse.size() > 0;
        giftRankFragment.h = z;
        if (this.a.h) {
            this.a.i.addAll(listResponse);
            this.a.e.notifyDataSetChanged();
        } else if (this.a.a.isRefreshing() && this.a.a.getDirection() == SwipeRefreshLayoutBoth$SwipeRefreshLayoutDirection.BOTTOM) {
            ToastUtil.Short(this.a.getString(R.string.no_more_content));
        }
        this.a.f = this.a.f + 1;
        if (this.a.i == null || this.a.i.isEmpty()) {
            this.a.d.setTextOnly(AppUtils.getInstance().getAppContext().getString(R.string.empty));
        } else {
            this.a.d.hide();
        }
    }

    public Map<String, String> getParams() {
        Map<String, String> hashMap = new HashMap();
        hashMap.put(ParamKey.PAGE, this.a.f + "");
        hashMap.put("count", Constants.VIA_REPORT_TYPE_SHARE_TO_QQ);
        if (this.a.k != null) {
            hashMap.put("t_s", this.a.k.getOrigin() + "");
            hashMap.put("t_id", this.a.k.getOriginId() + "");
        }
        return hashMap;
    }

    public void onFinished() {
        this.a.g = false;
        this.a.a.setRefreshing(false);
    }

    public void onFailed(int i, String str) {
        if (this.a.i.isEmpty()) {
            this.a.d.showError(this.a.getActivity(), i, new f(this));
            this.a.a.setVisibility(8);
        } else {
            this.a.d.hide();
            this.a.a.setVisibility(0);
        }
        this.a.h = false;
    }
}
