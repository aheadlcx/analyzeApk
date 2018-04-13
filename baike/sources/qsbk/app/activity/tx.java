package qsbk.app.activity;

import android.content.Context;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.utils.GroupActionUtil.ProgressDialogCallBack;
import qsbk.app.utils.ToastAndDialog;

class tx extends ProgressDialogCallBack {
    final /* synthetic */ MemberTitleActivity a;

    tx(MemberTitleActivity memberTitleActivity, Context context, String str) {
        this.a = memberTitleActivity;
        super(context, str);
    }

    public void onSuccess(JSONObject jSONObject) {
        super.onSuccess(jSONObject);
        this.a.b.titleEnable = this.a.c.isChecked();
        if (this.a.b.titleEnable) {
            for (int i = 0; i < 6; i++) {
                this.a.b.titles[i] = this.a.d[i].getText().toString();
            }
        }
        this.a.finish();
    }

    public void onFailure(int i, String str) {
        super.onFailure(i, str);
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str, Integer.valueOf(0)).show();
    }
}
