package qsbk.app.fragments;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import qsbk.app.core.AsyncTask;

class m implements OnClickListener {
    final /* synthetic */ BaseNearbyFragment a;

    m(BaseNearbyFragment baseNearbyFragment) {
        this.a = baseNearbyFragment;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        switch (i) {
            case 0:
                AsyncTask nVar = new n(this);
                this.a.a(true);
                nVar.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[0]);
                dialogInterface.dismiss();
                return;
            case 1:
                dialogInterface.dismiss();
                return;
            default:
                return;
        }
    }
}
