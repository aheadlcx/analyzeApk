package qsbk.app.live.widget;

import android.view.View;
import android.view.View.OnClickListener;

class r implements OnClickListener {
    final /* synthetic */ DollHistoryDialog a;

    r(DollHistoryDialog dollHistoryDialog) {
        this.a = dollHistoryDialog;
    }

    public void onClick(View view) {
        this.a.dismiss();
    }
}
