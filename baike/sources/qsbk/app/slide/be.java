package qsbk.app.slide;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class be implements OnClickListener {
    final /* synthetic */ OnItemLongClick a;

    be(OnItemLongClick onItemLongClick) {
        this.a = onItemLongClick;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
    }
}
