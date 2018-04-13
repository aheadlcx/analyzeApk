package qsbk.app.activity;

import android.content.Context;
import org.json.JSONObject;
import qsbk.app.utils.GroupActionUtil.ProgressDialogCallBack;
import qsbk.app.utils.ToastAndDialog;

class if extends ProgressDialogCallBack {
    final /* synthetic */ CircleTopicActivity a;

    if(CircleTopicActivity circleTopicActivity, Context context, String str) {
        this.a = circleTopicActivity;
        super(context, str);
    }

    public void onSuccess(JSONObject jSONObject) {
        super.onSuccess(jSONObject);
        ToastAndDialog.makePositiveToast(this.a, "举报成功，感谢您的反馈").show();
    }

    public void onFailure(int i, String str) {
        super.onFailure(i, str);
        ToastAndDialog.makeNegativeToast(this.a, str).show();
    }
}
