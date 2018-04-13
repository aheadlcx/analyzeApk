package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;

class q implements OnCancelListener {
    final /* synthetic */ ActionBarLoginActivity a;

    q(ActionBarLoginActivity actionBarLoginActivity) {
        this.a = actionBarLoginActivity;
    }

    public void onCancel(DialogInterface dialogInterface) {
        ActionBarLoginActivity.d(this.a, true);
    }
}
