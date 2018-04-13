package qsbk.app.activity.publish;

import android.view.View;
import android.view.View.OnFocusChangeListener;

class f implements OnFocusChangeListener {
    final /* synthetic */ CirclePublishActivity a;

    f(CirclePublishActivity circlePublishActivity) {
        this.a = circlePublishActivity;
    }

    public void onFocusChange(View view, boolean z) {
        if (z) {
            this.a.l();
        }
    }
}
