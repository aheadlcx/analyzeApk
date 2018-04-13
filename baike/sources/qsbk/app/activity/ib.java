package qsbk.app.activity;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.QsbkApp;
import qsbk.app.activity.publish.CirclePublishActivity;

class ib implements OnClickListener {
    final /* synthetic */ CircleTopicActivity a;

    ib(CircleTopicActivity circleTopicActivity) {
        this.a = circleTopicActivity;
    }

    public void onClick(View view) {
        if (QsbkApp.currentUser == null) {
            this.a.startActivity(new Intent(this.a, ActionBarLoginActivity.class));
            return;
        }
        CirclePublishActivity.launch(this.a, this.a.g);
    }
}
