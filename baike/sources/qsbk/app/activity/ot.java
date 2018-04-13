package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class ot implements OnClickListener {
    final /* synthetic */ int a;
    final /* synthetic */ ImageViewer b;

    ot(ImageViewer imageViewer, int i) {
        this.b = imageViewer;
        this.a = i;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        GroupInfoActivity.sActionResult = this.a | 512;
        this.b.finish();
    }
}
