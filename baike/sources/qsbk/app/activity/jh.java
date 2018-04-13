package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;

class jh implements OnClickListener {
    final /* synthetic */ CommonSettingActivity a;

    jh(CommonSettingActivity commonSettingActivity) {
        this.a = commonSettingActivity;
    }

    public void onClick(View view) {
        this.a.k().show();
    }
}
