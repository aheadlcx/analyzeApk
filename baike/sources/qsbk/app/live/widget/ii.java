package qsbk.app.live.widget;

import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;

class ii implements OnDismissListener {
    final /* synthetic */ RollTableGameView a;

    ii(RollTableGameView rollTableGameView) {
        this.a = rollTableGameView;
    }

    public void onDismiss(DialogInterface dialogInterface) {
        if (this.a.mLiveBaseActivity != null) {
            this.a.mLiveBaseActivity.light();
        }
    }
}
