package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;

class ah implements OnClickListener {
    final /* synthetic */ ActionBarUserSettingNavi a;

    ah(ActionBarUserSettingNavi actionBarUserSettingNavi) {
        this.a = actionBarUserSettingNavi;
    }

    public void onClick(View view) {
        this.a.gotoAboutActivity();
    }
}
