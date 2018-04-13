package qsbk.app.widget.qbnews.ad;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class b implements OnClickListener {
    final /* synthetic */ BaiduNewsAdCell a;

    b(BaiduNewsAdCell baiduNewsAdCell) {
        this.a = baiduNewsAdCell;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
    }
}
