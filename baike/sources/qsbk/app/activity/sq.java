package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import qsbk.app.service.VersionCheckService;
import qsbk.app.utils.SharePreferenceUtils;

class sq implements OnCancelListener {
    final /* synthetic */ MainActivity a;

    sq(MainActivity mainActivity) {
        this.a = mainActivity;
    }

    public void onCancel(DialogInterface dialogInterface) {
        SharePreferenceUtils.setSharePreferencesValue(VersionCheckService.CANCLE_UDATE_TIME, String.valueOf(System.currentTimeMillis()));
    }
}
