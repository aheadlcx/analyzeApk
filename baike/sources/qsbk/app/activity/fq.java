package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class fq implements OnClickListener {
    final /* synthetic */ fo a;

    fq(fo foVar) {
        this.a = foVar;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        BindPhoneActivity.launch(this.a.b);
    }
}
