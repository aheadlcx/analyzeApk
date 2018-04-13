package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import qsbk.app.utils.TipsManager;

class af implements OnClickListener {
    final /* synthetic */ ActionBarUserSettingNavi a;

    af(ActionBarUserSettingNavi actionBarUserSettingNavi) {
        this.a = actionBarUserSettingNavi;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.a.f();
        this.a.a.showTip(TipsManager.shouldShowSecurityBind(this.a));
    }
}
