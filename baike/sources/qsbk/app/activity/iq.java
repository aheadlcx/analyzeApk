package qsbk.app.activity;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.QsbkApp;
import qsbk.app.utils.LoginPermissionClickDelegate;

class iq implements OnClickListener {
    final /* synthetic */ CircleTopicActivity a;

    iq(CircleTopicActivity circleTopicActivity) {
        this.a = circleTopicActivity;
    }

    public void onClick(View view) {
        if (QsbkApp.currentUser == null) {
            LoginPermissionClickDelegate.startLoginActivity(view.getContext());
            return;
        }
        Intent intent = new Intent(this.a, ClockedRankingActivity.class);
        intent.putExtra("topicId", Integer.parseInt(this.a.g.id));
        this.a.startActivity(intent);
    }
}
