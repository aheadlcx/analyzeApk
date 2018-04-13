package qsbk.app.widget.qbnews.ad;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class h implements OnClickListener {
    final /* synthetic */ QhNewsAdCell a;

    h(QhNewsAdCell qhNewsAdCell) {
        this.a = qhNewsAdCell;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
    }
}
