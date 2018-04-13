package qsbk.app.activity.publish;

import android.view.View;
import android.view.View.OnClickListener;

class g implements OnClickListener {
    final /* synthetic */ CirclePublishActivity a;

    g(CirclePublishActivity circlePublishActivity) {
        this.a = circlePublishActivity;
    }

    public void onClick(View view) {
        this.a.b.getText().insert(this.a.b.getSelectionEnd(), "@");
    }
}
