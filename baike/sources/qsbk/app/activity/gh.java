package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class gh implements OnClickListener {
    final /* synthetic */ OnItemLongClick a;

    gh(OnItemLongClick onItemLongClick) {
        this.a = onItemLongClick;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
    }
}
