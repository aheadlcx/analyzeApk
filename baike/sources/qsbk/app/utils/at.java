package qsbk.app.utils;

import org.json.JSONObject;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.model.Remark;

class at implements SimpleCallBack {
    final /* synthetic */ Remark a;
    final /* synthetic */ RemarkManager$UploadCallBack b;
    final /* synthetic */ RemarkManager c;

    at(RemarkManager remarkManager, Remark remark, RemarkManager$UploadCallBack remarkManager$UploadCallBack) {
        this.c = remarkManager;
        this.a = remark;
        this.b = remarkManager$UploadCallBack;
    }

    public void onSuccess(JSONObject jSONObject) {
        this.c.insertOrUpdate(this.a, true);
        if (this.b != null) {
            this.b.uploadSucc();
        }
    }

    public void onFailure(int i, String str) {
        if (this.b != null) {
            this.b.uploadFail(str);
        }
    }
}
