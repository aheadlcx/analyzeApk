package qsbk.app.widget;

import android.content.Context;
import android.content.ContextWrapper;
import org.json.JSONObject;
import qsbk.app.activity.CircleTopicActivity;
import qsbk.app.utils.GroupActionUtil.ProgressDialogCallBack;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.ToastUtil;

final class e extends ProgressDialogCallBack {
    final /* synthetic */ Context a;

    e(Context context, String str, Context context2) {
        this.a = context2;
        super(context, str);
    }

    public void onSuccess(JSONObject jSONObject) {
        super.onSuccess(jSONObject);
        Context context = this.a;
        if (context instanceof CircleTopicActivity) {
            ((CircleTopicActivity) context).refresh();
        } else if (this.a instanceof ContextWrapper) {
            context = ((ContextWrapper) this.a).getBaseContext();
            if (context instanceof CircleTopicActivity) {
                ((CircleTopicActivity) context).refresh();
            }
        }
        ToastAndDialog.makePositiveToast(this.a, "取消置顶动态成功").show();
    }

    public void onFailure(int i, String str) {
        super.onFailure(i, str);
        ToastUtil.Long(str);
    }
}
