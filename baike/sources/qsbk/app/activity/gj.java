package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class gj implements OnClickListener {
    final /* synthetic */ OnItemLongClick a;

    gj(OnItemLongClick onItemLongClick) {
        this.a = onItemLongClick;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
    }
}
