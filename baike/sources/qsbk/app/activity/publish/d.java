package qsbk.app.activity.publish;

import android.view.View;
import android.view.View.OnClickListener;

class d implements OnClickListener {
    final /* synthetic */ CirclePublishActivity a;

    d(CirclePublishActivity circlePublishActivity) {
        this.a = circlePublishActivity;
    }

    public void onClick(View view) {
        if (this.a.G()) {
            this.a.l();
        }
        if (this.a.m.getVisibility() == 8 || this.a.m.getVisibility() == 4) {
            this.a.m.setVisibility(0);
            this.a.n.post(new e(this));
        }
    }
}
