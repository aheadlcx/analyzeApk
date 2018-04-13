package qsbk.app.activity;

import android.content.Context;
import java.util.ArrayList;
import java.util.Collection;
import org.json.JSONObject;
import qsbk.app.utils.CircleTopicManager;
import qsbk.app.utils.GroupActionUtil.ProgressDialogCallBack;
import qsbk.app.utils.ToastAndDialog;

class ij extends ProgressDialogCallBack {
    final /* synthetic */ CircleTopicActivity a;

    ij(CircleTopicActivity circleTopicActivity, Context context, String str) {
        this.a = circleTopicActivity;
        super(context, str);
    }

    public void onSuccess(JSONObject jSONObject) {
        super.onSuccess(jSONObject);
        this.a.g.user = null;
        this.a.g.master_id = -1;
        this.a.f();
        Collection arrayList = new ArrayList(1);
        arrayList.add(this.a.g);
        CircleTopicManager.updateTopic(arrayList);
        ToastAndDialog.makePositiveToast(this.a, "成功辞职题主").show();
    }

    public void onFailure(int i, String str) {
        super.onFailure(i, str);
        ToastAndDialog.makeNegativeToast(this.a, str).show();
    }
}
