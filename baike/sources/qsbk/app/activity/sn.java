package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import qsbk.app.service.VersionCheckService;
import qsbk.app.utils.SharePreferenceUtils;

class sn implements OnClickListener {
    final /* synthetic */ MainActivity a;

    sn(MainActivity mainActivity) {
        this.a = mainActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        SharePreferenceUtils.setSharePreferencesValue(VersionCheckService.CANCLE_UDATE_TIME, String.valueOf(System.currentTimeMillis()));
        dialogInterface.dismiss();
    }
}
