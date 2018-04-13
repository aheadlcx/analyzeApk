package qsbk.app.adapter;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class be implements OnClickListener {
    final /* synthetic */ ContactQiuYouAdapter a;

    be(ContactQiuYouAdapter contactQiuYouAdapter) {
        this.a = contactQiuYouAdapter;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.cancel();
    }
}
