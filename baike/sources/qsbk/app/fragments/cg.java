package qsbk.app.fragments;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class cg implements OnClickListener {
    final /* synthetic */ ContactQiuYouFragment a;

    cg(ContactQiuYouFragment contactQiuYouFragment) {
        this.a = contactQiuYouFragment;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
    }
}
