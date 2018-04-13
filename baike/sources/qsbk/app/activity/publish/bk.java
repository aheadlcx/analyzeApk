package qsbk.app.activity.publish;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class bk implements OnClickListener {
    final /* synthetic */ PublishActivity a;

    bk(PublishActivity publishActivity) {
        this.a = publishActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
    }
}
