package qsbk.app.live.ui.family;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class bn implements OnClickListener {
    final /* synthetic */ bm a;

    bn(bm bmVar) {
        this.a = bmVar;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        switch (i) {
            case 0:
                this.a.b.a(this.a.a);
                return;
            case 1:
                this.a.b.b(this.a.a);
                return;
            case 2:
                this.a.b.c(this.a.a);
                return;
            default:
                return;
        }
    }
}
