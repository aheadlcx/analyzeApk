package qsbk.app.activity;

import org.json.JSONObject;
import qsbk.app.http.HttpCallBack;
import qsbk.app.model.Laisee;

class qo implements HttpCallBack {
    final /* synthetic */ LaiseeDetailActivity a;

    qo(LaiseeDetailActivity laiseeDetailActivity) {
        this.a = laiseeDetailActivity;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
        if (jSONObject != null && !jSONObject.isNull("laisee")) {
            Laisee createLaiseeFromJson = Laisee.createLaiseeFromJson(jSONObject.optJSONObject("laisee"));
            if (createLaiseeFromJson == null) {
                this.a.finish();
                return;
            }
            createLaiseeFromJson.secret = this.a.f.secret;
            this.a.f = createLaiseeFromJson;
            this.a.i();
            this.a.b(this.a.f);
        }
    }

    public void onFailure(String str, String str2) {
    }
}
