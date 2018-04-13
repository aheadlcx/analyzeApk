package qsbk.app.activity;

import android.content.Context;
import java.util.ArrayList;
import java.util.Collection;
import org.json.JSONObject;
import qsbk.app.utils.CircleTopicManager;
import qsbk.app.utils.GroupActionUtil.ProgressDialogCallBack;
import qsbk.app.utils.ToastAndDialog;

class ia extends ProgressDialogCallBack {
    final /* synthetic */ String a;
    final /* synthetic */ CircleTopicActivity b;

    ia(CircleTopicActivity circleTopicActivity, Context context, String str, String str2) {
        this.b = circleTopicActivity;
        this.a = str2;
        super(context, str);
    }

    public void onSuccess(JSONObject jSONObject) {
        super.onSuccess(jSONObject);
        this.b.g.intro = this.a;
        Collection arrayList = new ArrayList(1);
        arrayList.add(this.b.g);
        CircleTopicManager.updateTopic(arrayList);
        this.b.f();
        ToastAndDialog.makePositiveToast(this.b, "修改简介成功").show();
    }

    public void onFailure(int i, String str) {
        super.onFailure(i, str);
        ToastAndDialog.makeNegativeToast(this.b, str).show();
    }
}
