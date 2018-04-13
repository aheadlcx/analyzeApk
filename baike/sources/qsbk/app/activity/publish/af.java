package qsbk.app.activity.publish;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class af implements OnClickListener {
    final /* synthetic */ CirclePublishActivity a;

    af(CirclePublishActivity circlePublishActivity) {
        this.a = circlePublishActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.a.A = i;
        this.a.circlePermisson.setText(CirclePublishActivity.ACCESS_PERMISSION[i]);
    }
}
