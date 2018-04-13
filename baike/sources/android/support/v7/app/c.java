package android.support.v7.app;

import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;

class c implements OnClickListener {
    final /* synthetic */ AlertController a;

    c(AlertController alertController) {
        this.a = alertController;
    }

    public void onClick(View view) {
        Message obtain;
        if (view == this.a.c && this.a.d != null) {
            obtain = Message.obtain(this.a.d);
        } else if (view == this.a.e && this.a.f != null) {
            obtain = Message.obtain(this.a.f);
        } else if (view != this.a.g || this.a.h == null) {
            obtain = null;
        } else {
            obtain = Message.obtain(this.a.h);
        }
        if (obtain != null) {
            obtain.sendToTarget();
        }
        this.a.p.obtainMessage(1, this.a.a).sendToTarget();
    }
}
