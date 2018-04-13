package qsbk.app.activity;

import android.content.Context;
import java.util.ArrayList;
import java.util.Collection;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.utils.CircleTopicManager;
import qsbk.app.utils.GroupActionUtil.ProgressDialogCallBack;
import qsbk.app.utils.ToastAndDialog;

class hv extends ProgressDialogCallBack {
    final /* synthetic */ CircleTopicActivity a;

    hv(CircleTopicActivity circleTopicActivity, Context context, String str) {
        this.a = circleTopicActivity;
        super(context, str);
    }

    public void onSuccess(JSONObject jSONObject) {
        super.onSuccess(jSONObject);
        this.a.g.user = QsbkApp.currentUser;
        this.a.f();
        Collection arrayList = new ArrayList(1);
        arrayList.add(this.a.g);
        CircleTopicManager.updateTopic(arrayList);
        ToastAndDialog.makePositiveToast(this.a, "申请题主成功").show();
    }

    public void onFailure(int i, String str) {
        super.onFailure(i, str);
        ToastAndDialog.makeNegativeToast(this.a, str).show();
    }
}
