package qsbk.app.activity;

import android.support.v4.app.NotificationCompat;
import android.view.View;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.utils.CircleTopicManager;
import qsbk.app.utils.CircleUpgradeDialog;
import qsbk.app.utils.ToastAndDialog;

class gu implements SimpleCallBack {
    final /* synthetic */ View a;
    final /* synthetic */ gt b;

    gu(gt gtVar, View view) {
        this.b = gtVar;
        this.a = view;
    }

    public void onSuccess(JSONObject jSONObject) {
        int optInt = jSONObject.optInt(NotificationCompat.CATEGORY_ERROR);
        if (jSONObject.has(NotificationCompat.CATEGORY_ERROR) && optInt == 0) {
            CircleTopicManager.getInstance().insertTopicToLRU(this.b.a.i.topic);
        }
        optInt = jSONObject.optInt("score");
        if (optInt > 0) {
            ToastAndDialog.makePositiveToast(QsbkApp.mContext, "积分+" + optInt, Integer.valueOf(0)).show();
        }
        optInt = jSONObject.optInt("rank", 0);
        if (optInt > 0) {
            CircleUpgradeDialog.show(this.a.getContext(), optInt);
        }
    }

    public void onFailure(int i, String str) {
    }
}
