package qsbk.app.widget.qiuyoucircle;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import qsbk.app.widget.qiuyoucircle.BaseAdCell.BaiduAdCell;

class b implements OnClickListener {
    final /* synthetic */ BaiduAdCell a;

    b(BaiduAdCell baiduAdCell) {
        this.a = baiduAdCell;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
    }
}
