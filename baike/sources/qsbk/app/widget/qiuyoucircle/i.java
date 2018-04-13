package qsbk.app.widget.qiuyoucircle;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import qsbk.app.widget.qiuyoucircle.BaseAdCell.QbAdCell;

class i implements OnClickListener {
    final /* synthetic */ QbAdCell a;

    i(QbAdCell qbAdCell) {
        this.a = qbAdCell;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
    }
}
