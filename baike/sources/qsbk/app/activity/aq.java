package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;

class aq implements OnClickListener {
    final /* synthetic */ ActionBarUserSettingNavi a;

    aq(ActionBarUserSettingNavi actionBarUserSettingNavi) {
        this.a = actionBarUserSettingNavi;
    }

    public void onClick(View view) {
        this.a.gotoPersonalPrivacyActivity();
    }
}
