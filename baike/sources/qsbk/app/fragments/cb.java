package qsbk.app.fragments;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class cb implements OnClickListener {
    final /* synthetic */ ContactMyGroupFragment a;

    cb(ContactMyGroupFragment contactMyGroupFragment) {
        this.a = contactMyGroupFragment;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
    }
}
