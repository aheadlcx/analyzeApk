package qsbk.app.adapter;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class db implements OnClickListener {
    final /* synthetic */ QiuYouAdapter a;

    db(QiuYouAdapter qiuYouAdapter) {
        this.a = qiuYouAdapter;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.cancel();
    }
}
