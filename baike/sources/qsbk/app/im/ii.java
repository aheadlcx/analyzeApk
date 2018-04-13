package qsbk.app.im;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class ii implements OnClickListener {
    final /* synthetic */ ih a;

    ii(ih ihVar) {
        this.a = ihVar;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.cancel();
    }
}
