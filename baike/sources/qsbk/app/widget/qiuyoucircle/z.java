package qsbk.app.widget.qiuyoucircle;

import android.support.v4.app.NotificationCompat;
import android.view.View;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.model.CircleArticle;
import qsbk.app.utils.CircleTopicManager;
import qsbk.app.utils.CircleUpgradeDialog;
import qsbk.app.utils.ToastAndDialog;

class z implements SimpleCallBack {
    final /* synthetic */ CircleArticle a;
    final /* synthetic */ View b;
    final /* synthetic */ y c;

    z(y yVar, CircleArticle circleArticle, View view) {
        this.c = yVar;
        this.a = circleArticle;
        this.b = view;
    }

    public void onSuccess(JSONObject jSONObject) {
        int optInt = jSONObject.optInt(NotificationCompat.CATEGORY_ERROR);
        if (jSONObject.has(NotificationCompat.CATEGORY_ERROR) && optInt == 0) {
            CircleTopicManager.getInstance().insertTopicToLRU(this.a.topic);
        }
        optInt = jSONObject.optInt("score");
        if (optInt > 0) {
            ToastAndDialog.makePositiveToast(QsbkApp.mContext, "积分+" + optInt, Integer.valueOf(0)).show();
        }
        optInt = jSONObject.optInt("rank", 0);
        if (optInt > 0) {
            CircleUpgradeDialog.show(this.b.getContext(), optInt);
        }
    }

    public void onFailure(int i, String str) {
    }
}
