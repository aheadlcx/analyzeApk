package qsbk.app.share;

import android.text.TextUtils;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.ToastAndDialog;

final class y implements SimpleCallBack {
    final /* synthetic */ boolean a;
    final /* synthetic */ String b;

    y(boolean z, String str) {
        this.a = z;
        this.b = str;
    }

    public void onSuccess(JSONObject jSONObject) {
        ToastAndDialog.makePositiveToast(QsbkApp.mContext, this.a ? "已收藏" : "已取消收藏", Integer.valueOf(0)).show();
        if (this.a) {
            QsbkApp.allCollection.add(this.b);
        } else {
            QsbkApp.allCollection.remove(this.b);
        }
        SharePreferenceUtils.setCollections(QsbkApp.allCollection);
    }

    public void onFailure(int i, String str) {
        String str2 = TextUtils.isEmpty(str) ? this.a ? "收藏失败" : "取消收藏失败" : str;
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str2, Integer.valueOf(0)).show();
    }
}
