package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class afy implements OnClickListener {
    final /* synthetic */ afx a;

    afy(afx afx) {
        this.a = afx;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        BindPhoneActivity.launchForResult(this.a.a, 0);
    }
}
