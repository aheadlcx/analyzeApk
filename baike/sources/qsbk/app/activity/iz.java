package qsbk.app.activity;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.QsbkApp;

class iz implements OnClickListener {
    final /* synthetic */ CircleTopicActivity a;

    iz(CircleTopicActivity circleTopicActivity) {
        this.a = circleTopicActivity;
    }

    public void onClick(View view) {
        if (QsbkApp.currentUser == null) {
            this.a.startActivity(new Intent(this.a, ActionBarLoginActivity.class));
            return;
        }
        this.a.a(this.a.g.intro);
    }
}
