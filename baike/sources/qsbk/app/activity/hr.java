package qsbk.app.activity;

import org.json.JSONObject;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.utils.ToastAndDialog;

class hr implements SimpleCallBack {
    final /* synthetic */ CircleTopicActivity a;

    hr(CircleTopicActivity circleTopicActivity) {
        this.a = circleTopicActivity;
    }

    public void onSuccess(JSONObject jSONObject) {
        this.a.r();
        if (jSONObject.optInt("rank") < 9) {
            ToastAndDialog.makeNegativeToast(this.a, "圈等级LV9以上才能申请题主哦").show();
        } else {
            this.a.j();
        }
    }

    public void onFailure(int i, String str) {
        this.a.r();
        this.a.j();
    }
}
