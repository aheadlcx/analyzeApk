package qsbk.app.fragments;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class kw implements OnClickListener {
    final /* synthetic */ RandomDoorUsersFragment a;

    kw(RandomDoorUsersFragment randomDoorUsersFragment) {
        this.a = randomDoorUsersFragment;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
    }
}
