package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class ic implements OnClickListener {
    final /* synthetic */ CircleTopicActivity a;

    ic(CircleTopicActivity circleTopicActivity) {
        this.a = circleTopicActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
    }
}
