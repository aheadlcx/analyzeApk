package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class sz implements OnClickListener {
    final /* synthetic */ MainActivity$c a;

    sz(MainActivity$c mainActivity$c) {
        this.a = mainActivity$c;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
    }
}
