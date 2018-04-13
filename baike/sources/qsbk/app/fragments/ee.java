package qsbk.app.fragments;

import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import java.io.File;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.LaiseeDetailActivity;
import qsbk.app.activity.pay.PayPWDUniversalActivity;
import qsbk.app.http.HttpCallBack;
import qsbk.app.utils.ToastAndDialog;

class ee implements HttpCallBack {
    final /* synthetic */ File a;
    final /* synthetic */ long b;
    final /* synthetic */ LaiseeVoiceGetFragment c;

    ee(LaiseeVoiceGetFragment laiseeVoiceGetFragment, File file, long j) {
        this.c = laiseeVoiceGetFragment;
        this.a = file;
        this.b = j;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
        this.c.q = false;
        if (jSONObject != null) {
            try {
                if (jSONObject.getInt(NotificationCompat.CATEGORY_ERROR) == 0) {
                    Object optString = jSONObject.optString(PayPWDUniversalActivity.MONEY);
                    if (!TextUtils.isEmpty(optString)) {
                        try {
                            this.c.o.gotMoney = Double.parseDouble(optString);
                        } catch (Exception e) {
                        }
                        Intent intent = new Intent(Constants.ACTION_GET_VOICE_LAISEE_SUCCESS);
                        intent.putExtra("voice_data", this.a.getAbsolutePath());
                        intent.putExtra("voice_duration", this.b / 1000);
                        intent.putExtra("conversationId", this.c.p);
                        LocalBroadcastManager.getInstance(QsbkApp.getInstance()).sendBroadcast(intent);
                        if (!this.c.isDetached() && this.c.isAdded()) {
                            LaiseeDetailActivity.launch(this.c.getActivity(), this.c.o);
                            this.c.getActivity().finish();
                        } else {
                            return;
                        }
                    }
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        this.c.b.setEnabled(true);
    }

    public void onFailure(String str, String str2) {
        this.c.q = false;
        this.c.b.setEnabled(true);
        this.c.b.setBackgroundResource(R.drawable.btn_voice_open);
        this.c.h.setVisibility(8);
        this.c.b.setVisibility(0);
        ToastAndDialog.makeNegativeToast(this.c.getActivity(), str2).show();
        this.c.i.setText("按住按钮读口令");
    }
}
