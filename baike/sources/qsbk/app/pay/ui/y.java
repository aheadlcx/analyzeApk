package qsbk.app.pay.ui;

import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.widget.TextView;
import org.json.JSONObject;
import qsbk.app.core.net.Callback;
import qsbk.app.core.net.response.BaseResponse;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.utils.PreferenceUtils;
import qsbk.app.pay.R;

class y extends Callback {
    final /* synthetic */ WithdrawActivity a;

    y(WithdrawActivity withdrawActivity) {
        this.a = withdrawActivity;
    }

    public void onSuccess(BaseResponse baseResponse) {
        super.onSuccess(baseResponse);
    }

    public void onSuccess(JSONObject jSONObject) {
        boolean z = true;
        if (jSONObject.optInt(NotificationCompat.CATEGORY_ERROR) == 0) {
            this.a.K = jSONObject.optString("exchange_info");
            this.a.z = jSONObject.optDouble("today");
            this.a.y = jSONObject.optDouble("valid");
            this.a.A = jSONObject.optDouble("total");
            this.a.t = jSONObject.optDouble("fee");
            this.a.u = (double) jSONObject.optInt("min_fee");
            this.a.C = jSONObject.optInt("up_limit");
            this.a.D = jSONObject.optInt("down_limit");
            this.a.H = jSONObject.optString("help_url");
            this.a.k.setText(this.a.y + "");
            this.a.l.setText(this.a.z + "");
            this.a.m.setText(this.a.A + "");
            if (jSONObject.optBoolean("fstwithdraw")) {
                String optString = jSONObject.optString("account");
                String optString2 = jSONObject.optString("name");
                PreferenceUtils.instance().putString("alipay_account_" + AppUtils.getInstance().getUserInfoProvider().getUserId(), optString);
                PreferenceUtils.instance().putString("alipay_name_" + AppUtils.getInstance().getUserInfoProvider().getUserId(), optString2);
                this.a.a();
            } else {
                PreferenceUtils.instance().putString("alipay_account_" + AppUtils.getInstance().getUserInfoProvider().getUserId(), "");
                PreferenceUtils.instance().putString("alipay_name_" + AppUtils.getInstance().getUserInfoProvider().getUserId(), "");
                this.a.a();
            }
            this.a.E = jSONObject.optLong("coupon");
            this.a.I = jSONObject.optInt("vcount");
            this.a.J = jSONObject.optDouble("vmoney");
            if (this.a.I > 0) {
                this.a.o.setVisibility(0);
                this.a.p.setText(this.a.getString(R.string.pay_withdraw_money_info, new Object[]{Integer.valueOf(this.a.I), Double.toString(this.a.J)}));
                this.a.p.setSelected(true);
            } else {
                this.a.o.setVisibility(8);
            }
            this.a.F = jSONObject.optString("info_url");
            this.a.G = jSONObject.optString("info_msg");
            if (TextUtils.isEmpty(this.a.G)) {
                this.a.q.setVisibility(8);
            } else {
                this.a.q.setVisibility(0);
                this.a.r.setText(this.a.G);
            }
            if (this.a.y < ((double) this.a.D)) {
                this.a.h.setText(this.a.getString(R.string.pay_withdraw_down_limit, new Object[]{Integer.toString(this.a.D)}));
                this.a.h.setEnabled(false);
            } else {
                this.a.h.setText(this.a.getString(R.string.pay_withdraw));
                this.a.h.setEnabled(true);
            }
            TextView z2 = this.a.s;
            if (this.a.y < 1.0d) {
                z = false;
            }
            z2.setEnabled(z);
        }
    }

    public void onFinished() {
        super.onFinished();
    }
}
