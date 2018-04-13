package qsbk.app.activity.publish;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import qsbk.app.activity.ImagesPickerActivity;

class r implements OnClickListener {
    final /* synthetic */ CirclePublishActivity a;

    r(CirclePublishActivity circlePublishActivity) {
        this.a = circlePublishActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.a.z = null;
        this.a.x();
        this.a.a(ImagesPickerActivity.prepareIntent(this.a, 6 - this.a.y.size()), this.a.T);
    }
}
