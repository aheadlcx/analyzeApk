package qsbk.app.activity.publish;

import android.view.View;
import android.view.View.OnClickListener;

class i implements OnClickListener {
    final /* synthetic */ CirclePublishActivity a;

    i(CirclePublishActivity circlePublishActivity) {
        this.a = circlePublishActivity;
    }

    public void onClick(View view) {
        if (this.a.G()) {
            this.a.l();
            this.a.O = false;
        } else {
            this.a.k();
            this.a.O = true;
        }
        this.a.U = false;
    }
}
