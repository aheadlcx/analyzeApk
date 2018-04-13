package qsbk.app.fragments;

import android.support.v4.app.NotificationCompat;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.model.QiushiTopic;
import qsbk.app.utils.QiushiTopicBus;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.widget.QiushiTopicCell;

class jc implements SimpleCallBack {
    final /* synthetic */ QiushiTopic a;
    final /* synthetic */ QiushiTopicCell b;
    final /* synthetic */ QiushiTopicListFragment c;

    jc(QiushiTopicListFragment qiushiTopicListFragment, QiushiTopic qiushiTopic, QiushiTopicCell qiushiTopicCell) {
        this.c = qiushiTopicListFragment;
        this.a = qiushiTopic;
        this.b = qiushiTopicCell;
    }

    public void onSuccess(JSONObject jSONObject) {
        if (jSONObject.optInt(NotificationCompat.CATEGORY_ERROR, -1) == 0) {
            this.a.isSubscribed = true;
            QiushiTopic qiushiTopic = this.a;
            qiushiTopic.subcribeCount++;
            this.c.b.notifyDataSetChanged();
            ToastAndDialog.makePositiveToast(QsbkApp.mContext, "订阅成功", Integer.valueOf(0)).show();
            QiushiTopicBus.updateQiushiTopic(this.a, QiushiTopicListFragment.TAG);
        }
    }

    public void onFailure(int i, String str) {
        ToastAndDialog.makeNegativeToast(QsbkApp.getInstance(), str);
        this.b.onUpdate();
    }
}
