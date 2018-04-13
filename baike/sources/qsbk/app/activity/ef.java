package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;

class ef implements OnCancelListener {
    final /* synthetic */ ee a;

    ef(ee eeVar) {
        this.a = eeVar;
    }

    public void onCancel(DialogInterface dialogInterface) {
        if (this.a.a.J != null && this.a.a.J.isShowing()) {
            this.a.a.J.dismiss();
        }
    }
}
