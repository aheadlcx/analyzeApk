package qsbk.app.activity;

import android.content.Context;
import org.json.JSONObject;
import qsbk.app.utils.GroupActionUtil.ProgressDialogCallBack;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.ToastUtil;
import qsbk.app.utils.UIHelper;

class adr extends ProgressDialogCallBack {
    final /* synthetic */ TopicBlackListActivity a;

    adr(TopicBlackListActivity topicBlackListActivity, Context context, String str) {
        this.a = topicBlackListActivity;
        super(context, str);
    }

    public void onSuccess(JSONObject jSONObject) {
        super.onSuccess(jSONObject);
        this.a.f.clear();
        this.a.g.set(UIHelper.getEmptyImg(), "没有屏蔽用户哦");
        this.a.g.show();
        this.a.d.notifyDataSetChanged();
        this.a.supportInvalidateOptionsMenu();
        ToastAndDialog.makePositiveToast(this.a, "解除屏蔽成功").show();
    }

    public void onFailure(int i, String str) {
        super.onFailure(i, str);
        ToastUtil.Long(str);
    }
}
