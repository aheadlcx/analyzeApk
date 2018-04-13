package qsbk.app.fragments;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class i implements OnClickListener {
    final /* synthetic */ BaseNearByUserFragment a;

    i(BaseNearByUserFragment baseNearByUserFragment) {
        this.a = baseNearByUserFragment;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.a.e = null;
        dialogInterface.dismiss();
    }
}
