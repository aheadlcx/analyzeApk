package qsbk.app.activity.publish;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class bi implements OnClickListener {
    final /* synthetic */ PublishActivity a;

    bi(PublishActivity publishActivity) {
        this.a = publishActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.a.submitContent();
    }
}
