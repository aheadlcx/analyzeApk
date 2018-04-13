package qsbk.app.widget;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class t implements OnClickListener {
    final /* synthetic */ ArticleMoreOperationbar a;

    t(ArticleMoreOperationbar articleMoreOperationbar) {
        this.a = articleMoreOperationbar;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
    }
}
