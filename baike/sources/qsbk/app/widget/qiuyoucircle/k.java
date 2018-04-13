package qsbk.app.widget.qiuyoucircle;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class k implements OnClickListener {
    final /* synthetic */ BaseUserCell a;

    k(BaseUserCell baseUserCell) {
        this.a = baseUserCell;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
    }
}
