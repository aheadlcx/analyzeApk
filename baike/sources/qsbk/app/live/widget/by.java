package qsbk.app.live.widget;

import android.view.View;
import android.view.View.OnClickListener;

class by implements OnClickListener {
    final /* synthetic */ GameHistoryDialog a;

    by(GameHistoryDialog gameHistoryDialog) {
        this.a = gameHistoryDialog;
    }

    public void onClick(View view) {
        this.a.dismiss();
    }
}
