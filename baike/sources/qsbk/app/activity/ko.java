package qsbk.app.activity;

import android.support.v4.app.NotificationCompat;
import org.json.JSONObject;
import qsbk.app.http.HttpCallBack;
import qsbk.app.utils.ToastAndDialog;

class ko implements HttpCallBack {
    final /* synthetic */ EventWindowActivity a;

    ko(EventWindowActivity eventWindowActivity) {
        this.a = eventWindowActivity;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
        if (!this.a.isFinishing()) {
            if (jSONObject.optInt(NotificationCompat.CATEGORY_ERROR, -1) == 0) {
                this.a.e();
                return;
            }
            ToastAndDialog.makeNegativeToast(this.a, "活动已结束，请继续关注下一期活动").show();
            onFailure(null, null);
        }
    }

    public void onFailure(String str, String str2) {
        if (!this.a.isFinishing()) {
            if ("活动不存在".equals(str2)) {
                ToastAndDialog.makeNegativeToast(this.a, "活动已结束，请继续关注下一期活动").show();
            }
            this.a.e();
        }
    }
}
