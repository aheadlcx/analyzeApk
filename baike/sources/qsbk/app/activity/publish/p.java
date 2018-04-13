package qsbk.app.activity.publish;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import qsbk.app.utils.ToastAndDialog;

class p implements OnClickListener {
    final /* synthetic */ CirclePublishActivity a;

    p(CirclePublishActivity circlePublishActivity) {
        this.a = circlePublishActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        switch (i) {
            case 0:
                this.a.startImagePicker();
                return;
            case 1:
                if (this.a.H >= 6) {
                    this.a.A();
                    return;
                } else {
                    ToastAndDialog.makeText(this.a, "糗友圈等级大于或等于L6级才能发视频").show();
                    return;
                }
            default:
                return;
        }
    }
}
