package qsbk.app.im.CollectEmotion;

import android.net.Uri;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.utils.ToastAndDialog;

class m implements SimpleCallBack {
    final /* synthetic */ Uri a;
    final /* synthetic */ ShowCollectActivity b;

    m(ShowCollectActivity showCollectActivity, Uri uri) {
        this.b = showCollectActivity;
        this.a = uri;
    }

    public void onSuccess(JSONObject jSONObject) {
        try {
            String string = jSONObject.getString("uptoken");
            if (!this.b.j()) {
                this.b.a(string, this.a);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            onFailure(0, null);
        }
    }

    public void onFailure(int i, String str) {
        this.b.i();
        ToastAndDialog.makeNegativeToast(this.b, "上传失败，请重试...").show();
    }
}
