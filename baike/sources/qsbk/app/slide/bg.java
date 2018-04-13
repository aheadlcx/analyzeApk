package qsbk.app.slide;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class bg implements OnClickListener {
    final /* synthetic */ OnItemLongClick a;

    bg(OnItemLongClick onItemLongClick) {
        this.a = onItemLongClick;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
    }
}
