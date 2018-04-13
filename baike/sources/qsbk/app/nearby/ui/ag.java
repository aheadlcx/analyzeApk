package qsbk.app.nearby.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class ag implements OnClickListener {
    final /* synthetic */ InfoCompleteActivity a;

    ag(InfoCompleteActivity infoCompleteActivity) {
        this.a = infoCompleteActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.a.E = InfoCompleteActivity.c[i];
        this.a.k(this.a.E);
        this.a.z();
        dialogInterface.dismiss();
    }
}
