package qsbk.app.activity;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.QsbkApp;
import qsbk.app.im.OfficialSubscribeListActivity;

class yi implements OnClickListener {
    final /* synthetic */ NotificationSettingActivity a;

    yi(NotificationSettingActivity notificationSettingActivity) {
        this.a = notificationSettingActivity;
    }

    public void onClick(View view) {
        if (QsbkApp.currentUser != null) {
            this.a.startActivity(new Intent(this.a, OfficialSubscribeListActivity.class));
        } else {
            this.a.startActivity(new Intent(this.a, ActionBarLoginActivity.class));
        }
    }
}
