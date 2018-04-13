package qsbk.app.fragments;

import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.model.QiushiTopic;
import qsbk.app.utils.QiushiTopicBus;
import qsbk.app.utils.ToastAndDialog;

class jf implements SimpleCallBack {
    final /* synthetic */ je a;

    jf(je jeVar) {
        this.a = jeVar;
    }

    public void onSuccess(JSONObject jSONObject) {
        this.a.a.isSubscribed = false;
        QiushiTopic qiushiTopic = this.a.a;
        qiushiTopic.subcribeCount--;
        if (this.a.a.subcribeCount < 0) {
            this.a.a.subcribeCount = 0;
        }
        this.a.b.b.notifyDataSetChanged();
        QiushiTopicBus.updateQiushiTopic(this.a.a, QiushiTopicListFragment.TAG);
        ToastAndDialog.makePositiveToast(QsbkApp.mContext, "取消成功", Integer.valueOf(0)).show();
    }

    public void onFailure(int i, String str) {
        ToastAndDialog.makeNegativeToast(QsbkApp.getInstance(), str);
    }
}
