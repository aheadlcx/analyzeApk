package qsbk.app.widget.qbnews.ad;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class f implements OnClickListener {
    final /* synthetic */ QbNewsAdCell a;

    f(QbNewsAdCell qbNewsAdCell) {
        this.a = qbNewsAdCell;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
    }
}
