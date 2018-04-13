package qsbk.app.activity;

import android.support.v4.app.NotificationCompat;
import org.json.JSONObject;
import qsbk.app.http.HttpCallBack;
import qsbk.app.utils.ToastAndDialog;

class abs implements HttpCallBack {
    final /* synthetic */ SearchQiuYouActivity a;

    abs(SearchQiuYouActivity searchQiuYouActivity) {
        this.a = searchQiuYouActivity;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
        if (this.a.e.getText().toString().equalsIgnoreCase(str)) {
            if (jSONObject.optInt(NotificationCompat.CATEGORY_ERROR) == 0) {
                this.a.a(jSONObject);
                this.a.m = this.a.m + 1;
            } else {
                ToastAndDialog.makeNegativeToast(this.a, "搜索不到糗友：" + this.a.e.getText(), Integer.valueOf(0)).show();
            }
        }
        this.a.c.setVisibility(8);
    }

    public void onFailure(String str, String str2) {
        ToastAndDialog.makeNegativeToast(this.a, str2, Integer.valueOf(0)).show();
        this.a.c.setVisibility(8);
    }
}
