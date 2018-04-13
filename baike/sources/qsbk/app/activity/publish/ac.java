package qsbk.app.activity.publish;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;

class ac implements OnCancelListener {
    final /* synthetic */ CirclePublishActivity a;

    ac(CirclePublishActivity circlePublishActivity) {
        this.a = circlePublishActivity;
    }

    public void onCancel(DialogInterface dialogInterface) {
        this.a.send.setEnabled(true);
        if (this.a.G != null) {
            this.a.G.cancel(true);
        }
    }
}
