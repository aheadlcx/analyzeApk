package qsbk.app.live.widget;

import android.view.View;
import android.view.View.OnClickListener;

class bv implements OnClickListener {
    final /* synthetic */ GameGuessHelpDialog a;

    bv(GameGuessHelpDialog gameGuessHelpDialog) {
        this.a = gameGuessHelpDialog;
    }

    public void onClick(View view) {
        if (this.a.listenner != null && this.a.k != null) {
            this.a.listenner.clickListenner(2, this.a.k);
        }
    }
}
