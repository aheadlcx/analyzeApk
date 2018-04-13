package qsbk.app.widget.qiuyoucircle;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import qsbk.app.widget.qiuyoucircle.BaseAdCell.GDTAdCell;

class d implements OnClickListener {
    final /* synthetic */ GDTAdCell a;

    d(GDTAdCell gDTAdCell) {
        this.a = gDTAdCell;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
    }
}
