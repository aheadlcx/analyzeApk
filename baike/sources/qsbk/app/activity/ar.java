package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;

class ar implements OnClickListener {
    final /* synthetic */ ActionBarUserSettingNavi a;

    ar(ActionBarUserSettingNavi actionBarUserSettingNavi) {
        this.a = actionBarUserSettingNavi;
    }

    public void onClick(View view) {
        this.a.gotoCommonActivity();
    }
}
