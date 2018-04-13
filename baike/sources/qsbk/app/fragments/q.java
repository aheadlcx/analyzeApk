package qsbk.app.fragments;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;

class q implements OnCancelListener {
    final /* synthetic */ BaseNearbyFragment a;

    q(BaseNearbyFragment baseNearbyFragment) {
        this.a = baseNearbyFragment;
    }

    public void onCancel(DialogInterface dialogInterface) {
        this.a.show_restart();
    }
}
