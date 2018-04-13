package qsbk.app.fragments;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class ga implements OnClickListener {
    final /* synthetic */ MyGroupFragment a;

    ga(MyGroupFragment myGroupFragment) {
        this.a = myGroupFragment;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
    }
}
