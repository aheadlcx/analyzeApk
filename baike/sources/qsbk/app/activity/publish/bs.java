package qsbk.app.activity.publish;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class bs implements OnClickListener {
    final /* synthetic */ int a;
    final /* synthetic */ PublishActivity b;

    bs(PublishActivity publishActivity, int i) {
        this.b = publishActivity;
        this.a = i;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        if (this.a == 0) {
            this.b.startAlbum();
        } else if (this.a == 2) {
            this.b.startVideo();
        }
    }
}
