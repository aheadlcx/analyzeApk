package qsbk.app.activity;

import com.baidu.mobstat.StatService;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.utils.ToastAndDialog;

class kk implements SimpleCallBack {
    final /* synthetic */ EditInfoEntranceActivity a;
    final /* synthetic */ EditInfoEntranceActivity$EditItemSubmitter b;

    kk(EditInfoEntranceActivity$EditItemSubmitter editInfoEntranceActivity$EditItemSubmitter, EditInfoEntranceActivity editInfoEntranceActivity) {
        this.b = editInfoEntranceActivity$EditItemSubmitter;
        this.a = editInfoEntranceActivity;
    }

    public void onSuccess(JSONObject jSONObject) {
        this.b.b();
        StatService.onEvent(this.b.c, "update_user_info", "onSuccess");
    }

    public void onFailure(int i, String str) {
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str).show();
        StatService.onEvent(this.b.c, "update_user_info", str);
    }
}
