package qsbk.app.adapter;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.text.TextUtils;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.utils.ToastAndDialog;

class cp implements SimpleCallBack {
    final /* synthetic */ String a;
    final /* synthetic */ QiuYouAdapter b;

    cp(QiuYouAdapter qiuYouAdapter, String str) {
        this.b = qiuYouAdapter;
        this.a = str;
    }

    public void onSuccess(JSONObject jSONObject) {
        this.b.i.dismiss();
        if (this.b.e == 1) {
            this.b.a(jSONObject, this.a);
            ToastAndDialog.makePositiveToast(QsbkApp.mContext, "已取消粉...", Integer.valueOf(0)).show();
        } else if (this.b.e == 2) {
            ToastAndDialog.makePositiveToast(QsbkApp.mContext, "已取消粉...", Integer.valueOf(0)).show();
            this.b.a(this.a);
            this.b.a(jSONObject, this.a);
        } else if (this.b.e == 3) {
            ToastAndDialog.makePositiveToast(QsbkApp.mContext, "你们已经是好友了！", Integer.valueOf(0)).show();
            this.b.a(jSONObject, this.a);
        }
    }

    public void onFailure(int i, String str) {
        this.b.i.dismiss();
        if (i != -110) {
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str, Integer.valueOf(0)).show();
        } else if (i != -110 || this.b.e != 3) {
        } else {
            if (!(this.b.b instanceof Activity) || !((Activity) this.b.b).isFinishing()) {
                Builder title = new Builder(this.b.b).setTitle(R.string.nearby_pop_title);
                if (TextUtils.isEmpty(str)) {
                    str = "请先完善个人资料!";
                }
                title.setMessage(str).setPositiveButton("完善资料", new cr(this)).setNegativeButton("再逛逛", new cq(this)).show();
            }
        }
    }
}
