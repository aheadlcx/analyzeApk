package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;

class an implements OnClickListener {
    final /* synthetic */ ActionBarUserSettingNavi a;

    an(ActionBarUserSettingNavi actionBarUserSettingNavi) {
        this.a = actionBarUserSettingNavi;
    }

    public void onClick(View view) {
        this.a.gotoSecuritySafeActivity();
    }
}
