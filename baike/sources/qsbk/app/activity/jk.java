package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;

class jk implements OnClickListener {
    final /* synthetic */ CommonSettingActivity a;

    jk(CommonSettingActivity commonSettingActivity) {
        this.a = commonSettingActivity;
    }

    public void onClick(View view) {
        this.a.l().show();
    }
}
