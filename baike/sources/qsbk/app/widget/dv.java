package qsbk.app.widget;

import android.support.v4.app.NotificationCompat;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.model.QiushiTopic;
import qsbk.app.utils.QiushiTopicBus;
import qsbk.app.utils.ToastAndDialog;

class dv implements SimpleCallBack {
    final /* synthetic */ QiushiTopic a;
    final /* synthetic */ QiushiTopicCell b;
    final /* synthetic */ QiushiTopicRecommendCell c;

    dv(QiushiTopicRecommendCell qiushiTopicRecommendCell, QiushiTopic qiushiTopic, QiushiTopicCell qiushiTopicCell) {
        this.c = qiushiTopicRecommendCell;
        this.a = qiushiTopic;
        this.b = qiushiTopicCell;
    }

    public void onSuccess(JSONObject jSONObject) {
        if (jSONObject.optInt(NotificationCompat.CATEGORY_ERROR, -1) == 0) {
            this.a.isSubscribed = true;
            QiushiTopic qiushiTopic = this.a;
            qiushiTopic.subcribeCount++;
            for (int i = 0; i < this.c.d.size(); i++) {
                QiushiTopicCell qiushiTopicCell = (QiushiTopicCell) this.c.d.get(i);
                if (this.a.equals(qiushiTopicCell.getItem())) {
                    qiushiTopicCell.onUpdate();
                    break;
                }
            }
            ToastAndDialog.makePositiveToast(QsbkApp.mContext, "订阅成功", Integer.valueOf(0)).show();
            QiushiTopicBus.updateQiushiTopic(this.a, QiushiTopicRecommendCell.TAG);
        }
    }

    public void onFailure(int i, String str) {
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str, Integer.valueOf(0)).show();
        this.b.onUpdate();
    }
}
