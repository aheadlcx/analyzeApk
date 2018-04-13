package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class eg implements OnClickListener {
    final /* synthetic */ ee a;

    eg(ee eeVar) {
        this.a = eeVar;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        if (this.a.a.J != null && this.a.a.J.isShowing()) {
            this.a.a.J.dismiss();
        }
    }
}
