package qsbk.app.activity;

import org.json.JSONObject;
import qsbk.app.http.HttpCallBack;
import qsbk.app.model.Laisee;
import qsbk.app.utils.ToastAndDialog;

class qw implements HttpCallBack {
    final /* synthetic */ LaiseeGetActivity a;

    qw(LaiseeGetActivity laiseeGetActivity) {
        this.a = laiseeGetActivity;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
        if (!this.a.isFinishing() && jSONObject != null && jSONObject.has("laisee")) {
            Laisee createLaiseeFromJson = Laisee.createLaiseeFromJson(jSONObject.optJSONObject("laisee"));
            if (createLaiseeFromJson != null) {
                createLaiseeFromJson.secret = this.a.a.secret;
                this.a.a = createLaiseeFromJson;
                if (this.a.a.isGot() || (this.a.a.isP2P() && this.a.a.isMine())) {
                    LaiseeDetailActivity.launch(this.a, this.a.a);
                    this.a.finish();
                    return;
                }
                this.a.c.dismiss();
                this.a.d();
            }
        }
    }

    public void onFailure(String str, String str2) {
        if (!this.a.isFinishing()) {
            ToastAndDialog.makeNegativeToast(this.a, str2).show();
            this.a.c.dismiss();
            this.a.finish();
        }
    }
}
