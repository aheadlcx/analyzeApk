package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class fh implements OnClickListener {
    final /* synthetic */ fg a;

    fh(fg fgVar) {
        this.a = fgVar;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        BindPhoneActivity.launchForResult(this.a.a, 0);
    }
}
