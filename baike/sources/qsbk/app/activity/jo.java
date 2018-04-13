package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class jo implements OnClickListener {
    final /* synthetic */ CommonSettingActivity a;

    jo(CommonSettingActivity commonSettingActivity) {
        this.a = commonSettingActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
    }
}
