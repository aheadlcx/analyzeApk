package qsbk.app.activity.publish;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import qsbk.app.video.VideoPickerActivity;

class q implements OnClickListener {
    final /* synthetic */ CirclePublishActivity a;

    q(CirclePublishActivity circlePublishActivity) {
        this.a = circlePublishActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.a.y.clear();
        this.a.x();
        VideoPickerActivity.launchForResult(this.a);
    }
}
