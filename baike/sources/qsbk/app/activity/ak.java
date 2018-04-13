package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;

class ak implements OnClickListener {
    final /* synthetic */ ActionBarUserSettingNavi a;

    ak(ActionBarUserSettingNavi actionBarUserSettingNavi) {
        this.a = actionBarUserSettingNavi;
    }

    public void onClick(View view) {
        this.a.o.logoutAlert();
    }
}
