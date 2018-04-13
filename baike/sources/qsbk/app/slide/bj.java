package qsbk.app.slide;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import qsbk.app.model.Comment;

class bj implements OnClickListener {
    final /* synthetic */ Comment a;
    final /* synthetic */ int b;
    final /* synthetic */ OnItemLongClick c;

    bj(OnItemLongClick onItemLongClick, Comment comment, int i) {
        this.c = onItemLongClick;
        this.a = comment;
        this.b = i;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        switch (i) {
            case 0:
                this.c.a.a(this.a, this.b);
                dialogInterface.dismiss();
                return;
            default:
                return;
        }
    }
}
