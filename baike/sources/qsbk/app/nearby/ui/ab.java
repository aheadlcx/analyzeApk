package qsbk.app.nearby.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class ab implements OnClickListener {
    final /* synthetic */ String a;
    final /* synthetic */ InfoCompleteActivity b;

    ab(InfoCompleteActivity infoCompleteActivity, String str) {
        this.b = infoCompleteActivity;
        this.a = str;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        this.b.submitAvatar(this.a);
    }
}
