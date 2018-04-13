package qsbk.app.activity.publish;

import android.net.Uri;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.activity.publish.QiniuUploader.OnUploadListener;
import qsbk.app.http.SimpleCallBack;

final class cb implements SimpleCallBack {
    final /* synthetic */ String a;
    final /* synthetic */ OnUploadListener b;

    cb(String str, OnUploadListener onUploadListener) {
        this.a = str;
        this.b = onUploadListener;
    }

    public void onSuccess(JSONObject jSONObject) {
        try {
            QiniuUploader.b(this.a, jSONObject.getString("uptoken"), this.b);
        } catch (JSONException e) {
            e.printStackTrace();
            onFailure(0, null);
        }
    }

    public void onFailure(int i, String str) {
        if (this.b != null) {
            this.b.onUploadFail(Uri.parse(this.a));
        }
    }
}
