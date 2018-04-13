package qsbk.app.fragments;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class fl implements OnClickListener {
    final /* synthetic */ ManangeMyContentsFragment a;

    fl(ManangeMyContentsFragment manangeMyContentsFragment) {
        this.a = manangeMyContentsFragment;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.a.F();
        dialogInterface.dismiss();
    }
}
