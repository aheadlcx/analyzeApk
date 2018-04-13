package qsbk.app.nearby.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class ad implements OnClickListener {
    final /* synthetic */ InfoCompleteActivity a;

    ad(InfoCompleteActivity infoCompleteActivity) {
        this.a = infoCompleteActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        this.a.s.upload(this.a, this.a.Q);
    }
}
