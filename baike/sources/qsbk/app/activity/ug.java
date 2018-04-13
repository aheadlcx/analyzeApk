package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import qsbk.app.QsbkApp;
import qsbk.app.activity.security.FindPasswordActivity;
import qsbk.app.utils.ToastAndDialog;

class ug implements OnClickListener {
    final /* synthetic */ ModifyPwdActivity a;

    ug(ModifyPwdActivity modifyPwdActivity) {
        this.a = modifyPwdActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        switch (i) {
            case 0:
                this.a.startActivity(new Intent(this.a, FindPasswordActivity.class));
                this.a.finish();
                return;
            case 1:
                if (QsbkApp.currentUser == null || !QsbkApp.currentUser.hasPhone()) {
                    ToastAndDialog.makeNegativeToast(this.a, "请先绑定手机", Integer.valueOf(0)).show();
                    return;
                }
                ResetPwdActivity.launch(this.a);
                this.a.finish();
                return;
            default:
                return;
        }
    }
}
