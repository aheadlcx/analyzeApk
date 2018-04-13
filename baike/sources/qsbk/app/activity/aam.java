package qsbk.app.activity;

import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.HttpCallBack;
import qsbk.app.model.QiushiTopic;
import qsbk.app.utils.QiushiTopicBus;
import qsbk.app.utils.ToastAndDialog;

class aam implements HttpCallBack {
    final /* synthetic */ aal a;

    aam(aal aal) {
        this.a = aal;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
        this.a.b.a.d.getTopicSub().setClickable(true);
        if (this.a.b.a.n) {
            this.a.b.a.a.isSubscribed = false;
            QiushiTopic a = this.a.b.a.a;
            a.subcribeCount--;
            QiushiTopicBus.updateQiushiTopic(this.a.b.a.a, QiushiTopicActivity.TAG);
            ToastAndDialog.makePositiveToast(QsbkApp.mContext, "取消订阅成功").show();
        }
    }

    public void onFailure(String str, String str2) {
        this.a.b.a.d.getTopicSub().setClickable(true);
        if (this.a.b.a.n) {
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "取消订阅失败，请稍后重试！").show();
        }
    }
}
