package qsbk.app.activity.pay;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class av implements OnClickListener {
    final /* synthetic */ String a;
    final /* synthetic */ au b;

    av(au auVar, String str) {
        this.b = auVar;
        this.a = str;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        if (!"904".equals(this.a)) {
            this.b.e.newLaisee(this.b.a, this.b.b, this.b.c, this.b.d);
        }
    }
}
