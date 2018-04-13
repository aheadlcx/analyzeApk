package qsbk.app.activity;

import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import com.tencent.open.SocialConstants;
import java.math.BigDecimal;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.R;
import qsbk.app.activity.pay.PayPWDUniversalActivity;
import qsbk.app.http.HttpCallBack;
import qsbk.app.utils.Util;

class agd implements HttpCallBack {
    final /* synthetic */ WalletBalanceActivity a;

    agd(WalletBalanceActivity walletBalanceActivity) {
        this.a = walletBalanceActivity;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
        boolean z = true;
        if (jSONObject.optInt(NotificationCompat.CATEGORY_ERROR, -1) == 0) {
            try {
                this.a.d.hide();
                JSONObject optJSONObject = jSONObject.optJSONObject("top");
                if (optJSONObject != null) {
                    this.a.l = optJSONObject.optString(SocialConstants.PARAM_APP_DESC);
                    this.a.m = optJSONObject.optString("action");
                    if (TextUtils.isEmpty(this.a.l)) {
                        this.a.c.setVisibility(8);
                    } else {
                        this.a.c.setVisibility(0);
                        this.a.c.setText(this.a.l);
                        this.a.c.setSingleLine();
                        this.a.c.setMarqueeEnable(true);
                        this.a.c.getViewTreeObserver().addOnGlobalLayoutListener(new age(this));
                    }
                    this.a.c.setOnClickListener(new agg(this));
                }
                JSONObject optJSONObject2 = jSONObject.optJSONObject("withdraw");
                if (optJSONObject2 != null) {
                    this.a.h = new BigDecimal(optJSONObject2.optString("min_money", "20"));
                    this.a.j = optJSONObject2.optString(SocialConstants.PARAM_APP_DESC);
                    this.a.i = optJSONObject2.optInt("binded") == 1;
                    this.a.n = optJSONObject2.optString("ali_account");
                }
                this.a.g = new BigDecimal(jSONObject.getString(PayPWDUniversalActivity.MONEY));
                this.a.a.setText("￥" + Util.formatMoney(this.a.g.doubleValue()));
                WalletBalanceActivity walletBalanceActivity = this.a;
                if (this.a.g.doubleValue() < this.a.h.doubleValue()) {
                    z = false;
                }
                walletBalanceActivity.k = z;
                this.a.i();
            } catch (JSONException e) {
                e.printStackTrace();
                onFailure(null, "数据解析错误");
            }
        }
    }

    public void onFailure(String str, String str2) {
        this.a.d.show("加载失败，点击重试", R.drawable.fail_img, true);
    }
}
