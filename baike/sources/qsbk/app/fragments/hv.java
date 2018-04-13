package qsbk.app.fragments;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class hv implements OnClickListener {
    final /* synthetic */ QiuYouFragment a;

    hv(QiuYouFragment qiuYouFragment) {
        this.a = qiuYouFragment;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
    }
}
