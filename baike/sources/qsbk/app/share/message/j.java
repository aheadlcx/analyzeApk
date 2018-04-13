package qsbk.app.share.message;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class j implements OnClickListener {
    final /* synthetic */ RecentContactsFragment a;

    j(RecentContactsFragment recentContactsFragment) {
        this.a = recentContactsFragment;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
    }
}
