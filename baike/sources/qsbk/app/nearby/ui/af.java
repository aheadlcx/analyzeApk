package qsbk.app.nearby.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class af implements OnClickListener {
    final /* synthetic */ InfoCompleteActivity a;

    af(InfoCompleteActivity infoCompleteActivity) {
        this.a = infoCompleteActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.a.D = InfoCompleteActivity.b[i];
        this.a.j(this.a.D);
        this.a.z();
        dialogInterface.dismiss();
    }
}
