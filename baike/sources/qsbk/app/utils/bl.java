package qsbk.app.utils;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class bl implements OnClickListener {
    final /* synthetic */ UserLogoutHelper a;

    bl(UserLogoutHelper userLogoutHelper) {
        this.a = userLogoutHelper;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
    }
}
