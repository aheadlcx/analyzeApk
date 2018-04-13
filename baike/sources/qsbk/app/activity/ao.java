package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;

class ao implements OnClickListener {
    final /* synthetic */ ActionBarUserSettingNavi a;

    ao(ActionBarUserSettingNavi actionBarUserSettingNavi) {
        this.a = actionBarUserSettingNavi;
    }

    public void onClick(View view) {
        this.a.gotoMessageInfoRemindActivity();
    }
}
