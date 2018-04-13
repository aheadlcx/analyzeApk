package qsbk.app.activity;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

class am implements OnClickListener {
    final /* synthetic */ ActionBarUserSettingNavi a;

    am(ActionBarUserSettingNavi actionBarUserSettingNavi) {
        this.a = actionBarUserSettingNavi;
    }

    public void onClick(View view) {
        if (this.a.p > 0) {
            this.a.startActivity(new Intent(this.a, SwitchTestDomainActivity.class));
            this.a.finish();
        }
    }
}
