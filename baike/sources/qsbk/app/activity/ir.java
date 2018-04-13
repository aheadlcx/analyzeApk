package qsbk.app.activity;

import android.net.Uri;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.utils.ToastAndDialog;

class ir implements SimpleCallBack {
    final /* synthetic */ Uri a;
    final /* synthetic */ CircleTopicActivity b;

    ir(CircleTopicActivity circleTopicActivity, Uri uri) {
        this.b = circleTopicActivity;
        this.a = uri;
    }

    public void onSuccess(JSONObject jSONObject) {
        try {
            this.b.a(jSONObject.getString("uptoken"), this.a);
        } catch (JSONException e) {
            e.printStackTrace();
            onFailure(0, null);
        }
    }

    public void onFailure(int i, String str) {
        this.b.r();
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str, Integer.valueOf(0)).show();
    }
}
