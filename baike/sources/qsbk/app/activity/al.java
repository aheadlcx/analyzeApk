package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;

class al implements OnClickListener {
    final /* synthetic */ ActionBarUserSettingNavi a;

    al(ActionBarUserSettingNavi actionBarUserSettingNavi) {
        this.a = actionBarUserSettingNavi;
    }

    public void onClick(View view) {
        this.a.p = this.a.p + 1;
    }
}
