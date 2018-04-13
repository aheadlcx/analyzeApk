package qsbk.app.live.widget;

import android.view.View;
import android.view.View.OnClickListener;

class ch implements OnClickListener {
    final /* synthetic */ GameResultDialog a;

    ch(GameResultDialog gameResultDialog) {
        this.a = gameResultDialog;
    }

    public void onClick(View view) {
        this.a.dismiss();
    }
}
