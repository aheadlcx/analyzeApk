package qsbk.app.widget.qiuyoucircle;

import android.view.View;
import android.view.View.OnClickListener;

class bq implements OnClickListener {
    final /* synthetic */ UnsupportCell a;

    bq(UnsupportCell unsupportCell) {
        this.a = unsupportCell;
    }

    public void onClick(View view) {
        this.a.checkNewVersion();
    }
}
