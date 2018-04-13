package qsbk.app.fragments;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class fk implements OnClickListener {
    final /* synthetic */ ManangeMyContentsFragment a;

    fk(ManangeMyContentsFragment manangeMyContentsFragment) {
        this.a = manangeMyContentsFragment;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
    }
}
