package qsbk.app.widget.qbnews.ad;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class d implements OnClickListener {
    final /* synthetic */ GdtNewsAdCell a;

    d(GdtNewsAdCell gdtNewsAdCell) {
        this.a = gdtNewsAdCell;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
    }
}
