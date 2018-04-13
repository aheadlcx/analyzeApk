package qsbk.app.activity.publish;

import android.view.View;
import android.view.View.OnClickListener;

class c implements OnClickListener {
    final /* synthetic */ CirclePublishActivity a;

    c(CirclePublishActivity circlePublishActivity) {
        this.a = circlePublishActivity;
    }

    public void onClick(View view) {
        this.a.player.reset();
        this.a.z = null;
        this.a.x();
        this.a.y();
    }
}
