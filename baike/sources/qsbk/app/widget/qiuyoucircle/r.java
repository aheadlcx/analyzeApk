package qsbk.app.widget.qiuyoucircle;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class r implements OnClickListener {
    final /* synthetic */ p a;

    r(p pVar) {
        this.a = pVar;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.a.b.a(1);
        dialogInterface.dismiss();
    }
}
