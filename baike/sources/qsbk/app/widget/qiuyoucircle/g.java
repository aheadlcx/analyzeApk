package qsbk.app.widget.qiuyoucircle;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import qsbk.app.widget.qiuyoucircle.BaseAdCell.QHAdCell;

class g implements OnClickListener {
    final /* synthetic */ QHAdCell a;

    g(QHAdCell qHAdCell) {
        this.a = qHAdCell;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
    }
}
