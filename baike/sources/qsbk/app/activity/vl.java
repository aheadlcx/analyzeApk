package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class vl implements OnClickListener {
    final /* synthetic */ MyInfoActivity a;

    vl(MyInfoActivity myInfoActivity) {
        this.a = myInfoActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.cancel();
    }
}
