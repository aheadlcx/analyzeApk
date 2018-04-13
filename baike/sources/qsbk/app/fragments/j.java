package qsbk.app.fragments;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class j implements OnClickListener {
    final /* synthetic */ BaseNearByUserFragment a;

    j(BaseNearByUserFragment baseNearByUserFragment) {
        this.a = baseNearByUserFragment;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        this.a.a(this.a.e.getGender(), this.a.e.getTimeInMinute());
        this.a.e = null;
    }
}
