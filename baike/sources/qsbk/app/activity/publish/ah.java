package qsbk.app.activity.publish;

import android.view.View;
import android.view.View.OnClickListener;

class ah implements OnClickListener {
    final /* synthetic */ CirclePublishActivity a;

    ah(CirclePublishActivity circlePublishActivity) {
        this.a = circlePublishActivity;
    }

    public void onClick(View view) {
        this.a.onBackPressed();
    }
}
