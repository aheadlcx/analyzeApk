package qsbk.app.fragments;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class fj implements OnClickListener {
    final /* synthetic */ ManangeMyContentsFragment a;

    fj(ManangeMyContentsFragment manangeMyContentsFragment) {
        this.a = manangeMyContentsFragment;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.a.G();
        dialogInterface.dismiss();
    }
}
