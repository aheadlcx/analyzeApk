package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class vj implements OnClickListener {
    final /* synthetic */ MyInfoActivity a;

    vj(MyInfoActivity myInfoActivity) {
        this.a = myInfoActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.cancel();
    }
}
