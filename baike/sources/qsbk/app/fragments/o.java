package qsbk.app.fragments;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class o implements OnClickListener {
    final /* synthetic */ BaseNearbyFragment a;

    o(BaseNearbyFragment baseNearbyFragment) {
        this.a = baseNearbyFragment;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.a.a("dialog_deny");
        this.a.c = null;
        dialogInterface.dismiss();
        this.a.show_restart();
    }
}
