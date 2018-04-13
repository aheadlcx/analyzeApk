package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class kz implements OnClickListener {
    final /* synthetic */ kx a;

    kz(kx kxVar) {
        this.a = kxVar;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        switch (i) {
            case 0:
                this.a.a.e.getPicFromCapture();
                return;
            case 1:
                this.a.a.e.getPicFromContent();
                return;
            default:
                return;
        }
    }
}
