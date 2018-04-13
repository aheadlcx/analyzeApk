package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;

class ap implements OnClickListener {
    final /* synthetic */ ActionBarUserSettingNavi a;

    ap(ActionBarUserSettingNavi actionBarUserSettingNavi) {
        this.a = actionBarUserSettingNavi;
    }

    public void onClick(View view) {
        this.a.gotoSmallPaperActivity();
    }
}
