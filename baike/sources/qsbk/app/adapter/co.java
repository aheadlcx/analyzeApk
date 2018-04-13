package qsbk.app.adapter;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class co implements OnClickListener {
    final /* synthetic */ QiuYouAdapter a;

    co(QiuYouAdapter qiuYouAdapter) {
        this.a = qiuYouAdapter;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.cancel();
    }
}
