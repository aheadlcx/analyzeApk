package qsbk.app.activity;

import android.app.AlertDialog.Builder;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.QsbkApp;
import qsbk.app.utils.ToastAndDialog;

class aeg implements OnClickListener {
    final /* synthetic */ UserRevokeActivity a;

    aeg(UserRevokeActivity userRevokeActivity) {
        this.a = userRevokeActivity;
    }

    public void onClick(View view) {
        String trim = this.a.c.getText().toString().trim();
        if (trim.length() < 15) {
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "至少输入15个字!", Integer.valueOf(0)).show();
        } else if (QsbkApp.currentUser != null) {
            new Builder(this.a).setItems(new String[]{"确认提交申诉?", "取消"}, new aeh(this, trim)).show();
        }
    }
}
