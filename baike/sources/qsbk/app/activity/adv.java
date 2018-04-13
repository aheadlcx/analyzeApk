package qsbk.app.activity;

import android.content.Context;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.model.BaseUserInfo;
import qsbk.app.utils.GroupActionUtil.ProgressDialogCallBack;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.UIHelper;

class adv extends ProgressDialogCallBack {
    final /* synthetic */ BaseUserInfo a;
    final /* synthetic */ TopicBlackListActivity b;

    adv(TopicBlackListActivity topicBlackListActivity, Context context, String str, BaseUserInfo baseUserInfo) {
        this.b = topicBlackListActivity;
        this.a = baseUserInfo;
        super(context, str);
    }

    public void onSuccess(JSONObject jSONObject) {
        super.onSuccess(jSONObject);
        this.b.f.remove(this.a);
        if (this.b.f.size() == 0) {
            this.b.g.set(UIHelper.getEmptyImg(), "没有屏蔽用户哦");
            this.b.g.show();
        } else {
            this.b.g.hide();
        }
        this.b.d.notifyDataSetChanged();
        this.b.supportInvalidateOptionsMenu();
        ToastAndDialog.makePositiveToast(this.b, "解除屏蔽成功").show();
    }

    public void onFailure(int i, String str) {
        super.onFailure(i, str);
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str, Integer.valueOf(0)).show();
    }
}
