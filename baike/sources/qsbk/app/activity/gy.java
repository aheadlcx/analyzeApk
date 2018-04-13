package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;

class gy implements OnClickListener {
    final /* synthetic */ CircleTopicActivity a;

    gy(CircleTopicActivity circleTopicActivity) {
        this.a = circleTopicActivity;
    }

    public void onClick(View view) {
        this.a.onBackPressed();
    }
}
