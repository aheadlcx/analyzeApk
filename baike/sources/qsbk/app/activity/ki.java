package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class ki implements OnClickListener {
    final /* synthetic */ kg a;

    ki(kg kgVar) {
        this.a = kgVar;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        switch (i) {
            case 0:
                EditInfoEntranceActivity.a(this.a.a).getPicFromCapture();
                return;
            case 1:
                EditInfoEntranceActivity.a(this.a.a).getPicFromContent();
                return;
            default:
                return;
        }
    }
}
