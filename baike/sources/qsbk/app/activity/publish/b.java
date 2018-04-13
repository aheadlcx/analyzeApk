package qsbk.app.activity.publish;

import android.view.View;
import android.view.View.OnClickListener;

class b implements OnClickListener {
    final /* synthetic */ int a;
    final /* synthetic */ CirclePublishActivity b;

    b(CirclePublishActivity circlePublishActivity, int i) {
        this.b = circlePublishActivity;
        this.a = i;
    }

    public void onClick(View view) {
        int size = this.b.y.size();
        if (this.a == 0 && this.b.z != null) {
            this.b.z = null;
        }
        if (this.a < size) {
            this.b.b(this.a);
        }
    }
}
