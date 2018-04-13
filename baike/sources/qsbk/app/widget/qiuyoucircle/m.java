package qsbk.app.widget.qiuyoucircle;

import android.content.Context;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.activity.CircleTopicActivity;
import qsbk.app.utils.GroupActionUtil.ProgressDialogCallBack;
import qsbk.app.utils.ToastAndDialog;

class m extends ProgressDialogCallBack {
    final /* synthetic */ Context a;
    final /* synthetic */ BaseUserCell b;

    m(BaseUserCell baseUserCell, Context context, String str, Context context2) {
        this.b = baseUserCell;
        this.a = context2;
        super(context, str);
    }

    public void onSuccess(JSONObject jSONObject) {
        super.onSuccess(jSONObject);
        ToastAndDialog.makePositiveToast(QsbkApp.mContext, "取消置顶成功", Integer.valueOf(0)).show();
        if (this.a instanceof CircleTopicActivity) {
            ((CircleTopicActivity) this.a).refresh();
        }
    }

    public void onFailure(int i, String str) {
        super.onFailure(i, str);
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str, Integer.valueOf(0)).show();
    }
}
