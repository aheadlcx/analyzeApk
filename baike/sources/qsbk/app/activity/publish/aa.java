package qsbk.app.activity.publish;

import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.SimpleCallBack;

class aa implements SimpleCallBack {
    final /* synthetic */ CirclePublishActivity a;

    aa(CirclePublishActivity circlePublishActivity) {
        this.a = circlePublishActivity;
    }

    public void onSuccess(JSONObject jSONObject) {
        this.a.H = jSONObject.optInt("rank");
        if (this.a.H > 0) {
            QsbkApp.currentUser.circleLevel = this.a.H;
        }
        this.a.circlePermisson.setOnClickListener(new ab(this));
    }

    public void onFailure(int i, String str) {
    }
}
