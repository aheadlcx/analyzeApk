package qsbk.app.fragments;

import android.text.TextUtils;
import java.math.BigDecimal;
import org.json.JSONObject;
import qsbk.app.http.HttpCallBack;
import qsbk.app.utils.ToastAndDialog;

class eo implements HttpCallBack {
    final /* synthetic */ LaiseeVoiceSendFragment a;

    eo(LaiseeVoiceSendFragment laiseeVoiceSendFragment) {
        this.a = laiseeVoiceSendFragment;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
        if (this.a.isAdded() && !this.a.isDetached()) {
            Object optString = jSONObject.optString("rate");
            if (!TextUtils.isEmpty(optString)) {
                try {
                    this.a.K = Double.parseDouble(optString);
                } catch (Exception e) {
                    this.a.K = 0.0d;
                }
            }
            optString = jSONObject.optString("min");
            if (!TextUtils.isEmpty(optString)) {
                try {
                    this.a.O = Double.parseDouble(optString);
                } catch (Exception e2) {
                    this.a.O = 0.0d;
                }
            }
            this.a.L = new BigDecimal(jSONObject.optString("wallet_balance"));
            this.a.d();
        }
    }

    public void onFailure(String str, String str2) {
        if (this.a.isAdded() && !this.a.isDetached()) {
            ToastAndDialog.makeText(this.a.getActivity(), str2).show();
        }
    }
}
