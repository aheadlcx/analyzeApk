package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class uh implements OnClickListener {
    final /* synthetic */ ModifyPwdActivity a;

    uh(ModifyPwdActivity modifyPwdActivity) {
        this.a = modifyPwdActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
    }
}
