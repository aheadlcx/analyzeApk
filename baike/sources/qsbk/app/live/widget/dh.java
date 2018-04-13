package qsbk.app.live.widget;

import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;

class dh implements OnDismissListener {
    final /* synthetic */ GameView a;

    dh(GameView gameView) {
        this.a = gameView;
    }

    public void onDismiss(DialogInterface dialogInterface) {
        if (this.a.mLiveBaseActivity != null) {
            this.a.mLiveBaseActivity.light();
        }
    }
}
