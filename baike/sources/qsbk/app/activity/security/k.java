package qsbk.app.activity.security;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;

class k implements OnClickListener {
    final /* synthetic */ j a;

    k(j jVar) {
        this.a = jVar;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        if (i == 0) {
            this.a.a.startActivityForResult(new Intent(this.a.a, EmailBindActivity.class), this.a.a.h);
        }
        dialogInterface.dismiss();
    }
}
