package qsbk.app.activity.publish;

import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.utils.ToastAndDialog;

class y implements SimpleCallBack {
    final /* synthetic */ CirclePublishActivity a;

    y(CirclePublishActivity circlePublishActivity) {
        this.a = circlePublishActivity;
    }

    public void onSuccess(JSONObject jSONObject) {
        try {
            String string = jSONObject.getString("uptoken");
            if (!this.a.r()) {
                this.a.uploadVideo(this.a.z.getFilePath(this.a), string);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            onFailure(0, null);
        }
    }

    public void onFailure(int i, String str) {
        ToastAndDialog.makeNegativeToast(this.a, str).show();
    }
}
