package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;

class hl implements OnClickListener {
    final /* synthetic */ CircleTopicActivity a;

    hl(CircleTopicActivity circleTopicActivity) {
        this.a = circleTopicActivity;
    }

    public void onClick(View view) {
        this.a.a(view);
    }
}
