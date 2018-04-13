package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class iw implements OnClickListener {
    final /* synthetic */ iu a;

    iw(iu iuVar) {
        this.a = iuVar;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        switch (i) {
            case 0:
                this.a.a.V.getPicFromCapture();
                return;
            case 1:
                this.a.a.V.getPicFromContent();
                return;
            default:
                return;
        }
    }
}
