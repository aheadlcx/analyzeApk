package qsbk.app.live.widget;

import android.view.View;
import android.view.View.OnClickListener;

class bu implements OnClickListener {
    final /* synthetic */ GameGuessHelpDialog a;

    bu(GameGuessHelpDialog gameGuessHelpDialog) {
        this.a = gameGuessHelpDialog;
    }

    public void onClick(View view) {
        if (this.a.listenner != null && this.a.k != null) {
            this.a.listenner.clickListenner(1, this.a.k);
        }
    }
}
