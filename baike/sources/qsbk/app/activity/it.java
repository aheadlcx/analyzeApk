package qsbk.app.activity;

import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.utils.ToastAndDialog;

class it implements SimpleCallBack {
    final /* synthetic */ CircleTopicActivity a;

    it(CircleTopicActivity circleTopicActivity) {
        this.a = circleTopicActivity;
    }

    public void onSuccess(JSONObject jSONObject) {
        this.a.r();
        ToastAndDialog.makePositiveToast(QsbkApp.mContext, "图片上传成功", Integer.valueOf(0)).show();
        this.a.refresh();
    }

    public void onFailure(int i, String str) {
        this.a.r();
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str, Integer.valueOf(0)).show();
    }
}
