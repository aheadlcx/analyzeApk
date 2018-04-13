package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class qj implements OnClickListener {
    final /* synthetic */ qi a;

    qj(qi qiVar) {
        this.a = qiVar;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        BindPhoneActivity.launchForResult(this.a.a, 11);
    }
}
