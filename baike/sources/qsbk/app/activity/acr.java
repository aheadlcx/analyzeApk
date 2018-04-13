package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class acr implements OnClickListener {
    final /* synthetic */ SettingPersonalBigCoverBaseActivity a;

    acr(SettingPersonalBigCoverBaseActivity settingPersonalBigCoverBaseActivity) {
        this.a = settingPersonalBigCoverBaseActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.a.e();
        this.a.f();
    }
}
