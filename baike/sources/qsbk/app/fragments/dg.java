package qsbk.app.fragments;

import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.activity.LaiseeDetailActivity;
import qsbk.app.activity.pay.PayPWDUniversalActivity;
import qsbk.app.http.HttpCallBack;
import qsbk.app.utils.ToastAndDialog;

class dg implements HttpCallBack {
    final /* synthetic */ LaiseeEventGetFragment a;

    dg(LaiseeEventGetFragment laiseeEventGetFragment) {
        this.a = laiseeEventGetFragment;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
        this.a.l = false;
        if (jSONObject != null) {
            try {
                if (jSONObject.getInt(NotificationCompat.CATEGORY_ERROR) == 0) {
                    Object optString = jSONObject.optString(PayPWDUniversalActivity.MONEY);
                    if (!TextUtils.isEmpty(optString)) {
                        try {
                            this.a.j.gotMoney = Double.parseDouble(optString);
                        } catch (Exception e) {
                        }
                        LaiseeDetailActivity.launch(this.a.getActivity(), this.a.j);
                        this.a.getActivity().finish();
                    }
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
    }

    public void onFailure(String str, String str2) {
        this.a.l = false;
        this.a.a();
        ToastAndDialog.makeNegativeToast(this.a.getActivity(), str2).show();
    }
}
