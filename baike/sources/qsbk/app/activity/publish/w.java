package qsbk.app.activity.publish;

import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.utils.LogUtil;

class w implements SimpleCallBack {
    final /* synthetic */ int a;
    final /* synthetic */ CirclePublishActivity b;

    w(CirclePublishActivity circlePublishActivity, int i) {
        this.b = circlePublishActivity;
        this.a = i;
    }

    public void onSuccess(JSONObject jSONObject) {
        try {
            LogUtil.d("upload image sucess : index = " + this.a);
            String string = jSONObject.getString("uptoken");
            if (!this.b.r()) {
                this.b.uploadImage(this.a, string);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            onFailure(0, null);
        }
    }

    public void onFailure(int i, String str) {
        this.b.a(this.a);
    }
}
