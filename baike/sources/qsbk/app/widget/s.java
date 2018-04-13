package qsbk.app.widget;

import android.app.Activity;
import android.content.Context;
import org.json.JSONObject;
import qsbk.app.model.CircleArticle;
import qsbk.app.utils.CircleArticleBus;
import qsbk.app.utils.GroupActionUtil.ProgressDialogCallBack;
import qsbk.app.utils.ToastAndDialog;

final class s extends ProgressDialogCallBack {
    final /* synthetic */ CircleArticle a;
    final /* synthetic */ Activity b;

    s(Context context, String str, CircleArticle circleArticle, Activity activity) {
        this.a = circleArticle;
        this.b = activity;
        super(context, str);
    }

    public void onSuccess(JSONObject jSONObject) {
        super.onSuccess(jSONObject);
        CircleArticleBus.deleteArticle(this.a, null);
        ToastAndDialog.makePositiveToast(this.b, "举报成功，感谢您的反馈").show();
    }

    public void onFailure(int i, String str) {
        super.onFailure(i, str);
        ToastAndDialog.makeNegativeToast(this.b, str).show();
    }
}
