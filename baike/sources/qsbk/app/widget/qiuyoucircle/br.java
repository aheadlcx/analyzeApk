package qsbk.app.widget.qiuyoucircle;

import android.view.View;
import android.view.View.OnClickListener;

class br implements OnClickListener {
    final /* synthetic */ UnsupportCell a;

    br(UnsupportCell unsupportCell) {
        this.a = unsupportCell;
    }

    public void onClick(View view) {
        this.a.checkNewVersion();
    }
}
