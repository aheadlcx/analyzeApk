package qsbk.app.activity;

import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.HttpCallBack;
import qsbk.app.utils.ToastAndDialog;

class adl implements HttpCallBack {
    final /* synthetic */ TemporaryNoteActivity a;

    adl(TemporaryNoteActivity temporaryNoteActivity) {
        this.a = temporaryNoteActivity;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
        ToastAndDialog.makePositiveToast(QsbkApp.mContext, "修改成功！", Integer.valueOf(0)).show();
        this.a.d = null;
    }

    public void onFailure(String str, String str2) {
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str2, Integer.valueOf(0)).show();
        this.a.d = null;
    }
}
