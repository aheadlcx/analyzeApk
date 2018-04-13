package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import qsbk.app.Constants;
import qsbk.app.service.VersionCheckService;
import qsbk.app.utils.SharePreferenceUtils;

class sp implements OnClickListener {
    final /* synthetic */ MainActivity a;

    sp(MainActivity mainActivity) {
        this.a = mainActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        SharePreferenceUtils.setSharePreferencesValue(VersionCheckService.CANCLE_UDATE_TIME, "-1");
        SharePreferenceUtils.setSharePreferencesValue(VersionCheckService.CANCLE_UDATE_VERSION, Constants.serverVersion);
        dialogInterface.dismiss();
    }
}
