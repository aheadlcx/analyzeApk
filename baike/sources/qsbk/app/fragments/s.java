package qsbk.app.fragments;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class s implements OnClickListener {
    final /* synthetic */ BlacklistFragment a;

    s(BlacklistFragment blacklistFragment) {
        this.a = blacklistFragment;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
    }
}
