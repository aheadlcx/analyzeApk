package qsbk.app.activity;

import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.HttpCallBack;
import qsbk.app.model.QiushiTopic;
import qsbk.app.utils.QiushiTopicBus;
import qsbk.app.utils.ToastAndDialog;

class aak implements HttpCallBack {
    final /* synthetic */ aaj a;

    aak(aaj aaj) {
        this.a = aaj;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
        this.a.a.d.getTopicSub().setClickable(true);
        if (this.a.a.n) {
            this.a.a.a.isSubscribed = true;
            QiushiTopic a = this.a.a.a;
            a.subcribeCount++;
            QiushiTopicBus.updateQiushiTopic(this.a.a.a, QiushiTopicActivity.TAG);
            ToastAndDialog.makePositiveToast(QsbkApp.mContext, "订阅成功！").show();
        }
    }

    public void onFailure(String str, String str2) {
        this.a.a.d.getTopicSub().setClickable(true);
        if (this.a.a.n) {
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "订阅失败，请稍后重试！").show();
        }
    }
}
