package qsbk.app.widget;

import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.model.QiushiTopic;
import qsbk.app.utils.QiushiTopicBus;
import qsbk.app.utils.ToastAndDialog;

class dy implements SimpleCallBack {
    final /* synthetic */ dx a;

    dy(dx dxVar) {
        this.a = dxVar;
    }

    public void onSuccess(JSONObject jSONObject) {
        this.a.a.isSubscribed = false;
        QiushiTopic qiushiTopic = this.a.a;
        qiushiTopic.subcribeCount--;
        if (this.a.a.subcribeCount < 0) {
            this.a.a.subcribeCount = 0;
        }
        for (int i = 0; i < this.a.b.d.size(); i++) {
            QiushiTopicCell qiushiTopicCell = (QiushiTopicCell) this.a.b.d.get(i);
            if (this.a.a.equals(qiushiTopicCell.getItem())) {
                qiushiTopicCell.onUpdate();
                break;
            }
        }
        QiushiTopicBus.updateQiushiTopic(this.a.a, QiushiTopicRecommendCell.TAG);
        ToastAndDialog.makePositiveToast(QsbkApp.mContext, "取消成功", Integer.valueOf(0)).show();
    }

    public void onFailure(int i, String str) {
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str, Integer.valueOf(0)).show();
    }
}
