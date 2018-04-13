package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;

class ag implements OnClickListener {
    final /* synthetic */ ActionBarUserSettingNavi a;

    ag(ActionBarUserSettingNavi actionBarUserSettingNavi) {
        this.a = actionBarUserSettingNavi;
    }

    public void onClick(View view) {
        this.a.gotoFeedbackActivity();
    }
}
