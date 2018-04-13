package qsbk.app.activity;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

class zw implements OnClickListener {
    final /* synthetic */ PersonalPrivacySettingActivity a;

    zw(PersonalPrivacySettingActivity personalPrivacySettingActivity) {
        this.a = personalPrivacySettingActivity;
    }

    public void onClick(View view) {
        this.a.startActivity(new Intent(this.a, BlacklistActivity.class));
    }
}
