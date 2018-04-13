package qsbk.app.nearby.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class ae implements OnClickListener {
    final /* synthetic */ InfoCompleteActivity a;

    ae(InfoCompleteActivity infoCompleteActivity) {
        this.a = infoCompleteActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        switch (i) {
            case 0:
                this.a.n.getPicFromCapture();
                return;
            case 1:
                this.a.n.getPicFromContent();
                return;
            default:
                return;
        }
    }
}
