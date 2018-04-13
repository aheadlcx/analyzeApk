package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import qsbk.app.QsbkApp;

class jn implements OnClickListener {
    final /* synthetic */ CommonSettingActivity a;

    jn(CommonSettingActivity commonSettingActivity) {
        this.a = commonSettingActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        if (i > CommonSettingActivity.b.length || i < 0) {
            i = 0;
        }
        this.a.c.setSubTitle(CommonSettingActivity.b[i]);
        QsbkApp.getInstance().setContentTextSize(CommonSettingActivity.a[i]);
        dialogInterface.dismiss();
    }
}
