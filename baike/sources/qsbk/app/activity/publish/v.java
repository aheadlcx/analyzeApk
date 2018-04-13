package qsbk.app.activity.publish;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class v implements OnClickListener {
    final /* synthetic */ int a;
    final /* synthetic */ CirclePublishActivity b;

    v(CirclePublishActivity circlePublishActivity, int i) {
        this.b = circlePublishActivity;
        this.a = i;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.b.s();
        this.b.uploadImage(this.a);
    }
}
