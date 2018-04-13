package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class acq implements OnClickListener {
    final /* synthetic */ SettingPersonalBigCoverBaseActivity a;

    acq(SettingPersonalBigCoverBaseActivity settingPersonalBigCoverBaseActivity) {
        this.a = settingPersonalBigCoverBaseActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
    }
}
