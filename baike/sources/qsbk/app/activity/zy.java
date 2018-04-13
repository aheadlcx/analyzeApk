package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;

class zy implements OnClickListener {
    final /* synthetic */ PrivacyActivity a;

    zy(PrivacyActivity privacyActivity) {
        this.a = privacyActivity;
    }

    public void onClick(View view) {
        this.a.gotoPersonalBlackListActivity();
    }
}
