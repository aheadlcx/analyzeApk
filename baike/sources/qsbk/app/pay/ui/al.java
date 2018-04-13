package qsbk.app.pay.ui;

import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import qsbk.app.core.net.Callback;
import qsbk.app.core.net.response.BaseResponse;
import qsbk.app.core.utils.ToastUtil;
import qsbk.app.core.widget.refresh.SwipeRefreshLayoutBoth$SwipeRefreshLayoutDirection;
import qsbk.app.pay.R;

class al extends Callback {
    final /* synthetic */ WithdrawRecordFragment a;

    al(WithdrawRecordFragment withdrawRecordFragment) {
        this.a = withdrawRecordFragment;
    }

    public Map<String, String> getParams() {
        Map<String, String> hashMap = new HashMap();
        hashMap.put(ParamKey.PAGE, this.a.h + "");
        return hashMap;
    }

    public void onSuccess(BaseResponse baseResponse) {
        if (this.a.h == 1) {
            this.a.f.clear();
            this.a.c.notifyDataSetChanged();
        }
        this.a.g = baseResponse.getSimpleDataStr("total_money");
        this.a.c.setTotal(this.a.g);
        Collection listResponse = baseResponse.getListResponse("record", new am(this));
        WithdrawRecordFragment withdrawRecordFragment = this.a;
        boolean z = listResponse != null && listResponse.size() > 0;
        withdrawRecordFragment.j = z;
        if (this.a.j) {
            this.a.f.addAll(listResponse);
            this.a.c.notifyDataSetChanged();
        } else if (this.a.b.isRefreshing() && this.a.b.getDirection() == SwipeRefreshLayoutBoth$SwipeRefreshLayoutDirection.BOTTOM) {
            ToastUtil.Short(R.string.no_more_content);
        }
        this.a.h = this.a.h + 1;
        this.a.b.setRefreshing(true);
        if (this.a.f.isEmpty()) {
            this.a.e.setTextOnly(this.a.k == 0 ? R.string.pay_withdraw_no_record : R.string.pay_exchange_no_record);
            return;
        }
        this.a.e.hide();
        this.a.b.setVisibility(0);
    }

    public void onFinished() {
        this.a.i = false;
        this.a.b.setRefreshing(false);
    }

    public void onFailed(int i, String str) {
        if (this.a.f.isEmpty()) {
            this.a.e.showError(this.a.getActivity(), i, str, new an(this));
            this.a.b.setVisibility(8);
        } else {
            this.a.e.hide();
            this.a.b.setVisibility(0);
        }
        this.a.j = false;
    }
}
