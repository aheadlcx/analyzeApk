package qsbk.app.fragments;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class fm implements OnClickListener {
    final /* synthetic */ ManangeMyContentsFragment a;

    fm(ManangeMyContentsFragment manangeMyContentsFragment) {
        this.a = manangeMyContentsFragment;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
    }
}
